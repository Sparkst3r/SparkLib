package com.sparkst3r.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZIPUtils {
	
	/** 1kB byte buffer */
    private static final byte[] BUFFER = new byte[1024];

    /**
     * Copies the bytes to the output stream using the byte buffer to improve speed and reduce chances of byte corruption
     * @param input InputStream
     * @param output OutputStream
     * @throws IOException Throws an IOException if the bytes could not be written to the output stream
     */
    private static void copy(InputStream input, OutputStream output) throws IOException {
        int bytesRead;
        while ((bytesRead = input.read(BUFFER))!= -1) {
            output.write(BUFFER, 0, bytesRead);
        }
    }
    
	/**
	 * Appends a file to a ZIP file. If the file doesn't exist then it will create a new file. 
	 * @param zip
	 * @param entry
	 * @throws IOException Throws an IOException if the file could not be written to. Or a SecurityException was thrown
	 */
	public static void appendEntry(File zip, File entry) throws IOException {
		ZipOutputStream moddedZip = new ZipOutputStream(new FileOutputStream(zip.getPath() + "new"));
		ArrayList<ZipEntry> entryList = new ArrayList<ZipEntry>();
		
		if (zip.exists()) {
			
			ZipFile zipFile = new ZipFile(zip.getPath());
			
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry e = entries.nextElement();
				entryList.add(e);
				moddedZip.putNextEntry(new ZipEntry(e.getName()));
				if (!e.isDirectory()) {
					copy(zipFile.getInputStream(e), moddedZip);
				}
				moddedZip.closeEntry();
			}	
			zipFile.close();
		}
		try {
			String name = entry.getName();
			ZipEntry zipEntry = new ZipEntry(name);
			boolean exists = false;
			for (ZipEntry thisEntry : entryList) {
				if (thisEntry.getName().equals(zipEntry.getName())) {
					System.out.println(thisEntry.getName() + zipEntry.getName());
					exists = true;
				}
			}
			if (!exists) {
				moddedZip.putNextEntry(zipEntry);
				
				FileInputStream fis = null;
				fis = new FileInputStream(entry);
				byte[] byteBuffer = new byte[1024];
				int bytesRead = -1;
				while ((bytesRead = fis.read(byteBuffer)) != -1) {
					moddedZip.write(byteBuffer, 0, bytesRead);
				}
				fis.close();
				moddedZip.closeEntry();
			}
		} 
		finally {
			try {
				moddedZip.close();
			} 
			catch (Exception e) {}
		}
		
		String path = zip.getPath();
		zip.delete();
		File newFile = new File(path);
		File oldFile = new File(path + "new");
		oldFile.renameTo(newFile);
	}
}
