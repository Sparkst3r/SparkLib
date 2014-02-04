package com.sparkst3r.window.popup;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sparkst3r.window.SparkFrame;
import com.sparkst3r.window.popup.PopupEnum.EnumButton;
import com.sparkst3r.window.popup.PopupEnum.EnumIcon;

/**
 * SparkPopup
 * 
 * Popup box similar to JOptionPane based on SparkFrame
 * 
 * @author Josh Lockheed {Sparkst3r}
 * @since 3 Jun 2013 00:22:14
 *
 */
public class SparkPopup extends SparkFrame {
	private static final long serialVersionUID = -434022776453448393L;
	
	private SparkFrame frame;
	private JLabel alertLabel;
	private JLabel alertIcon = new JLabel();
	private JLabel[] text;
	
	/**
	 * Popup window buttons.
	 * 
	 * Values: CANCEL, OK, CLOSE, YES, NO
	 * 
	 */
	private JButton[] buttons;
	private Rectangle size;
	
	public SparkPopup(String title, String alertText, String[] message, Rectangle size) {
		this.size = size;
		this.frame = new SparkFrame();
		this.frame.setDimensions(size);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.alertLabel = new JLabel(alertText);
		
		text = new JLabel[message.length + 1];
		text[0] = alertLabel;
		
		for (int line = 0; line < message.length; line++) {
			JLabel linetext = new JLabel(message[line]);
			text[line + 1] = linetext;
			frame.addElement(linetext, new Rectangle((int) (this.size.width / 5), (this.size.height / 5) + (16 * line), message[line].length() * 12, 12));
		}

		this.frame.setTitle(title);
		this.frame.setManualLayout();
		this.frame.setResizable(false);
		this.frame.addElement(alertLabel, new Rectangle(50, 20, alertText.length() * 12, 12));
	}
	
	/**
	 * Create a SparkPopup with a title, AlertText, a message, a size and a background image.
	 * @param title The title of the window
	 * @param alertText The text to display as the alert text.
	 * @param message Add extra text to the popup describing the popup to the user.
	 * @param size The size of the window
	 * @param background a background image for the popup
	 */
	public SparkPopup(String title, String alertText, String[] message, Rectangle size, ImageIcon background) {
		this.size = size;

		
		this.frame = new SparkFrame(background);
		this.frame.setDimensions(size);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.alertLabel = new JLabel(alertText);
		
		text = new JLabel[message.length + 1];
		text[0] = alertLabel;
		
		for (int line = 0; line < message.length; line++) {
			JLabel linetext = new JLabel(message[line]);
			text[line + 1] = linetext;
			frame.addElement(linetext, new Rectangle((int) (this.size.width / 5), (this.size.height / 5) + (16 * line), message[line].length() * 12, 12));
		}

		this.frame.setTitle(title);
		this.frame.setManualLayout();
		this.frame.setResizable(false);
		this.frame.addElement(alertLabel, new Rectangle(50, 20, alertText.length() * 12, 12));
	}
	
	
	
	public void popup() {
		this.frame.setVisible(true);
	}
	
	/**
	 * Sets the alert icon of the popup window. Recommended image size is 32x32
	 * @param iconPos
	 * @param icon
	 * @return
	 */
	public SparkPopup setPopupIcon(EnumIcon iconPos, ImageIcon icon) {
		if (icon != null) {
			this.alertIcon.setIcon(icon);
			switch(iconPos) {
				case LEFT:
					frame.addElement(this.alertIcon, new Rectangle(10, 10, icon.getIconWidth(), icon.getIconHeight()));
					break;
				case RIGHT:
					frame.addElement(this.alertIcon, new Rectangle(this.size.width - 15 - icon.getIconWidth(), 10, icon.getIconWidth(), icon.getIconHeight()));
					break;
				case NO_ICON:
					break;
			}
		}

		return this;
	}
	
	/**
	 * Set buttons to this pop-up dialogue
	 * @param buttonType Button configuration
	 * @return This SparkPopup instance
	 */
	public SparkPopup setButtons(EnumButton buttonType) {
		this.buttons = new JButton[5];
		this.buttons[0] = new JButton("Cancel");
		this.buttons[1] = new JButton("Ok");
		this.buttons[2] = new JButton("Close");
		this.buttons[3] = new JButton("Yes");
		this.buttons[4] = new JButton("No");
		
		switch(buttonType) {
			case CANCEL_OK_OPTION:
				this.frame.addElement(this.buttons[0], new Rectangle(this.size.width / 3 - 52, this.size.height - 70, 100, 25));
				this.frame.addElement(this.buttons[1], new Rectangle(this.size.width / 3 + 52, this.size.height - 70, 100, 25));
				break;
			case CANCEL_OPTION:
				this.frame.addElement(this.buttons[0], new Rectangle(this.size.width / 2 - 50, this.size.height - 70, 100, 25));
				break;
			case CLOSE_OPTION:
				this.frame.addElement(this.buttons[2], new Rectangle(this.size.width / 2 - 50, this.size.height - 70, 100, 25));
				break;
			case OK_OPTION:
				this.frame.addElement(this.buttons[1], new Rectangle(this.size.width / 2 - 50, this.size.height - 70, 100, 25));
				break;
			case YES_NO_CANCEL_OPTION:
				this.frame.addElement(this.buttons[0], new Rectangle(this.size.width / 5 - 39, this.size.height - 70, 75, 25));
				this.frame.addElement(this.buttons[4], new Rectangle(this.size.width / 5 + 39, this.size.height - 70, 75, 25));
				this.frame.addElement(this.buttons[3], new Rectangle(this.size.width / 5 + 118, this.size.height - 70, 75, 25));
				break;
			case YES_NO_OPTION:
				this.frame.addElement(this.buttons[3], new Rectangle(this.size.width / 3 - 52, this.size.height - 70, 100, 25));
				this.frame.addElement(this.buttons[4], new Rectangle(this.size.width / 3 + 52, this.size.height - 70, 100, 25));
				break;
		}
		
		return this;
	}
	
	/**
	 * Gets the button based on an index in the button array
	 * @see SparkPopup#buttons Button array for values
	 * @param button The index in the array
	 * @return The button from the array. Can be null.
	 */
	public JButton getButton(int button) {
		return this.buttons[button];
	}
	
	public SparkFrame getFrame() {
		return this.frame;
	}
	
	/**
	 * Set all text to a particular colour
	 * @param colour The colour to set the text to 
	 * @return this SparkFrame instance
	 */
	public SparkFrame setTextColour(Color colour) {
		for(int line = 0; line < this.text.length; line++) {
			this.text[line].setForeground(colour);
		}
		return this;
	}
	
	/**
	 * Set a specific line of text to a colour
	 * line 0 is the alert text, message text starts at 1
	 * @param colour The colour to set the line to
	 * @param line The line number of the text to set
	 * @return this SparkFrame instance
	 */
	public SparkFrame setTextLineColour(Color colour, int line) {
		this.text[line].setForeground(colour);
		return this;
	}
}
