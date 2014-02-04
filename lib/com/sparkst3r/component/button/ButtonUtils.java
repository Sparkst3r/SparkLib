package com.sparkst3r.component.button;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 * ButtonUtils
 * 
 * Various common methods for AbstractButton
 * 
 * @author Josh Lockheed {Sparkst3r}
 * 
 */
public class ButtonUtils {
	
	/**
	 * Sets the icon of a button based on an ImageIcon
	 * @param button The button to set the icon
	 * @param icon The Icon of the button which is a predefined size and matches the dimensions of the button
	 */
	public static void setIcon(AbstractButton button, ImageIcon icon) {
		button.setContentAreaFilled(false);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setIcon(icon);
	}
	
	/**
	 * Sets the icon of a button based on a file path
	 * @param button The button to set the icon
	 * @param path path to an icon to be set to the button which is a predefined size and matches the dimensions of the button
	 */
	public static void setIcon(AbstractButton button, String iconPath) {
		button.setContentAreaFilled(false);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setIcon(new ImageIcon(iconPath));
	}
	
}
