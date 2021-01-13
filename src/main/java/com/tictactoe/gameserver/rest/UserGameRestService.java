package com.tictactoe.gameserver.rest;

import com.tictactoe.gameserver.domain.Game;
import com.tictactoe.gameserver.domain.GamingPool;
import com.tictactoe.gameserver.repository.GameRepository;
import com.tictactoe.gameserver.repository.GamingPoolRepository;
import com.tictactoe.gameserver.util.GameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Primary rest service.
 *
 * @author Yogesh Gaikwad
 * @since 1.0.0
 */
@RestController
public class UserGameRestService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GamingPoolRepository gamingPoolRepository;

    public UserGameRestService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @RequestMapping("rest/user/game/{userId}/last")
    public Optional<Game> findLastGame(@PathVariable Long userId) {
        return gameRepository.findFirstByUserIdAndCompletedFalseOrderByUpdatedOnDesc(userId);
    }

    @RequestMapping("rest/user/{userId}/pair")
    @Transactional
    public Optional<Game> requestPair(@PathVariable Long userId) {
        Optional<GamingPool> currentUserInPool = gamingPoolRepository.findByUserIdAndWaitingForPairTrue(userId);
        if (currentUserInPool.isPresent()) {
            GamingPool userPoolRecord = currentUserInPool.get();
            List<GamingPool> otherUsersInPool = new ArrayList<>();

            int tries = 0;
            while (otherUsersInPool.isEmpty() && tries < 100) {
                otherUsersInPool = gamingPoolRepository.findAllByWaitingForPairTrueAndRequestedTikTacToeTrueAndUserIdNot(userId);
                if (gamingPoolRepository.findByUserIdAndWaitingForPairTrue(userPoolRecord.getUserId()).isPresent() && !otherUsersInPool.isEmpty()) {
                    GamingPool matchInPool = GameHelper.getRandomFromList(otherUsersInPool);
                    List<GamingPool> pair = new ArrayList<>();
                    pair.add(matchInPool);
                    pair.add(userPoolRecord);
                    userPoolRecord.setWaitingForPair(false);
                    matchInPool.setWaitingForPair(false);
                    userPoolRecord.setUserIdPaired(matchInPool.getUserId());
                    matchInPool.setUserIdPaired(userPoolRecord.getUserId());

                    Game game = new Game(userPoolRecord.getUserId(), matchInPool.getUserId(), false);
                    game = gameRepository.save(game);

                    userPoolRecord.setGameId(game.getGameId());
                    matchInPool.setGameId(game.getGameId());

                    gamingPoolRepository.saveAll(pair);

                    return Optional.of(game);
                }

                tries++;
            }
        } else {
            currentUserInPool = gamingPoolRepository.findFirstByUserIdAndWaitingForPairFalseOrderByCreatedOnDesc(userId);
            if (currentUserInPool.isPresent()) {
                Optional<Game> game = gameRepository.findById(currentUserInPool.get().getGameId());

                if (game.isPresent()) {
                    return game;
                }
            }

        }

        return Optional.empty();
    }

    @RequestMapping("rest/user/{userId}/register")
    @Transactional
    public Optional<GamingPool> registerInGamePool(@PathVariable Long userId) {
        Optional<GamingPool> currentUserInPool = gamingPoolRepository.findByUserIdAndWaitingForPairTrue(userId);
        GamingPool userPoolRecord;
        if (!currentUserInPool.isPresent()) {
            GamingPool newUserInPool = new GamingPool(userId, true, true);
            userPoolRecord = gamingPoolRepository.save(newUserInPool);
        } else {
            userPoolRecord = currentUserInPool.get();
        }

        return Optional.of(userPoolRecord);
    }
}
