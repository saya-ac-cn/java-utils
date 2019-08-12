package ac.cn.saya.observer;

/**
 * @Title: TestUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-08 22:24
 * @Description:
 */

public class TestUtil {

    public static void main(String[] args) {
        //创建一个 WeatherData
        WeatherData weatherData = new WeatherData();
        Sina sina = new Sina();
        Tencent tencent = new Tencent();

        //注册到 weatherData
        weatherData.registerObserver(sina);
        weatherData.registerObserver(tencent);

        //测试
        System.out.println("通知各个注册的观察者, 看看信息");
        weatherData.setData(10f, 100f, 30.3f);
    }

}
