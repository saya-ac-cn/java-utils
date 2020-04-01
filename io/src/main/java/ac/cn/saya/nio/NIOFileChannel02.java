package ac.cn.saya.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Title: NIOFileChannel02
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-22 21:01
 * @Description:本地文件读数据
 */

public class NIOFileChannel02 {

    public static void main(String[] args) throws IOException {
        //创建文件的输入流
        File file = new File("/Users/liunengkai/project/java/java-utils/io/src/main/resources/file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        //通过 fileInputStream 获取对应的 FileChannel -> 实际类型FileChannelImpl
        FileChannel channel = fileInputStream.getChannel();
        //创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate((int)file.length());
        //将 通道的数据读入到 Buffer
        channel.read(buffer);
        //将 byteBuffer 的 字节数据 转成 String
        System.out.println(new String(buffer.array()));
        channel.close();
        fileInputStream.close();
    }

}
