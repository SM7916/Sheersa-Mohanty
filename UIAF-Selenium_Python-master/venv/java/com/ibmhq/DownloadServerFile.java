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

public class DownloadServerFile {

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

	public String getFile(String FTPDetails) throws JSchException, SftpException {
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

				System.out.println("Initiate getting file from Linux Server...");
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
				try {
					System.out.println(sftpChannel.getHome());
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				try {

					sftpChannel.get(copyFrom, copyTo, monitor, ChannelSftp.OVERWRITE);
				} catch (SftpException e) {
					System.out.println("file was not found: " + copyFrom);
				}

				sftpChannel.exit();
				session.disconnect();
				System.out.println("Finished getting file from Linux Server...");

			} else {
				System.out.println("Informations are in incorrect format");
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
			System.out.println(strstatus);
		}
		System.out.println("Done !!");
		return strstatus;
	}

}