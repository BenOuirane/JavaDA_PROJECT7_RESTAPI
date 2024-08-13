package com.nnk.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nnk.springboot.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
    CustomSuccessHandler customSuccessHandler;
	
	@Autowired
	CustomUserDetailsService customerUserDetailsService;

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf(c -> c.disable())
            .authorizeHttpRequests(request -> request
            .requestMatchers("/","/css/**").permitAll()
            .requestMatchers("/admin/**").hasAuthority("ADMIN")
            .requestMatchers("/").hasAuthority("USER")
            .anyRequest()
            .authenticated()
            )
            .formLogin(form -> form
            .loginPage("/app/login")
            .loginProcessingUrl("/app/login")
        	.successHandler(customSuccessHandler)
        	.permitAll()
        	
        	)
        .logout(logout -> logout
        		.logoutRequestMatcher(new AntPathRequestMatcher("/app-logout"))
        		.logoutSuccessUrl("/login?logout")
        		.invalidateHttpSession(true)
        		.clearAuthentication(true)
        		.permitAll()

        		)
        .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Crée une session uniquement si nécessaire
                .sessionFixation().newSession() // Crée une nouvelle session après l'authentification
                .maximumSessions(1) // Limite le nombre de sessions simultanées à 1
                .expiredUrl("/login?sessionExpired") // URL à rediriger lorsque la session expire
            );
	
		return http.build();
	}

	@Autowired
	public void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customerUserDetailsService).passwordEncoder(passwordEncoder());
		
	}
}
