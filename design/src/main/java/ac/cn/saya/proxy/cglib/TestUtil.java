package ac.cn.saya.proxy.cglib;

/**
 * @Title: TestUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-31 21:59
 * @Description:
 * cglib动态代理测试单元，需要引入cglib包
 */

public class TestUtil {

    public static void main(String[] args) {
        //创建目标对象
        Cat target = new Cat();
        //获取到代理对象，并且将目标对象传递给代理对象
        Cat proxyInstance = (Cat)new ProxyFactory(target).getProxyInstance();
        //执行代理对象的方法，触发 intecept 方法，从而实现 对目标对象的调用
        proxyInstance.action();
        proxyInstance.breath();
    }

}
