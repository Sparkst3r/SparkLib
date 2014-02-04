package com.sparkst3r;

/**
 * SparkException
 * 
 * Base generic exception throughout SparkLib.
 * 
 * @author Josh Lockheed {Sparkst3r}
 * @since 8 Jun 2013 22:20:24
 *
 */
public class SparkException extends Exception {
	private static final long serialVersionUID = -3791378951927844161L;

	/** 
	 * Throw a blank SparkException 
	 */
	public SparkException() {
		super();
	}
	
	/**
	 * Throw a SparkException with a descriptive message
	 * @param message
	 */
	public SparkException(String message) {
		super(message);
	}
}
