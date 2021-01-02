package com.tictactoe.gameserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * RolePermissions class for the role based auth.
 *
 * @author Yogesh Gaikwad
 * @since 1.0.0
 */
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "role_permissions")
public class RolePermissions implements Serializable {

    private static final long serialVersionUID = 1L;

    public RolePermissions(RolePermissionsId rolePermissionsId, Long version) {
        this.rolePermissionsId = rolePermissionsId;
        this.version = version;
    }

    @EmbeddedId
    private  RolePermissionsId rolePermissionsId;

    /*@Column(name = "permission_id")
    private Long permissionId;

    @Column(name = "role_id")
    private Long roleId;*/

    @Version
    protected Long version;

    @JsonIgnore
    @CreationTimestamp
    protected LocalDateTime createdOn;

    @JsonIgnore
    @UpdateTimestamp
    protected LocalDateTime updatedOn;
}
