package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exceptions.BidListNotFoundException;
import com.nnk.springboot.service.BidListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class BidListController {
	
    // Inject Bid service
	@Autowired
    private BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        // call service find all bids to show to the view
        model.addAttribute("bidLists", bidListService.findAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // check data valid and save to db, after saving return bid list
    	 if (result.hasErrors()) {
             return "bidList/add";
         }
         bidListService.save(bid);
         return "redirect:/bidList/list";
      //  return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	 // Fetch the BidList by Id
        BidList bidList = bidListService.findById(id);
        // TODO: get Bid by Id and to model then show to the form
        model.addAttribute("bidList", bidList);
        return "bidList/update";
     //   return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        //  check required fields, if valid call service to update Bid and return list Bid
    	if (result.hasErrors()) {
            return "bidList/update";
        }
        bidListService.update(bidList);
        return "redirect:/bidList/list";
     //   return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
    	try {
            // Attempt to delete the BidList by ID
            bidListService.deleteById(id);
            return "redirect:/bidList/list";
        } catch (BidListNotFoundException e) {
            // Handle the case where the BidList with the given ID is not found
            model.addAttribute("error", "BidList with ID " + id + " not found.");
            return "redirect:/bidList/list";  // Optionally redirect to the list page with an error message
        }
    }
}
