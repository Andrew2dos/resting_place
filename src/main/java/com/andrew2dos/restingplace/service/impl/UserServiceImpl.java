package com.andrew2dos.restingplace.service.impl;

import com.andrew2dos.restingplace.entity.User;
import com.andrew2dos.restingplace.repository.UserRepository;
import com.andrew2dos.restingplace.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getById(Long userId) {
        Optional<User> result = userRepository.findById(userId);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        } else {
            throw new RuntimeException("Did not find User0 id - " + userId);
        }
        return theUser;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

}
