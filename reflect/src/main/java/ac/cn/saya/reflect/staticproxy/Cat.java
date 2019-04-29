package ac.cn.saya.reflect.staticproxy;

/**
 * @Title: Cat
 * @ProjectName spyder
 * @Description: TODO
 * @Author Administrator
 * @Date: 2019/4/29 0029 17:38
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
