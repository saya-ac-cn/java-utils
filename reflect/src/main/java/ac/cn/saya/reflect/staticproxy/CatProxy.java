package ac.cn.saya.reflect.staticproxy;

/**
 * @Title: CatProxy
 * @ProjectName spyder
 * @Description: TODO
 * @Author Administrator
 * @Date: 2019/4/29 0029 17:40
 * @Description:
 * 代理类，代理Cat去完成这些操作
 * 通过实现与目标对象相同的接口
 * 并维护一个代理对象，通过构造器传入实际目标对象并赋值
 * 执行代理对象实现的接口方法，实现对目标对象实现的干预
 */

public class CatProxy implements Animal {

    //真正要代理的类
    Cat cat;

    public CatProxy(Cat cat){
        this.cat = cat;
    }

    @Override
    public void action() {
        System.out.println("==========DogProxy 代理类执行开始！=============");
        //实质上在代理类中是调用了被代理实现接口的方法
        cat.action();
        System.out.println("==========DogProxy 代理类执行结束！===========");
    }

    @Override
    public void breath() {
        System.out.println("==========DogProxy 代理类执行开始！=============");
        cat.breath();
        System.out.println("==========DogProxy 代理类执行结束！===========");
    }

}
