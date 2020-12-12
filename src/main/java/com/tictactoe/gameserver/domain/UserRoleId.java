package com.tictactoe.gameserver.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

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
        if ( this == o ) {
            return true;
        }

        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        UserRoleId pk = (UserRoleId) o;
        return Objects.equals(userId, pk.userId ) &&
                Objects.equals(roleId, pk.roleId );
    }

    @Override
    public int hashCode() {
        return Objects.hash( roleId, userId );
    }
}
