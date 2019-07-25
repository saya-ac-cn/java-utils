package ac.cn.saya.decorator;

/**
 * @Title: TestUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-23 22:49
 * @Description:
 * 装饰者模式测试单元
 */

public class TestUtil {

    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        System.out.println("------装饰前：-------");
        component.function();
        Component newComponent = new ConcreteDecorator(component);
        System.out.println("------装饰后：-------");
        newComponent.function();
    }

}
