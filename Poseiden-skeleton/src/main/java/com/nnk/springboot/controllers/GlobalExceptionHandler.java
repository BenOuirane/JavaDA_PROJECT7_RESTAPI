package com.nnk.springboot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ConstraintViolationException.class)
    public ModelAndView handleValidationException(ConstraintViolationException ex) {
        logger.error("Validation error: {}", ex.getMessage(), ex);
        ModelAndView mav = new ModelAndView("error/validation-error");
        mav.addObject("message", "Validation failed: " + ex.getMessage());
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralException(Exception ex) {
        logger.error("General error: {}", ex.getMessage(), ex);
        ModelAndView mav = new ModelAndView("error/500");
        mav.addObject("message", "An unexpected error occurred. Please try again later.");
        return mav;
    }
}