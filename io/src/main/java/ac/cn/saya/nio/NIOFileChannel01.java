package ac.cn.saya.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Title: NIOFileChannel01
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-22 20:53
 * @Description:本地文件写数据
 */

public class NIOFileChannel01 {

    public static void main(String[] args) throws IOException {
        String s = "hello,saya";
        //创建一个输出流->channel
        FileOutputStream outputStream = new FileOutputStream("/Users/liunengkai/project/java/java-utils/io/src/main/resources/file01.txt");
        //通过 FileOutputStream 获取 对应的 FileChannel
        // 这个 FileChannel 真实 类型是 FileChannelImpl
        FileChannel channel = outputStream.getChannel();
        //创建一个缓冲区 ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //将 str 放入 byteBuffer
        buffer.put(s.getBytes());
        //对 byteBuffer 进行 flip
        buffer.flip();
        channel.write(buffer);
        channel.close();
        outputStream.close();
    }
    
}
