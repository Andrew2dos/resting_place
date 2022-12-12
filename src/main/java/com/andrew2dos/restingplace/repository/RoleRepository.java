package com.andrew2dos.restingplace.repository;

import com.andrew2dos.restingplace.entity.Role;
import com.andrew2dos.restingplace.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(RoleName roleName);
    boolean existsByRoleName(RoleName roleName);

}
