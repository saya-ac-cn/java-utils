package ac.cn.saya.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Title: ProxyFactory
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-31 21:15
 * @Description:
 */

public class ProxyFactory {

    //维护一个目标对象
    private Object target;

    //构造器 对 target 进行初始化
    public ProxyFactory(Object target) {
        this.target = target;
    }

    //给目标对象 生成一个代理对象
    public Object getProxyInstance() {
        /**
         * 说明
         * public static Object newProxyInstance(
         *              ClassLoader loader,: 指定当前目标对象使用的类加载器, 获取加载器的方法固定
         *              Class<?>[] interfaces,: 目标对象实现的接口类型，使用泛型方法确认类型
         *              InvocationHandler h): 事情处理，执行目标对象的方法时，会触发事情处理器方法, 会把当前执行
         *          的目标对象方法作为参数传入
         */
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("JDK 代理开始~~"); //反射机制调用目标对象的方法
                        Object returnVal = method.invoke(target, args);
                        System.out.println("JDK 代理提交");
                        return returnVal;
                    }
                });
    }


}
