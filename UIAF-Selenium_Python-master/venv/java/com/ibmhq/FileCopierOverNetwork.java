package com.ibmhq;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;

public class FileCopierOverNetwork {

	static Logger log = LogManager.getLogger(FileCopierOverNetwork.class.getName());

	final static SftpProgressMonitor monitor = new SftpProgressMonitor() {
		public void init(final int op, final String source, final String target, final long max) {
			System.out.println("sftp start uploading file from:" + source + " to:" + target);
		}

		public boolean count(final long count) {
			System.out.println("sftp sending bytes: " + count);
			return true;
		}

		public void end() {
			System.out.println("sftp uploading is done.");
		}
	};

	String strstatus = null;

	public String putFile(String FTPDetails) throws JSchException, SftpException {
		try {
			String Details = "";
			if (FTPDetails.contains("#")) {

				Details = FTPDetails;
				System.out.println(Details);

				String hostname = Details.split("#")[0];
				String portInitial = Details.split("#")[1];
				int port = Integer.parseInt(portInitial);
				String username = Details.split("#")[2];
				String password = Details.split("#")[3];
				String copyFrom = Details.split("#")[4];
				String copyTo = Details.split("#")[5];

				System.out.println("Initiate sending file to Linux Server...");
				JSch jsch = new JSch();
				Session session = null;
				System.out.println("Trying to connect.....");
				session = jsch.getSession(username, hostname, port);
				// session.setConfig("StrictHostKeyChecking", "no");
				session.setPassword(password);
				session.connect();
				System.out.println("is server connected? " + session.isConnected());

				Channel channel = session.openChannel("sftp");
				channel.connect();
				ChannelSftp sftpChannel = (ChannelSftp) channel;
				System.out.println("Server's home directory: " + sftpChannel.getHome());
				try {
					sftpChannel.put(copyFrom, copyTo, monitor, ChannelSftp.OVERWRITE);
					sftpChannel.chmod(0777, copyTo);
					System.out.println("Permission has been changed to 0777");
				} catch (SftpException e) {
					System.out.println("file was not found: " + copyFrom);
				}
				sftpChannel.exit();
				session.disconnect();
				System.out.println("Finished sending file to Linux Server...");
			} else {
				System.out.println("Informations are in incorrect format");
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
			System.out.println(strstatus);
		}
		return strstatus;
	}

	/*
	 * public static void main(String args[]) { try { FileCopierOverNetwork
	 * FCON=new FileCopierOverNetwork();
	 * 
	 * FCON.
	 * putFile("158.98.37.18#22#chamanc#G0ldsp0t#C:\\Users\\IBM_ADMIN\\Desktop\\POC Data File_Chaitra_Test.xml#/localCCB/CCBST2/data_in/mdms2/comm/POC Data File_Chaitra_Test.xml"
	 * ); } catch (JSchException e1) { e1.printStackTrace(); } catch
	 * (SftpException e1) { e1.printStackTrace(); }
	 * System.out.println("Done !!"); }
	 */
}