package ac.cn.saya.adapter.classadapter;

/**
 * @Title: ClassAdapterTestUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-18 21:55
 * @Description:
 * 类适配器
 */

public class ClassAdapterTestUtil {

    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter());
    }

}
