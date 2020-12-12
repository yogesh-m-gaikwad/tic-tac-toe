package com.tictactoe.gameserver.repository;

import com.tictactoe.gameserver.domain.RolePermissions;
import org.springframework.data.repository.CrudRepository;

public interface RolePermissionsRepository extends CrudRepository<RolePermissions, Long> {
}