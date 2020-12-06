package com.tictactoe.gameserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode
public class Game {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    protected Long gameId;

    private String gameState;

    protected Long userIdX;

    protected Long userIdO;

    @Version
    protected Long version;

    @CreationTimestamp
    protected LocalDateTime createdOn;

    @UpdateTimestamp
    protected LocalDateTime updatedOn;
}
