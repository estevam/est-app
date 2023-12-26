/**
 * 
 */
package ca.est.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ca.est.entity.UserApp;
import ca.est.security.UserDetailsBuilder;
import lombok.extern.log4j.Log4j2;

/**
 * @author Estevam Meneses
 */
@Log4j2
@Component
public class UserUtil {
	/**
	 * Get current user on spring context
	 * @return
	 */
	public UserApp getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		try {
			Object auth = authentication.getPrincipal();
			if (auth instanceof UserDetailsBuilder) {
				UserDetailsBuilder userDetailsBuilder = (UserDetailsBuilder) authentication.getPrincipal();
				return userDetailsBuilder.getUserApp();
			}
		} catch (Exception e) {
			log.error("Failed to get internal user", e);
		}
		return null;
	}
}
