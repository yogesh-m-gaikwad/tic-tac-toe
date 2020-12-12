package com.tictactoe.gameserver.repository;

import com.tictactoe.gameserver.domain.Permission;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PermissionRepository extends CrudRepository<Permission, Long> {
    Optional<Permission> findOneByName(String name);
}