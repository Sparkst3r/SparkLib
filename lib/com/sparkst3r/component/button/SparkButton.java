package com.sparkst3r.component.button;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.sparkst3r.image.icon.InvalidIconException;

/**
 * SparkButton
 * 
 * An extension to JButton to provide certain extra functionality.
 * 
 * @author Josh Lockheed {Sparkst3r}
 * @since 11 Jun 2013 17:22:12
 *
 */
public class SparkButton extends JButton {
	private static final long serialVersionUID = -7032454843056375561L;
	
	public SparkButton(String string) {
		super(string);
	}

	/**
	 * Sets the icon of a button to an {@code ImageIcon}. This will make the button invisible and replace the button with a graphic
	 * @param icon The Icon of the button which is a predefined size and matches the dimensions of the button
	 * @return if the icon was successfully set to the icon
	 * @throws InvalidIconException if the icon is invalid, corrupt, null or unreadable this method will throw an {@code InvalidIconException}
	 */
	public boolean setIcon(ImageIcon icon) throws InvalidIconException {
		if (icon == null) {
			throw new InvalidIconException("Icon is null, please do not pass a null icon");
		}
		this.setContentAreaFilled(false);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setIcon((Icon)icon);
		if (this.getIcon() == icon) {
			return true;
		}
		return false;
	}
	
}
