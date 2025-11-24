package com.ecomm.smartshop.identity.service;

import com.ecomm.smartshop.identity.dto.AuthRequest;
import com.ecomm.smartshop.identity.dto.AuthResponse;
import com.ecomm.smartshop.identity.entity.User;
import com.ecomm.smartshop.identity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public AuthResponse login(AuthRequest request){
        User user = userRepository.findByUsername(request.username())
                    .orElseThrow(()-> new RuntimeException("username or password is incorrect"));

        if(!user.getPassword().equals(request.password())){
            throw new RuntimeException("username or password is incorrect");
        }
        return new AuthResponse(user.getId(), user.getUsername(), user.getRole());
    }
}
