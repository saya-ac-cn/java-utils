package ac.cn.saya.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @Title: NIOFileChannel04
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-22 21:15
 * @Description:拷贝文件 transferFrom 方法
 */

public class NIOFileChannel04 {
    public static void main(String[] args) throws IOException {
        //创建相关流
        FileInputStream fileInputStream = new FileInputStream("/Users/liunengkai/project/java/java-utils/io/src/main/resources/file01.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/liunengkai/project/java/java-utils/io/src/main/resources/file02.txt");
        //获取各个流对应的 filechannel
        FileChannel source = fileInputStream.getChannel();
        FileChannel destination = fileOutputStream.getChannel();
        //使用 transferForm 完成拷贝
        destination.transferFrom(source, 0, source.size());
        source.close();
        destination.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
