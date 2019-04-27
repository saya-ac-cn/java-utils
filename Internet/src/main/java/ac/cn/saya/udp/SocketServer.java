package ac.cn.saya.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @Title: SocketServer
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-04-27 10:28
 * @Description:
 * UDP连接实例 服务端
 */

public class SocketServer {

    public static void main(String [] args){
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(8080);
            byte[] by = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(by,by.length);
            datagramSocket.receive(datagramPacket);
            String str = new String(datagramPacket.getData(),0,datagramPacket.getLength());
            System.out.println("收到了来自："+datagramPacket.getAddress() + "的消息["+str+"]");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(datagramSocket !=null){
                datagramSocket.close();
            }
        }
    }
}
