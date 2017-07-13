/**
 * 
 */
package org.di.uminho.cguide.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Filipe
 *
 */
public class AppZip {
	private List<String> fileList;
	private String owl_filepath;
	private String description;
	private TextFile textFile;
	private static final String OUTPUT_ZIP_FILE = System.getProperty("user.dir") + "\\owl.zip";
	private static final String SOURCE_FOLDER = System.getProperty("user.dir");

	public AppZip(String owl_filepath, String description) {
		this.fileList = new ArrayList<String>();
		this.owl_filepath = owl_filepath;
		this.description = description;
	}

	public void run() {
		this.textFile = new TextFile(this.description);
		File owlFile = new File(this.owl_filepath);
		File logfile = textFile.writeLogfile(this.description);
		
		fileList.add(generateZipEntry(logfile.getAbsoluteFile().toString()));
		fileList.add(generateZipEntry(owlFile.getAbsoluteFile().toString()));
		zipIt(OUTPUT_ZIP_FILE);
	}
	
	public void end() {
		this.textFile.deleteLogfile();
		File a = new File(this.OUTPUT_ZIP_FILE);
		if(a.exists()) {
			a.delete();
		}
	}

	/**
	 * Zip it
	 * 
	 * @param zipFile
	 *            output ZIP file location
	 */
	public void zipIt(String zipFile) {

		byte[] buffer = new byte[1024];

		try {

			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);

			System.out.println("Output to Zip : " + zipFile);

			for (String file : this.fileList) {

				System.out.println("File Added : " + file);
				ZipEntry ze = new ZipEntry(file);
				zos.putNextEntry(ze);

				FileInputStream in = new FileInputStream(SOURCE_FOLDER + File.separator + file);

				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}

				in.close();
			}

			zos.closeEntry();
			// remember close it
			zos.close();

			System.out.println("Done");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Traverse a directory and get all files, and add the file into fileList
	 * 
	 * @param node
	 *            file or directory
	 */
	public void generateFileList(File node) {

		// add file only
		if (node.isFile()) {
			fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
		}

		if (node.isDirectory()) {
			String[] subNote = node.list();
			for (String filename : subNote) {
				generateFileList(new File(node, filename));
			}
		}

	}

	/**
	 * Format the file path for zip
	 * 
	 * @param file
	 *            file path
	 * @return Formatted file path
	 */
	private String generateZipEntry(String file) {
		return file.substring(SOURCE_FOLDER.length() + 1, file.length());
	}
}
