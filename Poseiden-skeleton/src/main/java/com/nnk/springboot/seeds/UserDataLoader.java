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
	        String encodedPassword = passwordEncoder.encode("123");

	        if (userRepository.count() == 0) {
	            User user = new User(1,"julian",encodedPassword,"cauwet","USER");
	            User user1 = new User(2,"hajer",encodedPassword,"ben","ADMIN");

	            userRepository.save(user);
	            userRepository.save(user1);


	            System.out.println("Sample user data loaded.");
	        } else {
	            System.out.println("Users data already exists.");
	        }
	    }
	    

}
