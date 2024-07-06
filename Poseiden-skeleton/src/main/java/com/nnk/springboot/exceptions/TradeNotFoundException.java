package com.nnk.springboot.exceptions;

public class TradeNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5369413780763705912L;

	public TradeNotFoundException(String message) {
        super(message);
    }
}
