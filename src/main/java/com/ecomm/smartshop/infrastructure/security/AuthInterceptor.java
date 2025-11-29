package com.ecomm.smartshop.infrastructure.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ecomm.smartshop.shared.enums.UserRole;

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

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            
            RequireRole requiredRoleAnnotation = handlerMethod.getMethodAnnotation(RequireRole.class);

            if (requiredRoleAnnotation != null) {
                UserRole requiredRole = requiredRoleAnnotation.value(); 
                UserRole currentRole = (UserRole) session.getAttribute("USER_ROLE"); 

                if (currentRole != requiredRole) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Accès refusé: Vous n'avez pas les droits nécessaires");
                    return false; 
                }
        
            }
        }
        return true;

    }

}
