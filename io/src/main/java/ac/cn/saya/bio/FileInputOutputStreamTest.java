package ac.cn.saya.bio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Title: FileInputOutputStreamTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-04-25 20:08
 * @Description:
 */

public class FileInputOutputStreamTest {

    public static void main(String[] args){
        output();
        input();
    }

    /**
     * @描述 使用字节流读取文件
     * @参数  []
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-04-25
     * @修改人和其它信息
     */
    public static void input(){
        FileInputStream fis = null;
        try {
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getFile();
            String filePath = rootPath + File.separator + "config.propertis";
            File configFile = new File(filePath);
            fis = new FileInputStream(configFile);
            byte[] buffer = new byte[5];
            int len;
            while ((len = fis.read(buffer)) != -1){
                System.out.println(new String(buffer,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void output(){
        FileOutputStream fos = null;
        try {
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getFile();
            String filePath = rootPath + File.separator + "config.propertis";
            File configFile = new File(filePath);
            fos = new FileOutputStream(configFile,true);
            fos.write("\npwd=1212".getBytes());
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
