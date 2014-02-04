package com.sparkst3r.console;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sparkst3r.Library;
import com.sparkst3r.Library.OutputMode;
import com.sparkst3r.window.SparkFrame;

/**
 * Console
 * 
 * Customisable console window. 
 * Designed as an in program system console to display information to the user or to be a debugging tool to assist developers in finding and fixing issues
 * The console extends {@code SparkFrame} for easy modification to the console window.
 * @author Josh Lockheed {Sparkst3r}
 * @since 9 Jun 2013 19:06:23
 *
 */
public class Console extends SparkFrame {
	private static final long serialVersionUID = -7509368142118397222L;

	/** Logging text area */
	private JTextArea textArea;
	
	/** Thread to monitor general logging*/
	private Thread readerLog;
	
	/** Thread to monitor errors*/
	private Thread readerErr;
	
	/** Input stream for general logging */
	private final PipedInputStream inputLog = new PipedInputStream();
	
	/** Input stream for errors */
	private final PipedInputStream inputErr = new PipedInputStream();
	
	/** Output stream for logging to the textbox */
	private PipedOutputStream outputLog = null;
	
	/** Output stream for errors to be printed to the textbox */
	private PipedOutputStream outputErr = null;

	/** Default log PrintStream to the default console*/
	private final PrintStream oldLog = System.out;
	
	/** Default error PrintStream to the default console*/
	private final PrintStream oldErr = System.err;

	/** If this console tracking the Java output streams */
	private boolean tracking;
	
	/** Thread monitor to listen to the logger input */
	private LoggerMonitor loggerMonitor = new LoggerMonitor();
	
	
	/**
	 * Creates a new console with a window title. The default size of the window will be 300x300px, centred in the screen. And the console output will be in-set 30px in all directions. Frame size is fixed to prevent graphical issues
	 * @param programName The name of the program the console is to be used for. The title is constructed as "$programName$'s Console"
	 */
	public Console(String programName) {
		this.constructConsole(programName, new Rectangle(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 150, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 150, 300, 300), new Rectangle(30, 30, 270, 270), null);
	}
	
	/**
	 * Creates a new console with a title and a custom window size. The console output will be 30px in-set in all directions
	 * @param programName The name of the program the console is to be used for. The title is constructed as "$programName$'s Console"
	 * @param dimensions The dimensions of the console specified by a {@code Rectangle}
	 */
	public Console(String programName, Rectangle dimensions) {
		this.constructConsole(programName, dimensions, new Rectangle(30, 30, dimensions.width - 30, dimensions.height - 30), null);
	}
	
	/**
	 * Creates a new console with a title, a custom window size and a specified console output box size.
	 * @param programName The name of the program the console is to be used for. The title is constructed as "$programName$'s Console"
	 * @param dimensions The dimensions of the console specified by a {@code Rectangle}
	 * @param consoleDimensions The dimensions of the console output relative to (0, 0) in the console window
	 */
	public Console(String programName, Rectangle dimensions, Rectangle consoleDimensions) {
		this.constructConsole(programName, dimensions, consoleDimensions, null);
	}
	
	/**
	 * Creates a new console with a title, a custom window size and a specified console output box size.
	 * @param programName The name of the program the console is to be used for. The title is constructed as "$programName$'s Console"
	 * @param dimensions The dimensions of the console specified by a {@code Rectangle}
	 * @param consoleDimensions THe dimensions of the console output relative to (0, 0) in the console window
	 * @param backgroundImage The background to be applied to the window
	 */
	public Console(String programName, Rectangle dimensions, Rectangle consoleDimensions, ImageIcon backgroundImage) {
		this.constructConsole(programName, dimensions, consoleDimensions, backgroundImage);
	}
	
	/**
	 * Internal setup method for this console
	 * @param programName The program name passed from a constructor
	 * @param dimensions The dimensions of the window passed from a constructor
	 * @param consoleDimensions The console box dimensions passed from a constructor
	 * @param backgroundImage The background image passed by the constructor
	 */
	private void constructConsole(String programName, Rectangle dimensions, Rectangle consoleDimensions, ImageIcon backgroundImage) {
		this.setDimensions(dimensions);
		
		if (programName.endsWith("s")) {
			this.setTitle(programName + "' Console");
		}
		else {
			this.setTitle(programName + "'s Console");
		}

		
		try {
			outputLog = new PipedOutputStream(this.inputLog);
			outputErr = new PipedOutputStream(this.inputErr);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane pane = new JScrollPane(textArea);
		
		
		if (backgroundImage != null) {
			this.setBackground(backgroundImage);
		}
		this.addElement(pane, consoleDimensions);
		this.setManualLayout();
		this.setResizable(false);
		this.setTracking(true);
		
		if (programName.endsWith("s")) {
			System.out.println(programName + "' Console has been initialised");
		}
		else {
			System.out.println(programName + "'s Console has been initialised");
		}
	}

	public void setTracking(boolean track) {
		if (track) {
			this.startThreads();
			this.redirect(new PrintStream(outputLog, true), new PrintStream(outputErr, true));
			this.loggerMonitor.run();
			this.tracking = true;
			if (Library.canLogAtLevel(OutputMode.EVERYTHING)) {
				System.out.println("Console is now redirecting");
			}
		}
		else {
			System.setOut(this.oldLog);
			System.setErr(this.oldErr);
			this.tracking = false;
			if (Library.canLogAtLevel(OutputMode.EVERYTHING)) {
				System.out.println("Console has now stopped redirecting");
			}
		}
	}
	
	public boolean isTracking() {
		return this.tracking;
	}
	
	/**
	 * Redirects console out to the streams passed
	 * @param logStream The logging stream for general use
	 * @param errStream The error stream to print errors to.
	 * @return if it was redirected
	 */
	private boolean redirect(PrintStream logStream, PrintStream errStream) {
		try {
			System.setOut(logStream);
			System.setErr(errStream);
			return true;
		}
		catch (Exception e) {
			textArea.append("Couldn't redirect to the custom PrintStream \n");
			return false;
		}	
	}
	
	/** This console will remain in existence after the window is closed */
	public synchronized void windowClosed(WindowEvent evt) {}
	
	/** The window is hidden when the user attempts to close the window */
	public synchronized void windowClosing(WindowEvent evt) {
		this.setVisible(false);
	}
	
	/**
	 * Reads from an InputStream
	 * @param stream The InputStream to read from
	 * @return the string that was read from the stream
	 * @throws IOException throws an IOException if the stream could not be read
	 */
	private synchronized String readLine(PipedInputStream stream) throws IOException {
		String input = "";
		do {
			int available = stream.available();
			if (available == 0)
				break;
			
			byte bytes[] = new byte[available];
			stream.read(bytes);
			input += new String(bytes, 0, bytes.length);														
		}
		while(!input.endsWith("\n") && !input.endsWith("\r\n"));
			return input;
	}
	
	/**
	 * Start the monitoring threads
	 */
	private void startThreads() {
		readerLog = new Thread(this.loggerMonitor);
		readerLog.setDaemon(true);	
		readerLog.start();	
		readerErr = new Thread(this.loggerMonitor);	
		readerErr.setDaemon(true);	
		readerErr.start();
	}

	/** Get the current text of the console text box 
	 * @return a new String containing the text of the console
	 */
	public String getLog() {
		return this.textArea.getText();
	}
	
	/**
	 * Thread class to monitor the loggers 
	 */
	public class LoggerMonitor implements Runnable {
		
		/** Runs every tick and prints the reader contents to the box */
		public synchronized void run() {
			try {			
				while (Thread.currentThread() == readerLog) {
					try { 
						this.wait(100);
					}
					catch(Exception e) {}
					
					if (inputLog.available() != 0) {
						textArea.append(readLine(inputLog));
					}
				}
				while (Thread.currentThread() == readerErr) {
					try {
						this.wait(100);
					}
					catch(Exception ie) {}
					if (inputErr.available() != 0) {
						textArea.append(readLine(inputErr));
					}
				}
			} 
			catch (IOException e) {
				textArea.append("Internal Error" + e);
			}
		}
	}
	
}
