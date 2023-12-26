package ca.est.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Estevam Meneses
 */
@Getter
@Setter
@Entity
@Table(name = "user_app")
public class UserApp {

	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_user;

	@Column(name = "username")
	private String username;

	@Column(name = "role")
	private String role;

	@Column(name = "created")
	private LocalDateTime created;
	
	
	@JsonManagedReference // Is the forward part of reference, the one that gets serialized normally
	@OneToMany(mappedBy = "userApp", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<UserToken> userToken = new HashSet<UserToken>();

	public UserApp() {
		super();
	}

	public UserApp(String username, String role, LocalDateTime created, Set<UserToken> userToken) {
		super();
		this.role = role;
		this.username = username;
		this.created = created;
		this.userToken = userToken;
	}

	public UserApp(Long id_user, String username, String role, LocalDateTime created, Set<UserToken> userToken) {
		super();
		this.id_user = id_user;
		this.role = role;
		this.username = username;
		this.created = created;
		this.userToken = userToken;
	}
}
