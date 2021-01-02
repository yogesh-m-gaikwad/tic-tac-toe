package com.tictactoe.gameserver.rest;

import com.tictactoe.gameserver.domain.Game;
import com.tictactoe.gameserver.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public UserGameRestService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @RequestMapping("rest/user/{userId}/lastgame")
    public Optional<Game> findLastGame(@PathVariable Long userId){
        return gameRepository.findFirstByUserIdAndCompletedFalseOrderByUpdatedOnDesc(userId);
    }
}
