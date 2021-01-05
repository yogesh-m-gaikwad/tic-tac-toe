package com.tictactoe.gameserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * UserRoles class for role base auth.
 *
 * @author Yogesh Gaikwad
 * @since 1.0.0
 */
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "user_roles")
public class UserRoles implements Serializable {

    private static final long serialVersionUID = 1L;
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

    public UserRoles(UserRoleId userRoleId, Long version) {
        this.userRoleId = userRoleId;
        this.version = version;
    }
}