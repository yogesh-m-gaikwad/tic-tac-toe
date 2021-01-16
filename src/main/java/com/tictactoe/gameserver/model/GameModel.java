package com.tictactoe.gameserver.model;

import com.tictactoe.gameserver.domain.User;
import com.tictactoe.gameserver.util.GameHelper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Game class for the main game entity.
 *
 * @author Yogesh Gaikwad
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class GameModel {

    private static final long serialVersionUID = 298742480696915173L;

    private String gameState = GameHelper.getDefaultGameState();

    private boolean completed;
}
