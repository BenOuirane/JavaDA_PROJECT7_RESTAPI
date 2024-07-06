package com.nnk.springboot.exceptions;

public class RuleNameNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RuleNameNotFoundException(String message) {
        super(message);
    }
}
