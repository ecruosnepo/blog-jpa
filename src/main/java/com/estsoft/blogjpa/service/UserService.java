package com.estsoft.blogjpa.service;

import com.estsoft.blogjpa.domain.User;
import com.estsoft.blogjpa.dto.AddUserRequest;
import com.estsoft.blogjpa.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User save(AddUserRequest addUserRequest){
        return userRepository.save(
                User.builder()
                        .email(addUserRequest.getEmail())
                        .password(bCryptPasswordEncoder.encode(addUserRequest.getPassword()))
                        .build()
        );
    }
}
