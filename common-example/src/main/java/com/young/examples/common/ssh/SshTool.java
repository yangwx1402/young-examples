package com.young.examples.common.ssh;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by dell on 2016/4/22.
 */
public class SshTool {

    private static Log logger = LogFactory.getLog(SshTool.class);

    public static int execCmd(String user, String passwd, String host,String port,String ssh_log_src, String command) throws Exception {
        StringBuffer sb = new StringBuffer("\n");
        BufferedReader reader = null;
        int exitStatus = 0;
        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, Integer.parseInt(port));
        logger.info("session-----------"+session);
        session.setPassword(passwd);
        JSch.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        logger.info("session-conn----------"+session);
        Channel channel = session.openChannel("exec");
        logger.info("channel-----------"+channel);
        ((ChannelExec) channel).setCommand(command);

        logger.info("command-----------"+command);
        try {
            channel.connect();
            logger.info("channel-----conn------"+channel);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));
            String buf = null;
            while ((buf = reader.readLine()) != null) {
                sb.append(buf).append("\n");
            }
            while (true) {
                if (!channel.isClosed()) {
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                exitStatus = channel.getExitStatus();
                if (exitStatus != 0) {
                    System.out.println("existStatus=" + exitStatus);
                }
                System.out.println(sb.toString());
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            channel.disconnect();
            session.disconnect();
        }
        return exitStatus;
    }

    public static void main(String[] args) throws Exception {
        SshTool.execCmd("root","111111","10.16.124.3","22","","ls -ltr ");
    }

}
