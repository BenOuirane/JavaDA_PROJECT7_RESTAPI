package com.nnk.springboot.seeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
/*
@Component
public class DataLoader implements CommandLineRunner{
	
	@Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setFullname("Test User");
        user.setRole("USER");
        userRepository.save(user);
    }

}
*/
