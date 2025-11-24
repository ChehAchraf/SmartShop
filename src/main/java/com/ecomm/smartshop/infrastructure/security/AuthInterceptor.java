package com.ecomm.smartshop.infrastructure.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception{

        String path = request.getRequestURI();
        if (path.startsWith("/api/auth/") || path.contains("swagger") || path.contains("api-docs")) {
            return true;
        }

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("USER_ID") == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Vous devez vous connecter");
            return false;
        }
        return true;

    }

}
