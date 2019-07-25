package ac.cn.saya.decorator;

/**
 * @Title: Decorator
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-23 22:44
 * @Description:
 * 装饰角色
 */

public class Decorator implements Component{

    /**
     * 持有一个Component类型的对象引用
     */
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void function() {
        //客户端的调用委派给具体的子类
        component.function();
    }

}
