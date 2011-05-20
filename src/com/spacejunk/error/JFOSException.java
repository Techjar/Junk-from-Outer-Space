/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.error;

/**
 * 
 * @author Techjar
 */
public class JFOSException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    public JFOSException() {
        super();
    }

    public JFOSException(String message) {
        super(message);
    }

    public JFOSException(Throwable cause) {
        super(cause);
    }

    public JFOSException(String message, Throwable cause) {
        super(message, cause);
    }
}
