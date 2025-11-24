package com.ecomm.smartshop.identity.controller;

import com.ecomm.smartshop.identity.dto.AuthRequest;
import com.ecomm.smartshop.identity.dto.AuthResponse;
import com.ecomm.smartshop.identity.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final ResourceLoader resourceLoader;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request,
                                              HttpServletRequest httpRequest){
        AuthResponse user = authService.login(request);

        HttpSession session = httpRequest.getSession(true   );

        session.setAttribute("USER_ID",user.id());
        session.setAttribute("USER_ROLE",user.role());

        return ResponseEntity.ok(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.noContent().build();
    }
}
