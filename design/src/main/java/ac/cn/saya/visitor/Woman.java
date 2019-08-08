package ac.cn.saya.visitor;

/**
 * @Title: Woman
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-06 21:53
 * @Description:
 */

public class Woman extends Person {
    @Override
    public void accept(Action action) {
        action.getWomanResult(this);
    }
}
