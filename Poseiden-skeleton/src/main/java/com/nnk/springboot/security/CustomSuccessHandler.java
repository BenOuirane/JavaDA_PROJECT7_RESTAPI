package com.nnk.springboot.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
		                             	HttpServletResponse response, 
		                             	Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub		
		HttpSession session = request.getSession();
        //session.setAttribute("customAttribute", "customValue");
        String username = authentication.getName();
        session.setAttribute("username", username); // Stocker le nom d'utilisateur


       // response.sendRedirect("/");
		var authourities = authentication.getAuthorities();
		var roles = authourities.stream().map(r -> r.getAuthority()).findFirst();
		if (roles.orElse("").equals("ADMIN")) {
			response.sendRedirect("/admin/home");
		} else if (roles.orElse("").equals("USER")) {
			response.sendRedirect("/");
		} else {
			response.sendRedirect("/error");
		}
		
	}

}
