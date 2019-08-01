package ac.cn.saya.proxy.statics;

/**
 * @Title: Cat
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-30 21:45
 * @Description:
 * 被代理类
 * 目标对象实现类
 */

public class Cat implements Animal {
    @Override
    public void action() {
        System.out.println("喵喵喵~~~~");
    }

    @Override
    public void breath() {
        System.out.println("猫式呼吸法~~~~");
    }
}
