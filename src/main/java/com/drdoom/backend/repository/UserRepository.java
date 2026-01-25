package com.drdoom.backend.repository;


import com.drdoom.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, java.util.UUID> {
    Optional<User> findByPhoneNumber(String phoneNumber);
    Boolean existsByPhoneNumber(String phoneNumber);
    Optional<User> findByPhoneNumberAndIsActiveTrue(String phoneNumber);
    Optional<User> findById(String id);
}
