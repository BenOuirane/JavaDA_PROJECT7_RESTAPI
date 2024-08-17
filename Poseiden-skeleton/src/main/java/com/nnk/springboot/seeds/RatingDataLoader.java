package com.nnk.springboot.seeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Component
public class RatingDataLoader implements CommandLineRunner {

	@Autowired
    private RatingRepository ratingRepository;

    @Override
    public void run(String... args) throws Exception {
        loadRatingData();
    }

    private void loadRatingData() {
    	if(ratingRepository.count()==0) {
        Rating rating1 = new Rating(null, "Moody's AAA", "S&P AAA", "Fitch AAA", 1);
        Rating rating2 = new Rating(null, "Moody's AA", "S&P AA", "Fitch AA", 2);
        Rating rating3 = new Rating(null, "Moody's A", "S&P A", "Fitch A", 3);

        ratingRepository.save(rating1);
        ratingRepository.save(rating2);
        ratingRepository.save(rating3);
        
    	}
    }

}
