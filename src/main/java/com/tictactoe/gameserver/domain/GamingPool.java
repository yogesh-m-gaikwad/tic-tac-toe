package com.tictactoe.gameserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * GamingPool class for users in pool for gaming.
 *
 * @author Yogesh Gaikwad
 * @since 1.0.0
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class GamingPool {

    private static final long serialVersionUID = 6068949006616149093L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pairId;

    private Long userId;
    // TODO: link with game table
    private Long gameId;
    private Long userIdPaired;
    private boolean requestedTikTacToe;
    private boolean waitingForPair;
    @Version
    private Long version;
    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime createdOn;
    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    public GamingPool(Long userId, boolean requestedTikTacToe, boolean waitingForPair) {
        this.userId = userId;
        this.requestedTikTacToe = requestedTikTacToe; // TODO: reconsider design - for now only one game
        this.waitingForPair = waitingForPair;
    }
}
