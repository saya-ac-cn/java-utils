package ac.cn.saya.proxy.dynamic;

/**
 * @Title: TestUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-31 21:28
 * @Description:
 * 动态代理
 */

public class TestUtil {

    public static void main(String[] args) {
        //创建目标对象
        Animal target = new Cat();

        //给目标对象，创建代理对象,
        Animal proxyInstance = (Animal)new ProxyFactory(target).getProxyInstance();

        System.out.println("proxyInstance=" + proxyInstance.getClass());

        proxyInstance.action();

        proxyInstance.breath();

    }

}
