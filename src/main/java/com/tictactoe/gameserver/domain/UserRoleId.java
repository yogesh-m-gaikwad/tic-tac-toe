package com.tictactoe.gameserver.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * UserRoleId class for the joining Role and User tables for role based auth.
 *
 * @author Yogesh Gaikwad
 * @since 1.0.0
 */
@Embeddable
@NoArgsConstructor
public class UserRoleId implements Serializable {

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "user_id")
    private Long userId;

    public UserRoleId(Long roleId, Long userId) {
        this.roleId = roleId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserRoleId pk = (UserRoleId) o;
        return Objects.equals(userId, pk.userId) &&
                Objects.equals(roleId, pk.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, userId);
    }
}
