package ac.cn.saya.facade;

/**
 * @Title: Oil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-25 22:24
 * @Description:
 * 油路系统
 */

public class Oil {

    //使用单例模式, 使用饿汉式
    private static Oil instance = new Oil();
    public static Oil getInstanc() {
        return instance;
    }

    public void on() {
        System.out.println(" 开启油路系统 ");
    }

    public void off() {
        System.out.println(" 关闭油路系统 ");
    }

    public void run() {
        System.out.println(" 油路系统供油 ");
    }

}
