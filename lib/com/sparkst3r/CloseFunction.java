package com.sparkst3r;

import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;
import javax.swing.JFrame;

import com.sparkst3r.component.event.IFunction;

/**
 * CloseFunction
 * 
 * Closes a window when the button is pressed
 * 
 * @author Josh Lockheed {Sparkst3r}
 *
 */
public class CloseFunction implements IFunction {

	/**
	 * Function method
	 */
	@Override
	public boolean function(Window frame, AbstractButton button, ActionEvent event) {
		((JFrame)frame).dispose();
		return false;
	}

	

}
