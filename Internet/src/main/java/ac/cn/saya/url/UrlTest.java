package ac.cn.saya.url;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Title: UrlTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-04-27 10:21
 * @Description:
 */

public class UrlTest {

    public static void main(String [] args){
        try {
            URL url = new URL("http://saya.ac.cn/login");
            System.out.println(url.getHost());
            System.out.println(url.getPort());
            System.out.println(url.getPath());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
