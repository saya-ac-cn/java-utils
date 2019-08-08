package ac.cn.saya.visitor;

/**
 * @Title: Fail
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-06 21:54
 * @Description:
 * 淘汰
 */

public class Fail extends Action {
    @Override
    public void getManResult(Man man) {
        System.out.println("Man的考核：淘汰 !");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("Woman的考核：淘汰 !");
    }
}
