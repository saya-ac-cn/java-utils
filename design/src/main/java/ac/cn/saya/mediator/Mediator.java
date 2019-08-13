package ac.cn.saya.mediator;

/**
 * @Title: Mediator
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-12 21:56
 * @Description:
 * 定义抽象Mediator,可以与同时们进行联络
 */

public abstract class Mediator {

    public abstract void contact(String content,Colleague coll);

}
