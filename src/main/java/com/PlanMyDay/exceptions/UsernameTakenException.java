package com.PlanMyDay.exceptions;

public class UsernameTakenException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7365603170919768515L;

	public UsernameTakenException(String message) {
		super(message);
	}
	
}
