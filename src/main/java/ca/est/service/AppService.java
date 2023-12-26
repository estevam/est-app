package ca.est.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ca.est.entity.UserApp;
import ca.est.entity.UserToken;
import ca.est.exception.NoSuchElementFoundException;
import ca.est.payload.response.ServiceResponse;
import ca.est.payload.response.UserDTO;
import ca.est.repository.UserRepository;
import ca.est.util.JwtUtil;

/**
 * @author Estevam Meneses
 */
@Service
public class AppService {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;

	@Value("${jwt.access_expiration_sc}")
	private Integer access_expiration_sc;

	// @Autowired
	// private UserTokensRepository userTokensRepository;

	/**
	 * Find all users
	 * 
	 * @return
	 */
	public ServiceResponse findAllUsers() throws NoSuchElementFoundException {
		List<UserApp> userList = userRepository.findAll();
		if (userList.isEmpty()) {
			return new ServiceResponse(HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse(userList, HttpStatus.OK);
	}

	/**
	 * Find user
	 * 
	 * @return
	 */
	public ServiceResponse findUser(long id) throws NoSuchElementFoundException {
		Optional<UserApp> userApp = userRepository.findById(id);
		if (userApp.isEmpty()) {
			return new ServiceResponse(HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse(userApp, HttpStatus.OK);
	}

	/**
	 * Find user
	 * 
	 * @return
	 */
	public ServiceResponse updateUser(UserDTO userDTO) throws NoSuchElementFoundException {
		if (ObjectUtils.isEmpty(userDTO)) {
			return new ServiceResponse(HttpStatus.BAD_REQUEST);
		}
		Optional<UserApp> userApp = userRepository.findById(userDTO.getId_user());
		if (userApp.isEmpty()) {
			return new ServiceResponse(HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse(userApp, HttpStatus.OK);
	}
	
 
	public ServiceResponse createUserAndToken(String name) {

		try {
			Optional<UserApp> userApp = userRepository.findByUsername(name);
			if (userApp.isPresent()) {
				// Update
				UserApp usr = userApp.get();
 				String token = jwtUtil.generateToken(name);
				UserToken userToken = new UserToken(token, LocalDateTime.now(),
						LocalDateTime.now().plusSeconds(access_expiration_sc));
				userToken.setUserApp(usr);
				usr.getUserToken().add(userToken);
				userRepository.save(usr);

				return new ServiceResponse(token, HttpStatus.OK);
			} else {
	            // user does not exist creating a new one 
				Set<UserToken> userTokensSet = new HashSet<UserToken>();
				String token = jwtUtil.generateToken(name);
				UserToken userToken = new UserToken(token, LocalDateTime.now(),
						LocalDateTime.now().plusSeconds(access_expiration_sc));
				UserApp usr = new UserApp(name, "ROLE_ADMIN", LocalDateTime.now(), userTokensSet);
				userToken.setUserApp(usr);
				usr.getUserToken().add(userToken);
				userRepository.save(usr);

				return new ServiceResponse(token, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ServiceResponse(HttpStatus.FORBIDDEN);
		}
	}
}