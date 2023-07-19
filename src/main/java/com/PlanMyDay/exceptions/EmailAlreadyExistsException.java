package com.PlanMyDay.exceptions;

public class EmailAlreadyExistsException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3128397561608800911L;

	public EmailAlreadyExistsException(String message) {
		super(message);
	}
}
