/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solxiom.signincontrol.connection.exception;

/**
 *
 * @author soleikav
 */
public class NullAccessGrantException extends Exception {

	private static final long serialVersionUID = 1L;
	private final static String default_str = "Facebook AccessGrant object cannot be Null  ";

	public NullAccessGrantException() {
		super(default_str);
	}

	public NullAccessGrantException(String message) {
		super(default_str + "[" + message + " ]");
	}

	public NullAccessGrantException(String message, Throwable cause) {
		super(default_str + "[" + message + " ]", cause);
	}

	public NullAccessGrantException(Throwable cause) {
		super(default_str, cause);
	}
}
