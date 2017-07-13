/**
 * 
 */
package org.di.uminho.cguide.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * @author Filipe
 *
 */
public class TCPClient {

	public final static int SOCKET_PORT = 4444; // you may change this
	public final static String SERVER = "127.0.0.1"; // localhost
	public final static String FILE_TO_SEND = System.getProperty("user.dir") + "\\owl.zip";
	private String owl_filepath;
	private String description;
	
	public TCPClient (String owl_filepath, String username, String description) {
		this.owl_filepath = owl_filepath;
		this.description = "Username:" + username.toLowerCase() + "\nDescription:\n" + description;
	}

	public void run() throws IOException {
        Socket socket = null;
        String host = SERVER;
        
        AppZip a = new AppZip(owl_filepath, description);
        a.run();

        socket = new Socket(host, SOCKET_PORT);

        File file = new File(FILE_TO_SEND);
        // Get the size of the file
        long length = file.length();
        byte[] bytes = new byte[16 * 1024];
        InputStream in = new FileInputStream(file);
        OutputStream out = socket.getOutputStream();

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        socket.close();
        
        a.end();
        
        JOptionPane.showMessageDialog(null,
				"OWL file uploaded successfully to the CompGuide Development Team. Thank you for your contribution.",
				"OWL file uploaded successfully.", JOptionPane.INFORMATION_MESSAGE);
        
    }
}
