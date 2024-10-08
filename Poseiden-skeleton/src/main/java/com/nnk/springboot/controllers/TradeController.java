package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;

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
public class TradeController {
	
    // Inject Trade service
	@Autowired
    private TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        //  find all Trade, add to model
        model.addAttribute("trades", tradeService.findAll());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        //  check data valid and save to db, after saving return Trade list
    	if (result.hasErrors()) {
            return "trade/add";
        }
        tradeService.save(trade);
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        //  get Trade by Id and to model then show to the form
    	Trade trade = tradeService.findById(id);
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        //  check required fields, if valid call service to update Trade and return Trade list
    	if (result.hasErrors()) {
            return "trade/update";
        }
        tradeService.update(trade);
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        //  Find Trade by Id and delete the Trade, return to Trade list
    	tradeService.deleteById(id);
        return "redirect:/trade/list";
    }
}
