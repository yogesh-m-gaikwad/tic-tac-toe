package com.tictactoe.gameserver.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Permission {

    private static final long serialVersionUID = 1L;

    public Permission(Long permissionId, String name, Long version) {
        this.permissionId = permissionId;
        this.name = name;
        this.version = version;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    protected Long permissionId;

    private String name;

    @Version
    protected Long version;

    @CreationTimestamp
    protected LocalDateTime createdOn;

    @UpdateTimestamp
    protected LocalDateTime updatedOn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
