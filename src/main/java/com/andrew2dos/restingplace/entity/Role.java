package com.andrew2dos.restingplace.entity;

import com.andrew2dos.restingplace.enums.RoleName;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", unique = true)
    private RoleName roleName;

    public Long getId() {
        return id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

}
