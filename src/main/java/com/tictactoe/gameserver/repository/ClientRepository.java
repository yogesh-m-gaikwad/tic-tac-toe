package com.tictactoe.gameserver.repository;

import com.tictactoe.gameserver.domain.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findOneByClientId(String clientId);
}