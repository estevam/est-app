package ca.est.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_token")
public class UserToken {
	@Id
	@Column(name = "id_token")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_token;

	@Column(name = "token")
	private String token;

	@Column(name = "created")
	private LocalDateTime created;

	@Column(name = "expire")
	private LocalDateTime expire;
	
	@JsonBackReference //is the back part of reference; it’ll be omitted from serialization.
	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	private UserApp userApp;

	public UserToken() {
		super();
	}

	public UserToken(String token, LocalDateTime created, LocalDateTime expire) {
		super();
		this.token = token;
		this.created = created;
		this.expire = expire;
	}

	public UserToken(Long id_token, String token, LocalDateTime created, LocalDateTime expire) {
		super();
		this.id_token = id_token;
		this.token = token;
		this.created = created;
		this.expire = expire;
	}

	public UserToken(Long id_token, String token, LocalDateTime created, LocalDateTime expire, UserApp userApp) {
		super();
		this.id_token = id_token;
		this.token = token;
		this.created = created;
		this.expire = expire;
		this.userApp = userApp;
	}
}