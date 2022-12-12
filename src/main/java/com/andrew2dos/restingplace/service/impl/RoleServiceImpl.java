package com.andrew2dos.restingplace.service.impl;

import com.andrew2dos.restingplace.entity.Role;
import com.andrew2dos.restingplace.enums.RoleName;
import com.andrew2dos.restingplace.repository.RoleRepository;
import com.andrew2dos.restingplace.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    public void save(Role rol){
        roleRepository.save(rol);
    }

    public Optional<Role> getByRoleName(RoleName roleName){
        return roleRepository.findByRoleName(roleName);
    }

    public boolean existsByRoleName(RoleName roleName){
        return roleRepository.existsByRoleName(roleName);
    }
}
