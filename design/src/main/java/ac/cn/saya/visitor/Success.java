package ac.cn.saya.visitor;

/**
 * @Title: Success
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-06 21:54
 * @Description:
 * 通过
 */

public class Success extends Action {
    @Override
    public void getManResult(Man man) {
        System.out.println("Man的考核：通过 !");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("Woman的考核：通过 !");
    }
}
