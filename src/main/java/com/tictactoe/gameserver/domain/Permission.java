package com.tictactoe.gameserver.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Permission class for the role based auth.
 *
 * @author Yogesh Gaikwad
 * @since 1.0.0
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Permission {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long permissionId;
    private String name;
    @Version
    private Long version;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    public Permission(Long permissionId, String name, Long version) {
        this.permissionId = permissionId;
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
