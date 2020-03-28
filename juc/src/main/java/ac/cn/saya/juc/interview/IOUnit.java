package ac.cn.saya.juc.interview;


import java.io.*;

/**
 * @Title: IOUnit
 * @ProjectName spring-boot-webflux
 * @Description: TODO
 * @Author Administrator
 * @Date: 2020/3/10 0010 14:13
 * @Description:IO操作
 */

public class IOUnit {

    // 文件复制
    public void fileCopy(){
        try {
            File source = new File("D:/os/line.txt");
            File destination = new File("D:/os/copy.txt");
            if (!destination.exists()){
                destination.createNewFile();
            }
            FileInputStream fis = new FileInputStream(source);
            FileOutputStream fos = new FileOutputStream(destination);
            int c;
            while ((c = fis.read())!= -1){
                fos.write(c);
            }
            fos.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 文件写入
    public void writeStr(){
        File file = new File("D:/os/copy.txt");
        try {
            OutputStream outputStream = new FileOutputStream(file);
            String str = "Java是一种可以撰写跨平台应用软件的面向对象的程序设计语言，是由Sun Microsystems公司于 1995年5月推出的Java程序设计语言和Java平台（即JavaSE, JavaEE, JavaME）的总称。Java 技术具有 卓越的通用性、高效性、平台移植性和安全性，广泛应用于个人PC、数据中心、游戏控制台、科 学超级计算机、移动电话和互联网，同时拥有全球最大的开发者专业社群。在全球云计算和移动互 联网的产业环境下，Java更具备了显著优势和广阔前景。";
            outputStream.write(str.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 文件读取
    public void readStr(){
        File file = new File("D:/os/copy.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            while ((fileInputStream.read(bytes))!=-1){
                System.out.println(new String(bytes));
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
