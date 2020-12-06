package com.tictactoe.gameserver.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Role {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    protected Long roleId;

    @Version
    protected Long version;

    @CreationTimestamp
    protected LocalDateTime createdOn;

    @UpdateTimestamp
    protected LocalDateTime updatedOn;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permissions", joinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "role_id")}, inverseJoinColumns = {
            @JoinColumn(name = "permission_id", referencedColumnName = "permission_id")})
    private List<Permission> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
