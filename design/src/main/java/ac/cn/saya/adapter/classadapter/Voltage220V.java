package ac.cn.saya.adapter.classadapter;

/**
 * @Title: Voltage220V
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-18 21:47
 * @Description:
 * 被适配的类
 */

public class Voltage220V {

    //输出 220V 的电压
    public int output220V() {
        int src = 220;
        System.out.println("电压=" + src + "伏");
        return src;
    }

}
