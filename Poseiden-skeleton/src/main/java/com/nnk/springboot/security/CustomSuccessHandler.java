package com.nnk.springboot.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Custom handler for processing successful authentication events.
 * This class redirects users to different pages based on their roles after a successful login.
 */
@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * Handles the successful authentication event by redirecting users to different pages 
     * based on their roles and storing the username in the session.
     *
     * @param request        the {@link HttpServletRequest} that resulted in successful authentication.
     * @param response       the {@link HttpServletResponse} so you can redirect the user after login.
     * @param authentication the {@link Authentication} object that contains the details of the authenticated user.
     * @throws IOException      if an input or output exception occurs.
     * @throws ServletException if a servlet-specific error occurs.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        
        HttpSession session = request.getSession();
        String username = authentication.getName();
        session.setAttribute("username", username); // Store the username in the session

        var authorities = authentication.getAuthorities();
        var roles = authorities.stream().map(r -> r.getAuthority()).findFirst();

        if (roles.orElse("").equals("ADMIN")) {
            response.sendRedirect("/admin/home");
        } else if (roles.orElse("").equals("USER")) {
            response.sendRedirect("/");
        } else {
            response.sendRedirect("/error");
        }
    }
}
