package ca.est.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
/**
 * @author Estevam Meneses
 */
public class JwtAuthenticationToken implements Authentication {
	  private static final long serialVersionUID = 1L;
	private boolean isAuthenticated;
	  private UserDetails userDetails;
	  @Getter
	  private final String token;

	  // Constructor to be used pre-authentication
	  public JwtAuthenticationToken(String token) {
	    this.token = token;
	  }

	  // Constructor to be used after successful authentication
	  public JwtAuthenticationToken(String token, UserDetails userDetails) {
	    this.token = token;
	    this.userDetails = userDetails;
	    this.isAuthenticated = true;
	  }

	  @Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    return userDetails.getAuthorities();
	  }

	  @Override
	  public Object getCredentials() {
	    return token;
	  }

	  @Override
	  public Object getDetails() {
	    return userDetails;
	  }

	  @Override
	  public Object getPrincipal() {
	    return userDetails;
	  }

	  @Override
	  public boolean isAuthenticated() {
	    return isAuthenticated;
	  }

	  @Override
	  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
	    throw new IllegalArgumentException("Not supported, use constructor");
	  }

	  @Override
	  public String getName() {
	    return userDetails.getUsername();
	  }

}
