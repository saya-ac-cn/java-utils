package ac.cn.saya.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Title: ChannelUtilTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-21 21:15
 * @Description:
 * 通道（channel）：用于源节点于目标节点的连接，在java nio中负责缓冲区中数据的传输，channel本身不存储数据，因此需要配合缓冲区进行传输
 *
 * 通道的主要实现类
 * java.nio.channels.Channel接口：
 *      |--FileChannel
 *      |--SocketChannel
 *      |--ServerSocketChannel
 *      |--DatagramChannel
 * 获取通道
 * java针对支持通道的类提供了getChannel（）方法
 *      本地IO：
 *      FileInputStream/FileOutputStream
 *      RandomAccessFile
 *      网络IO：
 *      Socket
 *      ServerSocket
 *      DatagramSocket
 * 在jdk1.7中的nio.2针对各个通道提供了静态方法open（）
 * 在jdk1.7中的nio.2的Files工具类的newByteChannel（）
 */

public class ChannelUtilTest {

    /**
     * @描述 管道之间的数据传输（直接缓冲区）
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-05-21
     * @修改人和其它信息
     */
    public static void test1() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("/home/saya/conf.sh"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("/home/saya/conf1.sh"), StandardOpenOption.WRITE,StandardOpenOption.READ);
        inChannel.transferTo(0,inChannel.size(),outChannel);
        //outChannel.transferFrom(inChannel,0,inChannel.size());
        inChannel.close();
        outChannel.close();
    }

    /**
     * @描述 使用直接缓冲区完成文件的复制（内存映射文件）
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-05-21
     * @修改人和其它信息
     */
    public static void test2() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("/home/saya/conf.sh"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("/home/saya/conf1.sh"), StandardOpenOption.WRITE,StandardOpenOption.READ);
        //内存映射文件;
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY,0,inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE,0,inChannel.size());
        //直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);
        inChannel.close();
        outChannel.close();
    }

    /**
     * @描述 利用通道完成文件的复制
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-05-21
     * @修改人和其它信息
     */
    public static void test3(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("/home/saya/conf.sh");
            fos = new FileOutputStream("/home/saya/conf1.sh");
            //获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();
            //分配缓冲区的大小
            ByteBuffer buf = ByteBuffer.allocate(1024);
            //将通道中的数据存入缓冲区中
            while (inChannel.read(buf) != -1){
                //切换为数据读取模式
                buf.flip();
                //将缓冲区的数据写入通道中
                outChannel.write(buf);
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inChannel != null){
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outChannel != null){
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
