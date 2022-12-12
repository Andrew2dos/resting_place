package com.andrew2dos.restingplace.config;

import com.andrew2dos.restingplace.service.RoleService;
import com.andrew2dos.restingplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateAdmin implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
//        User user = new User();
//        String passwordEncoded = passwordEncoder.encode("admin");
//        user.setUserName("admin");
//        user.setPassword(passwordEncoded);
//        Role roleAdmin = roleService.getByRoleName(RoleName.ROLE_ADMIN).get();
//        Role roleUser = roleService.getByRoleName(RoleName.ROLE_USER).get();
//        Set<Role> roles = new HashSet<>();
//        roles.add(roleAdmin);
//        roles.add(roleUser);
//        user.setRoles(roles);
//        userService.save(user);
      }
}
