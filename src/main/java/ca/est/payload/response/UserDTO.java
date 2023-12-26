package ca.est.payload.response;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author Estevam Meneses
 */
@Data
public class UserDTO {

	    private long id_user;
	    private String username;
		private String password;
		private String token;	
		private LocalDateTime created;
		
	 	public UserDTO() {
	 		super();
	 	}	
		
	 	public UserDTO(long id_user, String username, String password) {
			super();
			this.id_user = id_user;
			this.username = username;
			this.password = password;
		}    
		
		public UserDTO(long id_user, String username, String password, String token) {
			super();
			this.id_user = id_user;
			this.username = username;
			this.password = password;
			this.token = token;
		}
		
		public UserDTO(long id_user, String username, String password, String token, LocalDateTime created) {
			super();
			this.id_user = id_user;
			this.username = username;
			this.password = password;
			this.token = token;
			this.created = created;
		}
}
