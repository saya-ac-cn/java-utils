package ac.cn.saya.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Title: SocketServer
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-04-27 10:45
 * @Description:
 * tcp服务端实例
 */

public class SocketServer {
    public static void main(String [] args){
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            // 1
            serverSocket = new ServerSocket(8080);
            socket= serverSocket.accept();

            // 2
            OutputStream out = socket.getOutputStream();
            InputStream input = socket.getInputStream();

            byte[] data = new byte[1024];
            int len;
            while ((len = input.read(data)) != -1){
                System.out.println(new String(data,0,len));
            }
            out.write("连接成功".getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(socket != null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(serverSocket != null){
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
