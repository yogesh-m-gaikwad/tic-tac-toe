package com.tictactoe.gameserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "user_roles")
public class UserRoles implements Serializable {

    private static final long serialVersionUID = 1L;

    public UserRoles(UserRoleId userRoleId, Long version) {
        this.userRoleId = userRoleId;
        this.version = version;
    }

    @EmbeddedId
    private UserRoleId userRoleId;

    @Version
    private Long version;

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime createdOn;

    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updatedOn;
}