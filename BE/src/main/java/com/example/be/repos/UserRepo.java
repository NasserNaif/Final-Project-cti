package com.example.be.repos;

import com.example.be.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findUserByUsernameOrEmail(String username, String email);

    Optional<User> findUserById(Integer id);
}
