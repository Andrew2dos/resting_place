package com.andrew2dos.restingplace.security.service;

import com.andrew2dos.restingplace.entity.User;
import com.andrew2dos.restingplace.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService { // Задача сервиса по имени пользователя предоставить объект юзера

    @Autowired
    UserServiceImpl userService; // доступ к userRepository

    @Override
    @Transactional // чтоб избежать lazyLoadException тк колекция грузиться в режиме eager
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUserName(username).orElseThrow(()-> new UsernameNotFoundException(username));
        return MainUser.build(user);
    }
}
