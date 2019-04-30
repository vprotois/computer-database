package com.excilys.exception;

public class InvalidUserException extends Exception{

	private static final long serialVersionUID = 4125614959848970692L;

	public InvalidUserException(String string) {
		super(string);
	}
}
