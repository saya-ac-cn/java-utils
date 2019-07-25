package ac.cn.saya.decorator;

/**
 * @Title: ConcreteDecorator
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-23 22:47
 * @Description:
 * 具体装饰角色（对应吼叫和吃肉这两个功能）
 */

public class ConcreteDecorator extends Decorator{

    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void function() {
        super.function();
        System.out.println("附加功能：");
        this.eat();
        this.bellow();

    }

    private void eat() {
        System.out.println("吃肉");
    }

    private void bellow() {
        System.out.println("吼叫");
    }

}
