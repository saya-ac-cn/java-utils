package ac.cn.saya.visitor;

/**
 * @Title: Person
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-06 21:49
 * @Description:
 * 评价
 */

public abstract class Person {

    //提供一个方法，让访问者可以访问
    public abstract void accept(Action action);

}
