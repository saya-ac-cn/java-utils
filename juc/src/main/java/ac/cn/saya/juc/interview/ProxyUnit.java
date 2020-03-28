package ac.cn.saya.juc.interview;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Title: ProxyUnit
 * @ProjectName spring-boot-webflux
 * @Description: TODO
 * @Author Administrator
 * @Date: 2020/3/10 0010 12:02
 * @Description:jdk动态代理
 */

public class ProxyUnit {

    public static void main(String[] args) {
        CollegeStudent student = new CollegeStudent();
        Student instance = (Student)Proxy.newProxyInstance(Student.class.getClassLoader(),
                new Class[]{Student.class},
                new DynamicProxyHandler(student));
        instance.register();
    }

}

interface Student{
    public void register();
}

class CollegeStudent implements Student{
    @Override
    public void register() {
        System.out.println("办理入学手续");
    }
}

class DynamicProxyHandler implements InvocationHandler{

    private Object object;

    public DynamicProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(object,args);
    }
}
