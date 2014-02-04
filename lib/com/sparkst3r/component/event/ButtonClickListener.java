package com.sparkst3r.component.event;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

/**
 * ButtonClickListener
 * 
 * Add button press listeners to a button
 * 
 * @author Josh Lockheed {Sparkst3r}
 * @since 23 Jun 2013 13:24:09
 *
 */
public class ButtonClickListener {

	/**
	 * Add a listener for button clicks to a button.
	 * @param frame The frame
	 * @param button The button
	 * @param functions implements IFunction. Varargs, add multiple listeners to one button
	 */
	public ButtonClickListener(final Window frame, final AbstractButton button, final IFunction... functions) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				for (IFunction function : functions) {
					function.function(frame, button, event);
				}
			}
		});
	}

}
