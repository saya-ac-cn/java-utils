package ac.cn.saya.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Title: NIOFileChannel03
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-22 21:07
 * @Description:使用一个 Buffer 完成文件读取、写入
 */

public class NIOFileChannel03 {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("/Users/liunengkai/project/java/java-utils/io/src/main/resources/file01.txt");
        FileChannel fileChannel01 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("/Users/liunengkai/project/java/java-utils/io/src/main/resources/file02.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            //这里有一个重要的操作，一定不要忘了
            /*
            public final Buffer clear() { position = 0;
                limit = capacity; mark = -1; return this;
            } */
            buffer.clear();// 清空上一次的
            int read = fileChannel01.read(buffer);
            System.out.println("read =" + read);
            if (-1 == read) {
                // 读完
                break;
            }
            //将 buffer 中的数据写入到 fileChannel02 -- 2.txt
            buffer.flip();
            fileChannel02.write(buffer);
        }
        fileChannel01.close();
        fileChannel02.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
