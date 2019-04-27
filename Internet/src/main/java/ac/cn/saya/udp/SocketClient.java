package ac.cn.saya.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @Title: SocketClient
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-04-27 10:37
 * @Description:
 * UDP实例 客户端
 */

public class SocketClient {

    public static void main(String [] args){
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket();
            byte[] by = "客户端连接测试".getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(by,by.length, InetAddress.getByName("127.0.0.1"),8080);
            datagramSocket.send(datagramPacket);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(datagramSocket !=null){
                datagramSocket.close();
            }
        }
    }
}
