package com.nnk.springboot.exceptions;

public class RatingSaveException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RatingSaveException(String message) {
        super(message);
    }
}