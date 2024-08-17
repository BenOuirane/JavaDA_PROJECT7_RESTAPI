package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.RatingNotFoundException;
import com.nnk.springboot.exceptions.RatingSaveException;
import com.nnk.springboot.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import javax.validation.Valid;


@Controller
public class RatingController {
	
      //  Inject Rating service
	
	 @Autowired
	 private RatingService ratingService;
 
    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        // find all Rating, add to model
    	List<Rating> ratings = ratingService.getAllRatings();
        model.addAttribute("ratings", ratings);
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating,Model model) {
        model.addAttribute("rating", new Rating());
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid @ModelAttribute("rating") Rating rating, BindingResult result, Model model) {
        //check data valid and save to db, after saving return Rating list
    	if (result.hasErrors()) {
            return "rating/add";
        }
    	try {
    	ratingService.saveRating(rating);
        model.addAttribute("ratings", ratingService.getAllRatings());

    	 } catch (RatingSaveException e) {
             model.addAttribute("saveError", e.getMessage());
             return "rating/add";
         }        
    	 return "redirect:/rating/list";
    }
    

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws RatingNotFoundException {
        // get Rating by Id and to model then show to the form
    	 Rating rating = ratingService.getRatingById(id);
    	 if (rating == null) {
 			throw new IllegalArgumentException("Invalid rating Id:" + id);
 		}
         model.addAttribute("rating", rating);
    	 return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) throws RatingNotFoundException {
        //  check required fields, if valid call service to update Rating and return Rating list
    	 if (result.hasErrors()) {
             return "rating/update";
         }
         ratingService.updateRating(id,rating);
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) throws RatingNotFoundException {
        // Find Rating by Id and delete the Rating, return to Rating list
        ratingService.deleteRating(id);

        return "redirect:/rating/list";
    }
    
}
