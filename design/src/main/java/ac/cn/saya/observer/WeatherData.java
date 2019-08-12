package ac.cn.saya.observer;

import java.util.ArrayList;

/**
 * @Title: WeatherData
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-08 22:13
 * @Description:
 * 存放天气数据
 * 1. 包含最新的天气情况信息
 * 2. 含有 观察者集合，使用 ArrayList 管理
 * 3. 当数据有更新时，就主动的调用 ArrayList, 通知所有的(接入方)就看到最新的信息
 */

public class WeatherData implements Subject {

    private float temperatrue;
    private float pressure;
    private float humidity;
    //观察者集合
    private ArrayList<Observer> observers;

    public WeatherData( ) {
        this.observers = new ArrayList<Observer>();
    }

    public float getTemperatrue() {
        return temperatrue;
    }

    public void setTemperatrue(float temperatrue) {
        this.temperatrue = temperatrue;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public void dataChange() {
        //调用 接入方的 update
        notifyObservers();
    }

    //当数据有更新时，就调用 setData
    public void setData(float temperature, float pressure, float humidity) {
        this.temperatrue = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        //调用 dataChange， 将最新的信息 推送给 接入方 currentConditions
        dataChange();
    }


    /**
     * 注册订阅者
     *
     * @param o
     */
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    /**
     * 移除订阅者
     *
     * @param o
     */
    @Override
    public void removeObserver(Observer o) {
        if(observers.contains(o)) {
            observers.remove(o);
        }
    }

    /**
     * 遍历所有的观察者，并通知
     */
    @Override
    public void notifyObservers() {
        for(int i = 0; i < observers.size(); i++) {
            observers.get(i).update(this.temperatrue, this.pressure, this.humidity);
        }
    }
}
