package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController
{
      
/**
 * Displays the home page.
 * Sets a session attribute if it does not already exist.
 * @param model the model to be used by the view
 * @param session the HTTP session
 * @return the name of the view to be rendered
 */
     @GetMapping("/")
     public String home(Model model, HttpSession session) {
    // Set a default session attribute if not already set
    if (session.getAttribute("welcomeMessage") == null) {
        session.setAttribute("welcomeMessage", "Welcome to the Home Page!");
    }
    // Retrieve the session attribute to add it to the model
    String welcomeMessage = (String) session.getAttribute("welcomeMessage");
    model.addAttribute("welcomeMessage", welcomeMessage);
    return "home"; // Name of the view template
}

/**
 * Redirects to the bid list page for admin users.
 * Sets an admin-specific session attribute.
 * @param model the model to be used by the view
 * @param session the HTTP session
 * @return redirect URL to the bid list page
 */
    @GetMapping("/admin/home")
    public String adminHome(Model model, HttpSession session) {
    // Set a session attribute specific to admin users
    session.setAttribute("adminAccess", true);
    // Redirect to another page
    return "redirect:/bidList/list";
     }

/**
 * Clears the session attribute for admin access.
 * @param session the HTTP session
 * @return redirect URL to the home page
 */
    @GetMapping("/clearAdminSession")
    public String clearAdminSession(HttpSession session) {
    session.removeAttribute("adminAccess");
    return "redirect:/";
    }
}