package com.tictactoe.gameserver.repository;

import com.tictactoe.gameserver.domain.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Client Crud repository.
 *
 * @author Yogesh Gaikwad
 * @since 1.0.0
 */
public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findOneByClientId(String clientId);
}