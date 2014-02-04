package com.sparkst3r.component.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.sparkst3r.image.icon.InvalidIconException;


/**
 * ButtonImageListener
 * 
 * Icons can be specified for each state of a button.
 * Valid button states are : IDLE, HOVER and PRESS.
 * 
 * @author Josh Lockheed
 *
 */
public class ButtonImageListener {
	
	/**
	 * Adds a listener to a button to handle mouse-over and click events to change it's image.
	 * @param frame The frame
	 * @param button The button
	 * @param idleImg The image used when the button is idle
	 * @param hoverImg The image used when the button is hovered over
	 * @param clickImg The image used when the button is clicked
	 */
	public ButtonImageListener(final AbstractButton button, final ImageIcon idleImg, final ImageIcon hoverImg, final ImageIcon clickImg) {
		button.setContentAreaFilled(false);
		button.setBorder(BorderFactory.createEmptyBorder());
		
		if (idleImg != null) {
			try {
				setIcon(button, idleImg);
			} catch (InvalidIconException e) {
				e.printStackTrace();
			}
			
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseExited(MouseEvent event) {
					try {
						setIcon(button, idleImg);
					} catch (InvalidIconException e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		if (hoverImg != null) {
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent event) {
					try {
						setIcon(button, hoverImg);
					} catch (InvalidIconException e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		if (clickImg != null) {
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					try {
						setIcon(button, clickImg);
					} catch (InvalidIconException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	
	/**
	 * Sets the icon of a button to an {@code ImageIcon}. This will make the button invisible and replace the button with a graphic
	 * @param icon The Icon of the button which is a predefined size and matches the dimensions of the button
	 * @return if the icon was successfully set to the icon
	 * @throws InvalidIconException if the icon is invalid, corrupt, null or unreadable this method will throw an {@code InvalidIconException}
	 */
	public boolean setIcon(AbstractButton button, ImageIcon icon) throws InvalidIconException {
		if (icon == null) {
			throw new InvalidIconException("Icon is null, please do not pass a null icon");
		}
		button.setContentAreaFilled(false);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setIcon((Icon)icon);
		if (button.getIcon() == icon) {
			return true;
		}
		return false;
	}
	
}
