package com.tictactoe.gameserver.repository;

import com.tictactoe.gameserver.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface GameRepository extends JpaRepository<Game, Long> {
    @Override
    Optional<Game> findById(Long gameId);

    Optional<Game> findFirstByUserIdAndCompletedFalseOrderByUpdatedOnDesc(Long userId);

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    <S extends Game> S save(S var1);

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    <S extends Game> S saveAndFlush(S var1);
}
