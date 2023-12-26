package ca.est.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.est.entity.UserApp;
import jakarta.transaction.Transactional;

/**
 * @author Estevam Meneses
 */
@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {
	
	Optional<UserApp> findByUsername(String username);
	
	@Transactional
    @Query(value = "SELECT * FROM user_app u WHERE u.username = :username ", nativeQuery = true)
    Optional<UserApp> findByUsernameNative(@Param("username") String username);

	Boolean existsByUsername(String username);
	// Boolean existsByEmail(String email);
}
