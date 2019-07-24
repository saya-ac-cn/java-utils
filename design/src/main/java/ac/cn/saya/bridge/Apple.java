package ac.cn.saya.bridge;

/**
 * @Title: Apple
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-22 21:15
 * @Description:
 */

public class Apple implements Brand {

    @Override
    public void open() {
        System.out.println("Apple手机开机");
    }

    @Override
    public void close() {
        System.out.println("Apple手机关机");
    }

    @Override
    public void call() {
        System.out.println("Apple手机拨号");
    }
}
