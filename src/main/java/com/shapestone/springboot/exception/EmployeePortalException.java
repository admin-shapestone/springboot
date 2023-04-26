package com.shapestone.springboot.exception;

/**
 * This class is used throw custom exception
 * @author surya
 *
 */
public class EmployeePortalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeePortalException(String msg) {
		super(msg);
	}
}
