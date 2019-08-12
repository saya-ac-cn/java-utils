package ac.cn.saya.observer;

/**
 * @Title: Tencent
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-08 22:21
 * @Description:
 */

public class Tencent implements Observer  {

    // 温度，气压，湿度
    private float temperature;
    private float pressure;
    private float humidity;

    /**
     * 更新 天气情况，是由 WeatherData 来调用，我使用推送模式
     * @param temperature
     * @param pressure
     * @param humidity
     */
    @Override
    public void update(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }

    // 显示
    public void display() {
        System.out.println("===腾讯门户网站===="); 
        System.out.println("***腾讯门户网站 气温 : " + temperature + "***"); 
        System.out.println("***腾讯门户网站 气压: " + pressure + "***"); 
        System.out.println("***腾讯门户网站 湿度: " + humidity + "***");
    }
    
}
