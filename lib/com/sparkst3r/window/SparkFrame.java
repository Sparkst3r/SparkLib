package com.sparkst3r.window;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * SparkFrame
 * 
 * Extension of JFrame's functionality.
 * SparkFrames expose the WindowAdapter events directly in the frame.
 * @see java.awt.event.WindowAdapter WindowAdapter for method docs
 * 
 * @author Josh Lockheed {Sparkst3r}
 * @since 1 Jun 2013 19:30:48
 *
 */
public class SparkFrame extends JFrame {
	private static final long serialVersionUID = -1785528114781922169L;
	
	public SparkFrame() {
		
	}
	
	public SparkFrame(ImageIcon background) {
		this.setBackground(background);
	}
	
	
	/**
	 * Set the dimensions of this window.
	 * @param dimensions A rectangle specifying the dimensions of the window
	 */
	public void setDimensions(Rectangle dimensions) {
		this.setBounds(dimensions.x - (dimensions.width / 2), dimensions.y - (dimensions.height / 2), dimensions.width, dimensions.height);
	}
	
	/**
	 * Sets the icon of this frame to an ImageIcon.
	 * @param icon The ImageIcon that the frame's icon will be set to
	 * @return This SparkFrame instance
	 */
	public SparkFrame setIcon(ImageIcon icon) {
		super.setIconImage(icon.getImage());
		return this;
	}
	
	/** 
	 * Adds a Component to a SparkFrame with a defined size and position.
	 * @param frame The JFrame to add to
	 * @param element The component to add
	 * @param box The dimensions of the element to add
	 * @return This SparkFrame instance
	 */
	public SparkFrame addElement(Component element, Rectangle box) {
		element.setSize(box.width, box.height);
		element.setLocation(box.x, box.y);
		this.add(element);
		return this;
	}
	
	
	/**
	 * Set the background image of this SparkFrame
	 * @param icon The ImageIcon to set as the background of this frame
	 */
	public void setBackground(ImageIcon icon) {
		JPanel pane = new ImagePanel(icon);
		if (icon != null) {
			this.setContentPane(pane);	
		}
	}
	
	public SparkFrame setManualLayout() {
		this.setLayout(null);
		return this;
	}
	
	/**
	 * When called, the window listener is registered to this window. Otherwise the respective methods are useless. This is optional to prevent unnecessary lag.
	 * @return This SparkFrame instance
	 */
	public SparkFrame useWindowListener() {
		new SparkFrameListener(this);
		return this;
	}
	
	/**							*/
	/** Window Adapter Methods  */
	/**							*/
    public void windowOpened(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowStateChanged(WindowEvent e) {}
    public void windowGainedFocus(WindowEvent e) {}
    public void windowLostFocus(WindowEvent e) {}
}
