package com.nnk.springboot.exceptions;

public class BidListNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BidListNotFoundException(String message) {
        super(message);
    }
}