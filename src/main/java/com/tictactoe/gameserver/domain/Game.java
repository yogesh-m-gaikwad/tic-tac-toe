package com.tictactoe.gameserver.domain;

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
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Game {

    private static final long serialVersionUID = 1L;

    public Game(Long userId, Long userIdPaired, boolean completed) {
        this.userId = userId;
        this.userIdPaired = userIdPaired;
        this.completed = completed;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long gameId;

    @Lob
    private String gameState = GameHelper.getDefaultGameState();

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    private Long userIdPaired;

    private boolean completed;

    @Version
    private Long version;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;
}
