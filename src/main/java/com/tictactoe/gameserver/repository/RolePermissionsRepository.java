package com.tictactoe.gameserver.repository;

import com.tictactoe.gameserver.domain.RolePermissions;
import org.springframework.data.repository.CrudRepository;

/**
 * RolePermissions Crud repository.
 *
 * @author Yogesh Gaikwad
 * @since 1.0.0
 */
public interface RolePermissionsRepository extends CrudRepository<RolePermissions, Long> {
}