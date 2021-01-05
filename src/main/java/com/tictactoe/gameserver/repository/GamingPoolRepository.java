package com.tictactoe.gameserver.repository;

import com.tictactoe.gameserver.domain.GamingPool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * GamingPool crud repository.
 *
 * @author Yogesh Gaikwad
 * @since 1.0.0
 */
@Repository
@Transactional
public interface GamingPoolRepository extends CrudRepository<GamingPool, Long> {

    List<GamingPool> findAllByWaitingForPairTrueAndRequestedTikTacToeTrueAndUserIdNot(Long userId);

    Optional<GamingPool> findByUserId(Long userId);

    Optional<GamingPool> findByUserIdAndWaitingForPairTrue(Long userId);
}
