package ac.cn.saya.nio;

import java.nio.ByteBuffer;

/**
 * @Title: BufferUtilTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-20 22:22
 * @Description:
 * 缓冲区（Buffer）；在java NIO中负责数据的存取，缓冲区就是数组，用于存储不同数据类型的数据
 * 根据数据类型不同（boolean除外），提供了相应的缓冲区
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * 上述缓冲区的管理方式几乎一致，通过allocate（）获取缓冲区
 *
 * 缓冲区存取数据的两个核心方法
 * put（）：存入数据到缓冲区
 * get（）：获取缓冲区中的数据
 *
 * 缓冲区中的四个核心属性
 * capacity：容量，表示缓冲区中最大存储数据的容量，一旦声明不能改变
 * limit：界限，表示缓冲区中可操作数据的大小，（limit后面的数据不能进行读写，初始后的大小为capital的大小）
 * position：位置，表示缓冲区中正在操作数据的位置
 * mark：标记，表示记录当前的position的位置，可以通过reset（），恢复到mark的位置
 */

public class BufferUtilTest {

    public static void markTest(){
        String str = "abcde";
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());
        buffer.flip();
        byte[] dst = new byte[buffer.limit()];
        buffer.get(dst,0,2);
        System.out.println(new String(dst,0,2));
        System.out.println(buffer.position());
        // 标记
        buffer.mark();
        buffer.get(dst,2,2);
        System.out.println(new String(dst,0,2));
        System.out.println(buffer.position());
        // reset 恢复到mark的位置
        buffer.reset();
        System.out.println(buffer.position());

    }

    public static void main(String[] args){
        String str = "abcde";
        // 分配一个指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("-----------allocate------------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        //利用put（）存入数据到缓冲区
        buffer.put(str.getBytes());
        System.out.println("-----------put------------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        // 切换读取模式
        buffer.flip();
        System.out.println("-----------flip------------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        // 利用get（） 读取缓冲区中的数据
        byte[] dst = new byte[buffer.limit()];
        buffer.get(dst);
        System.out.println(new String(dst,0,dst.length));
        System.out.println("-----------get------------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        //rewind():可重复读
        buffer.rewind();
        System.out.println("-----------rewind------------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        // clear():清空缓冲区，但是缓冲区中的数据依然存在，处于被遗忘状态
        buffer.clear();
        System.out.println("-----------clear------------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        System.out.println((char)buffer.get());
    }

}
