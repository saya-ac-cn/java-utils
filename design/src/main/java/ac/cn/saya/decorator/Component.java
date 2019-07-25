package ac.cn.saya.decorator;

/**
 * @Title: Component
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-23 22:42
 * @Description:
 * 抽象构件角色（对应动物类）
 */

/**
 * 抽象构件角色（Component）通常是一个抽象类或者一个接口，定义了一系列方法，
 * 方法的实现可以由子类实现或者自己实现。通常不会直接使用该类，而是通过继承
 * 该类或者实现该接口来实现特定的功能
 *
 * 具体构件角色（Concrete Component）：是Component的子类，实现了对应的
 * 方法，它就是那个被装饰的类。
 *
 * 装饰角色（Decorator）：是Component的子类，它是具体装饰角色共同实现的
 * 抽象类（也可以是接口），并且持有一个Component类型的对象引用，它的主要
 * 作用就是把客户端的调用委派到被装饰类。
 *
 * 具体装饰角色（Concrete Decorator）：它是具体的装饰类，是Decorator的
 * 子类，当然也是Component的子类。它主要就是定义具体的装饰功能，
 *
 * https://blog.csdn.net/csdn15698845876/article/details/81544562
 */

public interface Component {

    public void function();

}
