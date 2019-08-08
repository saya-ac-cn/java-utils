package ac.cn.saya.visitor;

/**
 * @Title: Man
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-06 21:51
 * @Description:
 * 得到结果
 */

public class Man extends Person {
    @Override
    public void accept(Action action) {
        action.getManResult(this);
    }
}
