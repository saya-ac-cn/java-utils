package ac.cn.saya.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Title: SocketClient
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-04-27 10:56
 * @Description:
 */

public class SocketClient {

    public static void main(String [] args){
        Socket socket = null;
        try {
            // 1
            socket= new Socket("127.0.0.1",8080);

            // 2
            OutputStream out = socket.getOutputStream();
            InputStream input = socket.getInputStream();

            out.write("biubiu".getBytes());
            out.flush();
            socket.shutdownOutput();

            byte[] data = new byte[1024];
            int len;
            while ((len = input.read(data)) != -1){
                System.out.println(new String(data,0,len));
            }
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
        }
    }
}
