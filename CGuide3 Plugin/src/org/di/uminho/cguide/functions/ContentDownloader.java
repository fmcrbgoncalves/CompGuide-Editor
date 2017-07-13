package org.di.uminho.cguide.functions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class ContentDownloader {
	private String url; // "https://github.com/filipebravo123/CompGuide_Plugin/archive/master.zip";
	private static final int SIZE = 1024;
	private String destinationDir; // System.getProperty("user.dir") +
									// "\\CGuide_Repository";
	private String destinationFile; // System.getProperty("user.dir") +
									// "\\CGuide_Repository\\owl.zip";

	public ContentDownloader(String url, String destinationDir, String destinationFile) {
		this.url = url;
		this.destinationDir = destinationDir;
		this.destinationFile = destinationFile;
	}

	public void downloadContents() {

		URL urlObject = null;
		try {
			urlObject = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		URLConnection urlConnection = null;
		InputStream inputStream = null;
		BufferedInputStream bufferedInput = null;

		FileOutputStream outputStream = null;
		BufferedOutputStream bufferedOutput = null;

		// Create CGuide_Repository Directory if not exists
		File directory = new File(destinationDir);
		if (!directory.exists()) {
			directory.mkdir();
		}
		// Open connection and download zip file
		try {
			urlConnection = urlObject.openConnection();
			inputStream = urlConnection.getInputStream();
			bufferedInput = new BufferedInputStream(inputStream);

			outputStream = new FileOutputStream(this.destinationFile);
			bufferedOutput = new BufferedOutputStream(outputStream);

			byte[] buffer = new byte[SIZE];
			while (true) {
				int noOfBytesRead = bufferedInput.read(buffer, 0, buffer.length);
				if (noOfBytesRead == -1) {
					break;
				}
				bufferedOutput.write(buffer, 0, noOfBytesRead);
			}

			JOptionPane.showMessageDialog(null,
					"OWL file downloaded successfully.\nDownloaded files Directory: " + destinationDir,
					"OWL file downloaded successfully.", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"A problem has occured on downloading the files. Please check your internet connection.",
					"OWL file not downloaded successfully.", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			closeStreams(new InputStream[] { bufferedInput, inputStream },
					new OutputStream[] { bufferedOutput, outputStream });
		}
		System.out.println("Downloading completed");

		// Unzip zip file
		UnzipUtility unzipper = new UnzipUtility();
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss");
		Date date = new Date();

		destinationDir += "\\" + dateFormat.format(date);
		File new_directory = new File(destinationDir);
		if (!new_directory.exists()) {
			new_directory.mkdir();
		}

		System.out.println(destinationDir);
		try {
			unzipper.unzip(destinationFile, destinationDir);
		} catch (Exception ex) {
			// some errors occurred
			ex.printStackTrace();
		}

		// Remove zip file
		File zipfile = new File(destinationFile);
		try {
			zipfile.delete();
			System.out.println("Done!");
		} catch (Exception e) {
		}
	}

	private void closeStreams(InputStream[] inputStreams, OutputStream[] outputStreams) {

		try {
			for (InputStream inputStream : inputStreams) {
				if (inputStream != null) {
					inputStream.close();
				}
			}
			for (OutputStream outputStream : outputStreams) {
				if (outputStream != null) {
					outputStream.close();
				}
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
