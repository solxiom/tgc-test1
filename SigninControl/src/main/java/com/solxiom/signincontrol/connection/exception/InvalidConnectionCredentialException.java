/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solxiom.signincontrol.connection.exception;

/**
 *
 * @author Kavan Soleimanbeigi
 */

public class InvalidConnectionCredentialException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String default_str = "Check your ConnectionCredential fields ";

	public InvalidConnectionCredentialException() {
		super(default_str);
	}

	public InvalidConnectionCredentialException(String message) {
		super(default_str + "[" + message + " ]");
	}

	public InvalidConnectionCredentialException(String message, Throwable cause) {
		super(default_str + "[" + message + " ]", cause);
	}

	public InvalidConnectionCredentialException(Throwable cause) {
		super(default_str, cause);
	}
}

