package ca.est.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import ca.est.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

/**
 * @author Estevam Meneses
 */
@Log4j2
public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtils;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (request.getRequestURI().contains("/api/")) {
			try {
				String jwt = parseJwt(request);
				if (jwt != null && jwtUtils.isValidToken(jwt)) {
					
					String username = jwtUtils.getUsername(jwt);
					UserDetails userDetails = userDetailsService.loadUserByUsername(username);
					JwtAuthenticationToken jwtAuthToken = new JwtAuthenticationToken(jwt, userDetails);
					SecurityContextHolder.getContext().setAuthentication(jwtAuthToken);
				}
			} catch (Exception e) {
				log.error("Cannot set user authentication: {}", e.getMessage());
				response.sendError(401);
				return;
			}
		}
		filterChain.doFilter(request, response);

	}

	/**
	 * Parse JWT from the header
	 * 
	 * @param request
	 * @return
	 */
	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}

		return null;
	}
}
