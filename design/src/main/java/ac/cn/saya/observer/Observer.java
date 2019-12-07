package ac.cn.saya.observer;

/**
 * @Title: Observer
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-08 22:19
 * @Description:
 * 观察者接口，由观察者来实现
 */

public interface Observer {
    public void update(float temperature, float pressure, float humidity);
}
