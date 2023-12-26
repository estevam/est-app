package ca.est.security;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ca.est.entity.UserApp;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Estevam Meneses
 */
@Getter
@Setter
public class UserDetailsBuilder implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private long id_user;
	private String username;
	private LocalDateTime created;
	private Collection<? extends GrantedAuthority> authorities = null;
	private UserApp userApp;
	
	public UserDetailsBuilder() {

	}
	
    public UserDetailsBuilder(long id_user, String username, LocalDateTime created,
			Set<GrantedAuthority> authorities, UserApp userApp) {
		this.id_user = id_user;
		this.username = username;
		this.created = created;
		this.authorities = authorities;
		this.userApp = userApp;
	}
    
	/**
     * @param userApp
     * @return
     */
    public static UserDetailsBuilder build(UserApp userApp) {
    	//Set<UserToken> userRolesList = userApp.getUserToken();
        //Set<String> tokenList = userRolesList.stream().map(UserToken::getToken).collect(Collectors.toSet());
    	Set<String>roleSet = new HashSet<String>();
    	roleSet.add(userApp.getRole());
    	return new UserDetailsBuilder(
    			userApp.getId_user(),
    			userApp.getUsername(),
    			userApp.getCreated(),
                mapToGrantedAuthorities(roleSet),
                userApp
        );
    }

    
    /**
     * @param Task code
     * GrantedAuthority is used by the spring security 
     * @return
     */
    private static Set<GrantedAuthority> mapToGrantedAuthorities(Set<String> rolesList) {
        return rolesList.stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toSet());
    }

    

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}