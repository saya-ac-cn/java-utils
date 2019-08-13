package ac.cn.saya.mediator;

/**
 * @Title: Colleague
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-12 21:57
 * @Description:
 */

public class Colleague {

    protected String name;
    protected Mediator mediator;

    public Colleague(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }
}
