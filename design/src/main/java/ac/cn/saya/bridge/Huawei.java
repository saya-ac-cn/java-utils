package ac.cn.saya.bridge;

/**
 * @Title: Huawei
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-22 21:17
 * @Description:
 */

public class Huawei implements Brand {

    @Override
    public void open() {
        System.out.println("Huawei手机开机");
    }

    @Override
    public void close() {
        System.out.println("Huawei手机关机");
    }

    @Override
    public void call() {
        System.out.println("Huawei手机拨号");
    }
}
