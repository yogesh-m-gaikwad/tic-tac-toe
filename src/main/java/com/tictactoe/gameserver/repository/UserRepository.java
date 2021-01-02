package com.tictactoe.gameserver.repository;

import com.tictactoe.gameserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * User JPA repository.
 *
 * @author Yogesh Gaikwad
 * @since 1.0.0
 */
@Repository
@Transactional
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    Optional<User> findById(Long userId);

    User findByUsername(String username);

    User findByEmail(String email);

}
