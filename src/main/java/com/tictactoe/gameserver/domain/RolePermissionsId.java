package com.tictactoe.gameserver.domain;


import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class RolePermissionsId implements Serializable {

    @Column(name = "permission_id")
    private Long permissionId;

    @Column(name = "role_id")
    private Long roleId;

    public RolePermissionsId(Long permissionId, Long roleId) {
        this.permissionId = permissionId ;
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }

        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        RolePermissionsId pk = (RolePermissionsId) o;
        return Objects.equals(permissionId, pk.permissionId ) &&
                Objects.equals(roleId, pk.roleId );
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionId, roleId );
    }
}