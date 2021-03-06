package com.dabdm.travelplan;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPHTTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.commons.net.io.CopyStreamEvent;
import org.apache.commons.net.io.CopyStreamListener;
import org.apache.commons.net.util.TrustManagerUtils;
/**
 * 
 * This class is a helper that provides functionalities to get and store a file 
 * on the TravelPlan server
 *
 */
public class FtpHelper {
	private final static String serverAddress = "ftp.alionka.org";
	private final static String userName = "adm@alionka.org";
	private final static String password = "20adm13";

	private FTPClient ftpClient;

	/**
	 *  This method connect the client to the ftp server
	 */
	public void connect() {
		ftpClient = new FTPClient();
		try {
			ftpClient.connect(serverAddress);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // Using port no=21
		catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ftpClient.login(userName, password);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Only passive mode is allowed in android
		ftpClient.enterLocalPassiveMode();

	}
	/**
	 * This method put a file given in parameter into the ftp server.
	 */
	public boolean put(File file) {
	    boolean b = false;
		BufferedInputStream buffIn = null;
		try {
			buffIn = new BufferedInputStream(new FileInputStream(
					file.getAbsolutePath()));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			ftpClient.storeFile(file.getName(), buffIn);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			buffIn.close();
			return b;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * This method get a file, which name is given in parameter, from the server.
	 * Then it stores it in the outputstream given in parameter.
	 * @param fileName : name of the file to get
	 * @param out : OutputStream in wich the file will be stored
	 */
	public boolean get(String fileName, OutputStream out) {
		InputStream inStream;
		try {
			inStream = ftpClient.retrieveFileStream(fileName);
			 
			int read = 0;
			byte[] bytes = new byte[1024];
		 
			while ((read = inStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
		 
			inStream.close();
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * This method close the connection between the client and the server
	 */
	public void close() {
		try {
			ftpClient.logout();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ftpClient.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
