package com.sparkst3r.image.icon;

import com.sparkst3r.SparkException;

/**
 * NoSuchIconException
 * 
 * Thrown if an ImageIcon is invalid, can't be returned or is non-existent.
 * 
 * @author Josh Lockheed {Sparkst3r}
 * @since 8 Jun 2013 22:07:15
 *
 */
public class InvalidIconException extends SparkException {
	private static final long serialVersionUID = 7911349139124795127L;

	/**
	 * Throw a blank NoSuchIconException
	 */
	public InvalidIconException() {
		super();
	}
	
	/**
	 * Throw a NoSuchIconException with a descriptive message
	 * @param message
	 */
	public InvalidIconException(String message) {
		super(message);
	}
}
