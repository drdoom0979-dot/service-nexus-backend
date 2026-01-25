package com.drdoom.backend.service;

import com.drdoom.backend.entity.User;
import com.drdoom.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(String idString) {
        UUID uuid = UUID.fromString(idString);
        return userRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}