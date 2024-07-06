package com.nnk.springboot.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.nnk.springboot.domain.Rating;
//import com.nnk.springboot.exceptions.RatingNotFoundException;
import com.nnk.springboot.exceptions.RatingNotFoundException;

@Service
public interface RatingService {
	
    List<Rating> getAllRatings();
    Rating saveRating(Rating rating);
	Rating getRatingById(Integer id) throws RatingNotFoundException;
    Rating updateRating(Integer id, Rating rating) throws RatingNotFoundException;
    void deleteRating(Integer id) throws RatingNotFoundException;

}
