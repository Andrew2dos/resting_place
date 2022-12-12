package com.andrew2dos.restingplace.service;

import com.andrew2dos.restingplace.entity.Role;
import com.andrew2dos.restingplace.enums.RoleName;

import java.util.Optional;

public interface RoleService {

    public void save(Role rol);

    public Optional<Role> getByRoleName(RoleName roleName);

    public boolean existsByRoleName(RoleName roleName);
}
