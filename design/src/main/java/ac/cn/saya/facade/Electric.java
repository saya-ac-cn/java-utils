package ac.cn.saya.facade;

/**
 * @Title: Electric
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-25 22:23
 * @Description:
 * 电路系统
 */

public class Electric {

    //使用单例模式, 使用饿汉式
    private static Electric instance = new Electric();
    public static Electric getInstanc() {
        return instance;
    }

    public void on() {
        System.out.println(" 开启电路系统 ");
    }

    public void off() {
        System.out.println(" 关闭电路系统 ");
    }

    public void run() {
        System.out.println(" 正在输出电源 ");
    }

}
