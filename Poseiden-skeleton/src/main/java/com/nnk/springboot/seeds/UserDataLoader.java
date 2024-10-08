package com.nnk.springboot.seeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@Component
public class UserDataLoader implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	   
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public void run(String... args) throws Exception {
		loadUserData();		
	}
	
	private void loadUserData() {
	        String encodedPassword = passwordEncoder.encode("Passw0rd!");
	        String encodedPassword1 = passwordEncoder.encode("Passw0rd!");

	        if (userRepository.count() == 0) {
	            User user1 = new User(1,"hajer",encodedPassword,"ben","ADMIN");
	            User user2 = new User(2,"chedi",encodedPassword1,"ben","USER");
 
	            userRepository.save(user1);
	            userRepository.save(user2);
 
	        } 
	    }
	    
}
