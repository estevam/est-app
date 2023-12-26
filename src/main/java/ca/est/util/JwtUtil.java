package ca.est.util;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * 
 * @author Estevam Meneses
 * https://github.com/jwtk/jjwt?tab=readme-ov-file
 */
@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	public String secret;

	@Value("${jwt.access_expiration_sc}")
	private Integer access_expiration_sc;

	@Value("${jwt.refresh_expiration_sc}")
	private Integer refresh_expiration_sc;

	//private SecureDigestAlgorithm<SecretKey, SecretKey> ALGORITHM = Jwts.SIG.HS256;

	/**
	 * 
	 * @return
	 */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

	public String generateToken(String username) {
		String uuid = UUID.randomUUID().toString(); // Used to set the JWT unique 
		Date now = new Date();
		Date exp = Date.from(Instant.now().plusSeconds(access_expiration_sc));
        return Jwts.builder()
                .claim("username", username).id(uuid)
                .issuedAt(now)
                .expiration(exp)
                .signWith(getSigningKey())
                .compact();

	}
	
    public String getUsername(String token) {
       return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload().get("username").toString();
    }
    
    public boolean isValidToken(String token) {
        String username = this.getUsername(token);
        return StringUtils.isNotEmpty(username) && !isTokenExpired(token);
    }
    
    private boolean isTokenExpired(String token) {
    	return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }
    
	/*
	public String generateTokenV1(String username) {
		SecretKey KEY = Keys.hmacShaKeyFor(secret.getBytes());
		String uuid = UUID.randomUUID().toString();

		Date now = new Date();
		Date exp = Date.from(Instant.now().plusSeconds(access_expiration_sc));
		return Jwts.builder().header().add("typ", "JWT").add("alg", "HS256").and().claim("username", username).id(uuid)
				.expiration(exp).issuedAt(now).subject("access_token").issuer("Blog").signWith(KEY, ALGORITHM)
				.compact();

	}

	public String generateTokenWithContent(String username, String content) {
		byte[] contentByte = content.getBytes(StandardCharsets.UTF_8);
		String uuid = UUID.randomUUID().toString();
		SecretKey KEY = Keys.hmacShaKeyFor(secret.getBytes());
		Date now = new Date();
		Date exp = Date.from(Instant.now().plusSeconds(refresh_expiration_sc));
		return Jwts.builder().header().add("typ", "JWT").add("alg", "HS256").and().claim("username", username).id(uuid)
				.expiration(exp).issuedAt(now).content(contentByte, "text/plain").issuer(username)
				.signWith(KEY, ALGORITHM).compact();
	}

	public boolean isValidToken(String token) {
		try {
			SecretKey KEY = Keys.hmacShaKeyFor(secret.getBytes());
			Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token);
			return true;
		} catch (JwtException e) {

			// don't trust the JWT!
			return false;
		}

	}

	public String getContent(String token) {
		try {
			SecretKey KEY = Keys.hmacShaKeyFor(secret.getBytes());
			return Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token).getPayload().toString();
		} catch (JwtException e) {
			return null;
		}

	}

	public String getUsername(String token) {
		try {
			SecretKey KEY = Keys.hmacShaKeyFor(secret.getBytes());
			return Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token).getPayload().get("username",
					String.class);

		} catch (JwtException e) {
			return null;
		}

	}
	*/
}
