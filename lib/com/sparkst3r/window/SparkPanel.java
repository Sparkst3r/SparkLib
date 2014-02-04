package com.sparkst3r.window;

import java.awt.Component;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class SparkPanel extends JPanel {
	private static final long serialVersionUID = -6546118032447736277L;

	/** 
	 * Adds a Component to a SparkPanel with a defined size and position.
	 * @param element The component to add
	 * @param box The dimensions of the element to add
	 * @return This SparkPanel instance
	 */
	public SparkPanel addElement(Component element, Rectangle box) {
		element.setSize(box.width, box.height);
		element.setLocation(box.x, box.y);
		this.add(element);
		return this;
	}
	
}
