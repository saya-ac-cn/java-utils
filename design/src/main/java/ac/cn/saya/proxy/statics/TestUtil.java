package ac.cn.saya.proxy.statics;

/**
 * @Title: TestUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-30 21:48
 * @Description:
 * 静态代理测试单元
 */

public class TestUtil {

    public static void main(String[] args) {
        //被代理的类Cat，Cat实现了Animal接口
        Cat cat = new Cat();
        //代理类CatProxy，也实现了Animal接口
        CatProxy catProxy = new CatProxy(cat);
        //代理类来调用方法，实际上调用的是Cat的action(),breath()方法
        catProxy.action();
        catProxy.breath();
    }

}
