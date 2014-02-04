package com.sparkst3r.window;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

/**
 * SparkFrameListener
 * 
 * Window Listener for SparkFrames
 * Used internally and should not be used. Please access through SparkFrame
 * 
 * @author Josh Lockheed {Sparkst3r}
 * @since 1 Jun 2013 20:15:32
 *
 */
public class SparkFrameListener implements WindowListener, WindowStateListener, WindowFocusListener {

	/** Parent frame to send events to. */
	private SparkFrame parent;
	
	/** Creates and binds this listener to the frame */
	protected SparkFrameListener(SparkFrame frame) {
		this.parent = frame;
		this.parent.addWindowListener(this);
		this.parent.addWindowStateListener(this);
		this.parent.addWindowFocusListener(this);
	}

	/** {@inheritDoc} */
	@Override
	public void windowGainedFocus(WindowEvent e) {
		parent.windowGainedFocus(e);
	}

	/** {@inheritDoc} */
	@Override
	public void windowLostFocus(WindowEvent e) {
		parent.windowLostFocus(e);
	}

	/** {@inheritDoc} */
	@Override
	public void windowStateChanged(WindowEvent e) {
		parent.windowStateChanged(e);
	}

	/** {@inheritDoc} */
	@Override
	public void windowOpened(WindowEvent e) {
		parent.windowOpened(e);
	}

	/** {@inheritDoc} */
	@Override
	public void windowClosing(WindowEvent e) {
		parent.windowClosing(e);
	}

	/** {@inheritDoc} */
	@Override
	public void windowClosed(WindowEvent e) {
		parent.windowClosed(e);
	}

	/** {@inheritDoc} */
	@Override
	public void windowIconified(WindowEvent e) {
		parent.windowIconified(e);
	}

	/** {@inheritDoc} */
	@Override
	public void windowDeiconified(WindowEvent e) {
		parent.windowDeiconified(e);
	}

	/** {@inheritDoc} */
	@Override
	public void windowActivated(WindowEvent e) {
		parent.windowActivated(e);
	}

	/** {@inheritDoc} */
	@Override
	public void windowDeactivated(WindowEvent e) {
		parent.windowDeactivated(e);
	}
	
}
