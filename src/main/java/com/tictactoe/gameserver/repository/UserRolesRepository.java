package com.tictactoe.gameserver.repository;

import com.tictactoe.gameserver.domain.UserRoles;
import org.springframework.data.repository.CrudRepository;

public interface UserRolesRepository extends CrudRepository<UserRoles, Long> {
}