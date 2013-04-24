package com.tedwon.jsch.sftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.util.Vector;

/**
 * Sftp Sample Application.
 *
 * @author <a href="iamtedwon@gmail.com">Ted Won</a>
 * @version 0.1.0
 */
public class SimpleSftpApp {

    public static void main(String[] args) throws Exception {

        String password = args[0];

        JSch jsch = new JSch();

        String host = "localhost";
        String user = "user";
        int port = 22;

        Session session = jsch.getSession(user, host, port);
        session.setPassword(password);

        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();

        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftp = (ChannelSftp) channel;

        // ls
        Vector ls = sftp.ls(".");
        System.out.println(ls);

        // download
        String srcFile = "/tmp/test.txt";
        String dstFile = "/tmp/dest.txt";
        sftp.get(srcFile, dstFile);

        sftp.disconnect();
        session.disconnect();
    }
}
