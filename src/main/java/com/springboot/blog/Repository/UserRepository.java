package com.springboot.blog.Repository;

import com.springboot.blog.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailOrUsername(String email, String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


}
