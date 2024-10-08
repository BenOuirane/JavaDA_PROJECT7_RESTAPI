package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import javax.validation.Valid;


@Controller
public class CurveController {
    // Inject Curve Point service
	
	@Autowired
	 private CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        //  find all Curve Point, add to model
        model.addAttribute("curvePoints", curvePointService.findAll());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // check data valid and save to db, after saving return Curve list
    	if (result.hasErrors()) {
            return "curvePoint/add";
        }
    	curvePointService.save(curvePoint);
        model.addAttribute("curvePoints", curvePointService.findAll());
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // get CurvePoint by Id and to model then show to the form
    	Optional<CurvePoint> curvePoint = curvePointService.findById(id);
        if (curvePoint.isPresent()) {
            model.addAttribute("curvePoint", curvePoint.get());
            return "curvePoint/update";
        } else {
            return "redirect:/curvePoint/list";
        }
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        //  check required fields, if valid call service to update Curve and return Curve list
    	 if (result.hasErrors()) {
             return "curvePoint/update";
         }
         curvePoint.setId(id);
         curvePointService.save(curvePoint);
         model.addAttribute("curvePoints", curvePointService.findAll());
         return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        //  Find Curve by Id and delete the Curve, return to Curve list
    	 curvePointService.deleteById(id);
         model.addAttribute("curvePoints", curvePointService.findAll());
         return "redirect:/curvePoint/list";
    }
}
