package com.tictactoe.gameserver.repository;

import com.tictactoe.gameserver.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface GameRepository extends JpaRepository<Game, Long> {

    @Override
    Optional<Game> findById(Long gameId);
}
