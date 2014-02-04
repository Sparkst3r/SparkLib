package com.sparkst3r;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


/**
 * Library
 * 
 * Global constants pertaining to this library
 * 
 * @author Josh Lockheed {Sparkst3r}
 * @since 3 Jun 2013 00:02:45
 *
 */
public final class Library {
	
	/** Current output mode*/
	private static OutputMode currentOutputMode;
	
	/** SparkLib version */
	public static final String VERSION = "v0.0.2";
	
	/** The current working class */
	private static Class<?> workingClass = null;

	/** File path relative to the JAR file's location */
	private static String FILEPATH;
	
	/** Screen size at program launch. Warning, this does NOT take into account resolution changes mid operation. As this would become inconsistent and inaccurate. */
	public static final Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
	
	/**
	 * Set internal console output mode. Used for debug and general monitoring of the program.
	 * @param type The debug type to set the library's output configuration to.
	 */
	public static void setOutputMode(OutputMode type) {
		Library.currentOutputMode = type;
	}
	
	/**
	 * Get the current OutputMode
	 * @return the currently set {@code OutputMode}
	 */
	public static OutputMode getOutputMode() {
		return Library.currentOutputMode;
	}
	
	/**
	 * You are required to call this method when your program is initiated.
	 * This ensures that the library is aware of class related details.
	 * @param clazz the main class of your program
	 */
	public static void setWorkingClass(Class<?> clazz) {
		Library.workingClass = clazz;
		FILEPATH = new File(Library.workingClass.getProtectionDomain().getCodeSource().getLocation().getPath()).getPath();
	}
	
	public static Class<?> getWorkingClass() {
		return Library.workingClass;
	}
	
	/**
	 * Get the running file path.
	 * @return the file path currently in use
	 */
	public static String getFilePath() {
		return FILEPATH;
	}
	
	/**
	 * Quick way to swap between LookAndFeels
	 * 0 = system
	 * 1 = metal
	 * 2 = Nimbus
	 * more will be added in time
	 * @param LAF the look and feel
	 */
	public static void setLookandFeel(int LAF) {
		
		try {
			switch(LAF) {
				case 0:
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				case 1:
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				case 2:
					UIManager.setLookAndFeel(NimbusLookAndFeel.class.getCanonicalName());
			}
		} 
		catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Can a print event be allowed to log to the console at a minimum logging level. 
	 * @param level The level to attempt to log at.
	 * @return true if it can be logged at the required minimum level.
	 */
	public static boolean canLogAtLevel(OutputMode level) {
		switch(level) {
			case MINIMAL:
				if (Library.getOutputMode() == OutputMode.MINIMAL ||
					Library.getOutputMode() == OutputMode.DEFAULT ||
					Library.getOutputMode() == OutputMode.DEBUG ||
					Library.getOutputMode() == OutputMode.EVERYTHING) {
					return true;
				}
				return false;
			case DEFAULT:
				if (Library.getOutputMode() == OutputMode.DEFAULT ||
					Library.getOutputMode() == OutputMode.DEBUG ||
					Library.getOutputMode() == OutputMode.EVERYTHING) {
					return true;
				}
				return false;
			case DEBUG:
				if (Library.getOutputMode() == OutputMode.DEBUG ||
					Library.getOutputMode() == OutputMode.EVERYTHING) {
					return true;
				}
				return false;
			case EVERYTHING:
				if (Library.getOutputMode() == OutputMode.EVERYTHING) {
					return true;
				}
				return false;
			default:
				return false;
		}
	}
	
	/**
	 * OutputMode
	 * 
	 * Valid console output modes.
	 * <p>
	 * {@code NONE = Nothing is printed to the console}
	 * </p>
	 * <p>
	 * {@code MINIMAL = The bare minimal details are included and are provided in user friendly language}
	 * </p>
	 * <p>
	 * {@code DEFAULT = Useful info is printed to the console, technical details are omitted plus all of MINIMAL}
	 * </p>
	 * <p>
	 * {@code DEBUG = Details such as state info, user input and action events plus all of DEFAULT}
	 * </p>
	 * 
	 * <p>
	 * {@code EVERYTHING = Every detail is included. Important variable states are included, technical info is also included, to prevent clutter MINIMAL is not included, DEFAULT and DEBUG however are.}
	 * </p>
	 * 
	 * 
	 * @author Josh Lockheed {Sparkst3r}
	 * @since 8 Jun 2013 22:24:39
	 *
	 */
	public enum OutputMode {
		NONE, MINIMAL, DEFAULT, DEBUG, EVERYTHING
	}	
}
