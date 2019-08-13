package ac.cn.saya.mediator;

/**
 * @Title: ColleagueB
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-12 21:58
 * @Description: 定义具体Colleagueclass
 */

public class ColleagueB extends Colleague {

    /**
     * 具体同事类继承自Colleague,此刻就可以与中介者mediator进行通信了
     *
     * @param name
     * @param mediator
     */
    public ColleagueB(String name, Mediator mediator) {
        super(name, mediator);
    }

    public void getMessage(String message) {
        System.out.println("同事B" + name + "获得信息" + message);
    }

    //同事A与中介者通信
    public void contact(String message) {
        mediator.contact(message, this);
    }

}
