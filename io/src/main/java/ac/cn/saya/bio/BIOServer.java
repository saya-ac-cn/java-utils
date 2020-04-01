package ac.cn.saya.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @Title: BIOServer
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-22 15:30
 * @Description: 1) 使用BIO模型编写一个服务器端，监听6666端口，当有客户端连接时，就启动一个线程与之通讯。
 * 2) 要求使用线程池机制改善，可以连接多个客户端.
 * 3) 服务器端可以接收客户端发送的数据(telnet 方式即可)。
 */

public class BIOServer {

    public static void main(String[] args) throws IOException {
        // 线程池机制
        //思路
        //1. 创建一个线程池
        //2. 如果有客户端连接，就创建一个线程，与之通讯(单独写一个方法)
        ExecutorService poolExecutor = new ThreadPoolExecutor(2, 4, 1L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        // 创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务已经启动");
        while (true) {
            System.out.println("线程信息id=" + Thread.currentThread().getId() + "名字 =" + Thread.currentThread().getName());
            //监听，等待客户端连接
            System.out.println("等待连接....");
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            poolExecutor.execute(() -> {
                handler(socket);
            });
        }
    }

    //编写一个 handler 方法，和客户端通讯
    public static void handler(Socket socket) {
        System.out.println("线程信息id =" + Thread.currentThread().getId() + "名字=" + Thread.currentThread().getName());
        byte[] bytes = new byte[1024];
        try {
            //通过 socket 获取输入流
            InputStream inputStream = socket.getInputStream();
            //循环的读取客户端发送的数据
            while (true) {
                System.out.println("线程信息id =" + Thread.currentThread().getId() + "名字=" + Thread.currentThread().getName());
                System.out.println("read....");
                int read = inputStream.read(bytes);
                if (-1 != read) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
