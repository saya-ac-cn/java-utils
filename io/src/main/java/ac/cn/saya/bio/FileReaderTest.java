package ac.cn.saya.bio;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Title: FileReaderTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-04-24 22:18
 * @Description:
 */

public class FileReaderTest {

    public static void main(String[] args){
        write();
        read2();
    }

    /**
     * @描述 写入操作
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-04-24
     * @修改人和其它信息
     * 如果文件不存在，则自动创建
     */
    public static void write(){
        File configFile = null;
        FileWriter fw = null;
        try {
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getFile();
            String filePath = rootPath + File.separator + "config.propertis";
            configFile = new File(filePath);
            fw = new FileWriter(configFile,true);
            fw.write("\nuser=saya");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @描述 读入操作
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-04-24
     * @修改人和其它信息
     * 请分析为什么用try
     */
    public static void read1(){
        File configFile = null;
        FileReader fr = null;
        try {
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getFile();
            String filePath = rootPath + File.separator + "config.propertis";
            configFile = new File(filePath);
            fr = new FileReader(configFile);
            int data;
            while ((data = fr.read()) != -1){
                System.out.print((char)data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @描述 每次读取固定长度
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-04-24
     * @修改人和其它信息
     */
    public static void read2(){
        File configFile = null;
        FileReader fr = null;
        try {
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getFile();
            String filePath = rootPath + File.separator + "config.propertis";
            configFile = new File(filePath);
            fr = new FileReader(configFile);
            char[] buf = new char[5];
            int len;
            while ((len = fr.read(buf)) != -1){
                System.out.print(new String(buf,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
