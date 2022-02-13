package ac.cn.saya;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Title: IOWriteRank
 * @ProjectName java-utils
 * @Author saya
 * @Date: 2022/2/13 22:22
 * @Description: io写入性能比较
 * 参考：https://blog.csdn.net/yiifaa/article/details/78128363
 */

public class IOWriteRank {

    private static String word1038 = "Earned Authority: all individuals are given the opportunity to participate, but their influence is based on publicly earned merit – what they contribute to the community. Merit lies with the individual, does not expire, is not influenced by employment status or employer, and is non-transferable (merit earned in one project cannot be applied to another). More on merit.\n" +
            "Community of Peers: individuals participate at the ASF, not organizations. The ASF’s flat structure dictates that roles are equal irrespective of title, votes hold equal weight, and contributions are made on a volunteer basis (even if paid to work on Apache code). The Apache community is expected to treat each other with respect in adherence to our Code of Conduct. Domain expertise is appreciated; Benevolent Dictators For Life are disallowed. More on individual participation.\n" +
            "Open Communications: as a virtual organization, the ASF requires all communications related to code and decision-making to be publicly accessible to ensure asynchronous collaboration, ";

    /**
     * 耗时：2423ms
     * @param file
     */
    public static void writeBuffer(File file) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));) {
            int i = 1000000;
            while (i>0){
                writer.write(word1038);
                i --;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 耗时：1158ms
     * @param file
     */
    public static void writeByteBuffer(File file) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(file);
            FileChannel channel = fileOutputStream.getChannel();) {
            int times = 100;
            byte[] bytes = word1038.getBytes();
            final ByteBuffer buffer = ByteBuffer.allocate(times * 1040);
            int i = 10000;
            while (i>0){
                for (int j = 0; j < times; j++) {
                    buffer.put(bytes);
                }
                buffer.flip();
                channel.write(buffer);
                buffer.clear();
                i --;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 耗时：1875ms
     * @param file
     */
    public static void writeMappedByteBuffer(File file){
        try(RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
            FileChannel channel = accessFile.getChannel();) {
            byte[] bytes = word1038.getBytes();
            int len = bytes.length * 100;
            long offset = 0;
            int i = 10000;
            while (i>0){
                MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, offset, len);
                for (int j = 0; j < 100; j++) {
                    mappedByteBuffer.put(bytes);
                }
                offset += len;
                i --;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getLength(String s) {
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255) {
                length++;
            } else {
                length += 2;
            }
        }
        return length;
    }

    public static void main(String[] args) {
        long  startTime=System.currentTimeMillis();//开始执行时间
        File file = new File("/Users/saya/project/java/java-utils/io/src/main/resources/write.file");
        writeMappedByteBuffer(file);
        long  endTime=System.currentTimeMillis();//结束执行时间
        System.out.println("耗时："+String.valueOf(endTime-startTime)+"ms");
    }

}