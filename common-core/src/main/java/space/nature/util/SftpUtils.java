/*
 * Copyright (c) 2019, LZx
 */

package space.nature.util;

import com.jcraft.jsch.*;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

/**
 * SFTP工具类
 */
public abstract class SftpUtils {

    private static final int DEFAULT_CONNECT_TIMEOUT_MS = 1000;

    /**
     * 创建会话，<strong style="color:red;">不再使用后必须关闭</strong>
     *
     * @param user
     * @return
     * @throws JSchException
     */
    public static Session session(UserDetail user) throws JSchException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(user.username, user.host, user.port);
        session.setUserInfo(user);
        return session;
    }

    /**
     * 上传文件到远程服务器
     *
     * @param session 会话
     * @param src     待上传文件
     * @param remote  上传目的地文件夹
     */
    public static void uploadFile(Session session, String src, String remote) throws JSchException, SftpException {
        if (!session.isConnected()) {
            session.connect(DEFAULT_CONNECT_TIMEOUT_MS);
        }
        ChannelSftp channelSftp = channelSftp(session, DEFAULT_CONNECT_TIMEOUT_MS);
        channelSftp.setFilenameEncoding("UTF-8");
        try {
            channelSftp.put(src, remote);
        } finally {
            channelSftp.disconnect();
        }
    }

    /**
     * @param session   会话
     * @param remoteDir 远程目标文件夹
     * @return
     * @throws JSchException
     * @throws SftpException
     */
    public static Vector<ChannelSftp.LsEntry> ls(Session session, final String remoteDir) throws JSchException, SftpException {
        if (!session.isConnected()) {
            session.connect(DEFAULT_CONNECT_TIMEOUT_MS);
        }
        ChannelSftp channelSftp = channelSftp(session, DEFAULT_CONNECT_TIMEOUT_MS);
        channelSftp.setFilenameEncoding("UTF-8");
        try {
            return channelSftp.ls(remoteDir);
        } finally {
            channelSftp.disconnect();
        }
    }

    /**
     * 执行命令
     * <pre style="color:green;">
     * Available commands:           <strong style="color:red;">* means unimplemented command.</strong><br>
     * cd path                       Change remote directory to 'path'<br>
     * lcd path                      Change local directory to 'path'<br>
     * chgrp grp path                Change group of file 'path' to 'grp'<br>
     * chmod mode path               Change permissions of file 'path' to 'mode'<br>
     * chown own path                Change owner of file 'path' to 'own'<br>
     * df [path]                     Display statistics for current directory or filesystem containing 'path'<br>
     * get remote-path [local-path]  Download file<br>
     * get-resume remote-path [local-path]  Resume to download file.<br>
     * get-append remote-path [local-path]  Append remote file to local file<br>
     * hardlink oldpath newpath      Hardlink remote file<br>
     * *lls [ls-options [path]]      Display local directory listing<br>
     * ln oldpath newpath            Symlink remote file<br>
     * *lmkdir path                  Create local directory<br>
     * lpwd                          Print local working directory<br>
     * ls [path]                     Display remote directory listing<br>
     * *lumask umask                 Set local umask to 'umask'<br>
     * mkdir path                    Create remote directory<br>
     * put local-path [remote-path]  Upload file<br>
     * put-resume local-path [remote-path]  Resume to upload file<br>
     * put-append local-path [remote-path]  Append local file to remote file.<br>
     * pwd                           Display remote working directory<br>
     * stat path                     Display info about path<br>
     * exit                          Quit sftp<br>
     * quit                          Quit sftp<br>
     * rename oldpath newpath        Rename remote file<br>
     * rmdir path                    Remove remote directory<br>
     * rm path                       Delete remote file<br>
     * symlink oldpath newpath       Symlink remote file<br>
     * readlink path                 Check the target of a symbolic link<br>
     * realpath path                 Canonicalize the path<br>
     * rekey                         Key re-exchanging<br>
     * compression level             Packet compression will be enabled<br>
     * version                       Show SFTP version<br>
     * </pre>
     *
     * @param session    会话
     * @param command    命令
     * @param successOut 成功的输出流
     * @param errorOut   异常的输出流
     * @return 0成功 1异常
     * @throws JSchException
     * @throws IOException
     * @throws InterruptedException
     */
    public static int execCommand(Session session, String command, OutputStream successOut, OutputStream errorOut) throws JSchException, InterruptedException, IOException {
        if (!session.isConnected()) {
            session.connect(DEFAULT_CONNECT_TIMEOUT_MS);
        }
        ChannelExec channelExec = channelExec(session, command, errorOut, DEFAULT_CONNECT_TIMEOUT_MS);
        try (InputStream is = channelExec.getInputStream()) {
            byte[] buf = new byte[1024];
            while (true) {
                while (is.available() > 0) {
                    int len = is.read(buf);
                    if (len < 0) {
                        break;
                    } else {
                        successOut.write(buf, 0, len);
                    }
                }
                if (channelExec.isClosed()) {
                    if (is.available() > 0) {
                        continue;
                    }
                    return channelExec.getExitStatus();
                }
                Thread.sleep(500);
            }
        } finally {
            if (successOut != null) {
                successOut.flush();
                successOut.close();
            }
            channelExec.disconnect();
        }
    }

    /**
     * 创建SFTP通道
     *
     * @param session        会话
     * @param connectTimeout 连接超时时间
     * @return
     * @throws JSchException
     */
    private static ChannelSftp channelSftp(Session session, int connectTimeout) throws JSchException {
        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        session.connect(connectTimeout);
        return channel;
    }

    /**
     * 创建执行命令的通道
     *
     * @param session           会话
     * @param command           命令
     * @param errorOutputStream 异常输出流
     * @param connectTimeout    连接超时时间
     * @return
     * @throws JSchException
     */
    private static ChannelExec channelExec(Session session, String command, OutputStream errorOutputStream, int connectTimeout) throws JSchException {
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand(command);
        channel.setInputStream(null);
        channel.setErrStream(errorOutputStream);
        channel.connect(connectTimeout);
        return channel;
    }

    /**
     * 用户信息
     */
    @Setter
    public static class UserDetail implements UserInfo {

        private String username;

        private String password;

        private String host;

        private int port;

        @Override
        public String getPassphrase() {
            return null;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public boolean promptPassword(String message) {
            return false;
        }

        @Override
        public boolean promptPassphrase(String message) {
            return false;
        }

        @Override
        public boolean promptYesNo(String message) {
            return false;
        }

        @Override
        public void showMessage(String message) {

        }
    }

}
