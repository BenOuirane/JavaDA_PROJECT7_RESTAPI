package com.nnk.springboot.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.RatingNotFoundException;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;


@Service
public class RatingServiceImpl  implements RatingService{

	@Autowired
    private RatingRepository ratingRepository;

    
    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }
    
    @Override
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }
    
    @Override
    public Rating getRatingById(Integer id) throws RatingNotFoundException {
    	Optional<Rating> rating = ratingRepository.findById(id);
        if (rating.isPresent()) {
            return rating.get();
        } else {
            throw new RatingNotFoundException("Rating not found with id: " + id);
        }
    }

    @Override
    public Rating updateRating(Integer id, Rating rating) throws RatingNotFoundException {
        Rating existingRating = getRatingById(id);
        existingRating.setMoodysRating(rating.getMoodysRating());
        existingRating.setSandPRating(rating.getSandPRating());
        existingRating.setFitchRating(rating.getFitchRating());
        existingRating.setOrderNumber(rating.getOrderNumber());
        return ratingRepository.save(existingRating);
    }
    
    @Override
    public void deleteRating(Integer id) throws RatingNotFoundException {
        Rating rating = getRatingById(id);
        ratingRepository.delete(rating);
    }


}
