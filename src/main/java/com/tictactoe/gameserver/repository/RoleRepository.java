package com.tictactoe.gameserver.repository;

import com.tictactoe.gameserver.domain.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findOneByName(String name);

}