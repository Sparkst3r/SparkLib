package com.sparkst3r.component.event;

import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

/**
 * IFunction
 * 
 * Function template for listener events to call.
 * Implemented by listener invoked functions.
 * 
 * @author Josh Lockheed
 *
 */
public interface IFunction {
	
	/**
	 * Function method
	 * @param frame The frame
	 * @param button The button that invoked this function
	 * @param event ActionEvent that describes this action
	 * @return Whether the function was successful.
	 */
	public boolean function(Window frame, AbstractButton button, ActionEvent event);
}
