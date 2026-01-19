package com.learningmat.ecommerce.repository;

import com.learningmat.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String name);
    Optional<User> findByUsername(String name);
}
