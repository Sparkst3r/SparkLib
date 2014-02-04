package com.sparkst3r.window;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * ImagePanel
 * 
 * JPanel capable of having a background image drawn to it.
 *
 * @author Josh Lockheed {Sparkst3r}
 * @since 2 Jun 2013 21:25:43 v0.0.2
 *
 */
public class ImagePanel extends SparkPanel {
	private static final long serialVersionUID = 7291354558323888296L;
	
	/** Background image */
    private Image bg;
    
    /**
     * Constructor takes an ImageIcon to be used as the panel background.
     * @param icon
     */
    public ImagePanel(ImageIcon icon) {
    	bg = icon.getImage();
    }
    
    /**
     * Constructor takes an Image to be used as the panel background.
     * @param image
     */
    public ImagePanel(Image image) {
    	bg = image;
    }
    
    /**
     * @return The image of this panel.
     */
    public Image getImage() {
    	return bg;
    }
    
    /**
     * @{@inheritDoc}
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(this.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
	}
}