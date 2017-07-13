package org.di.uminho.cguide.functions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class TextFile {
	
	private String text;
	private File logFile;
	
	public TextFile(String text) {
		this.text = text;
		this.logFile = null;
	}

	/**
	 * Writes a txt file (logfile.txt) with the text argument received
	 * 
	 * @param text
	 */

	public File writeLogfile(String text) {
		BufferedWriter writer = null;
		try {
			// create a temporary file
			this.logFile = new File("logfile.txt");

			// This will output the full path where the file will be written
			// to...
			//System.out.println(this.logFile.getCanonicalPath());

			writer = new BufferedWriter(new FileWriter(this.logFile));
			writer.write(text);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}
		return logFile;
	}
	
	/**
	 * Deletes a file (logfile) with the file argument received
	 * 
	 * @param logfile
	 */
	
	public void deleteLogfile() {
		Path path = this.logFile.toPath();
		try {
		    Files.delete(path);
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", path);
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", path);
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
	}
	
}
