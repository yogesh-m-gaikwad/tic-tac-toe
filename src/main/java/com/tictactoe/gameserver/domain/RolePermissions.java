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
    @Version
    protected Long version;
    @JsonIgnore
    @CreationTimestamp
    protected LocalDateTime createdOn;

    /*@Column(name = "permission_id")
    private Long permissionId;

    @Column(name = "role_id")
    private Long roleId;*/
    @JsonIgnore
    @UpdateTimestamp
    protected LocalDateTime updatedOn;
    @EmbeddedId
    private RolePermissionsId rolePermissionsId;

    public RolePermissions(RolePermissionsId rolePermissionsId, Long version) {
        this.rolePermissionsId = rolePermissionsId;
        this.version = version;
    }
}
