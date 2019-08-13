package ac.cn.saya.mediator;

/**
 * @Title: TestUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-12 22:03
 * @Description:
 * 中介模式测试单元
 */

/**
 * 中介者模式(Mediator)的定义
 *
 * 用一个中介对象来封装一系列的对象交互。中介者使各对象不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互。
 *
 * 中介者模式(Mediator)的适用性
 * 1.一组对象以定义良好但是复杂的方式进行通信，产生的相互依赖关系结构混乱且难以理解。
 * 2.一个对象引用其他很多对象并且直接与这些对象通信,导致难以复用该对象。
 * 3.想定制一个分布在多个类中的行为，但又不想生成太多的子类。
 * 中介者模式(Mediator)的参与者
 *
 * 1.Mediator
 * 中介者定义一个接口用于与各同事（Colleague）对象通信。
 *
 * 2.ConcreteMediator
 * 具体中介者通过协调各同事对象实现协作行为，了解并维护它的各个同事。
 *
 * 3.Colleague:
 * 抽象同事类。
 *
 * ４.Colleagueclass
 * 具体同事类。每个具体同事类都只需要知道自己的行为即可，但是他们都需要认识中介者
 */

public class TestUtil {

    public static void main(String[] args) {
        // 定义中介者
        ConcreteMediator mediator = new ConcreteMediator();
        // 定义具体同事类
        ColleagueA colleagueA = new ColleagueA("张三", mediator);
        ColleagueB colleagueB = new ColleagueB("李四", mediator);
        // 中介者知晓每一个具体的Colleague类
        mediator.setCollA(colleagueA);
        mediator.setCollB(colleagueB);
        colleagueA.contact("我是A，我要和同事B说说工作的事情");
        colleagueB.contact("我是B,我下午有时间,下午商量吧");
    }

}
