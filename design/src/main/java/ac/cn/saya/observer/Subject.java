package ac.cn.saya.observer;

/**
 * @Title: Subject
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-08 22:10
 * @Description:
 * 订阅接口
 */

public interface Subject {

    /**
     * 注册订阅者
     * @param o
     */
    public void registerObserver(Observer o);

    /**
     * 移除订阅者
     * @param o
     */
    public void removeObserver(Observer o);

    /**
     * 通知订阅
     */
    public void notifyObservers();

}
