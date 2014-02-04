package com.sparkst3r.image.icon;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sparkst3r.Library;

/**
 * IconRegister
 * 
 * Registers an ImageIcon to a global HashMap and retrieve via a key
 * 
 * @author Josh Lockheed
 *
 */
public class IconRegister {
	
	public static Map<String, ImageIcon> keyToIconMap = new HashMap<String, ImageIcon>(); 
	public static Map<String, String> keyToPathMap = new HashMap<String, String>(); 
	
	/**
	 * Get icon from registered key
	 * Returns a blank ImageIcon if the icon was not found
	 * @param key The key registered to the icon
	 * @return the icon pulled from the HashMap
	 */
	public static ImageIcon getIcon(String key) {
		ImageIcon icon = keyToIconMap.get(key);
		if (icon != null) {
			return icon;
		}
		else if (!keyToIconMap.containsKey(key)) {
			System.out.println("Could not retrieve image identified by the key : " + "'" + key + "'" + ".");
		}
		else {
			System.out.println("The icon identified by the key : " + "'" + key + "'" + " is invalid.");
			return new ImageIcon();
		}
		return new ImageIcon();

	}
	
	
	/**
	 * Get file path from registered key
	 * @param key The key
	 * @return the filepath
	 */
	public static String getPath(String key) {
		String path = keyToPathMap.get(key);
		if (path != null) {
			return path;
		}
		else {
			System.out.println("Could not retrieve image file path identified by the key :" + "'" + key + "'" + ".");
			return null;
		}
	}
	
	/**
	 * Registers an icon with a key.
	 * @param key The key
	 * @param path The path to the icon
	 * @return If the icon was added successfully. 
	 */
	public static boolean registerIcon(String key, String path) {
		InputStream image = Library.getWorkingClass().getResourceAsStream(path);
		if (key != null && path != null && !key.equals("") && !path.equals("")){
			try {
				ImageIcon icon = new ImageIcon(ImageIO.read(image), path);
				if (icon != null) {
					keyToIconMap.put(key, icon);
					keyToPathMap.put(key, path);
					return true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		System.out.println("Failed to register an icon with the path: " + path + " under the key: " + key);
		return false;
	}
	
}
