package com.tictactoe.gameserver.repository;

import com.tictactoe.gameserver.domain.UserRoles;
import org.springframework.data.repository.CrudRepository;

/**
 * UserRoles Crud repository.
 *
 * @author Yogesh Gaikwad
 * @since 1.0.0
 */
public interface UserRolesRepository extends CrudRepository<UserRoles, Long> {
}