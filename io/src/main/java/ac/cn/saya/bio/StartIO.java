package ac.cn.saya.bio;

import java.io.File;
import java.io.IOException;
/**
 * @Title: StartIO
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-04-24 21:08
 * @Description:
 */

public class StartIO {

    public static void main(String[] args){
        try {
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getFile();
            String filePath = rootPath + File.separator + "config.propertis";
            File configFile = new File(filePath);
            if(configFile.exists()){
                configFile.delete();
                System.out.println("exists");
            }else{
                configFile.createNewFile();
                System.out.println("don't exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
