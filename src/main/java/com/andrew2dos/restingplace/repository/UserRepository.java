package com.andrew2dos.restingplace.repository;

import com.andrew2dos.restingplace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

}
