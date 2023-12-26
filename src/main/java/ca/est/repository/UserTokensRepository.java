package ca.est.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.est.entity.UserToken;

/**
 * @author Estevam Meneses
 */
public interface UserTokensRepository extends JpaRepository<UserToken, Long> {
}