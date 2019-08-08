package ac.cn.saya.visitor;

/**
 * @Title: Action
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-06 21:47
 * @Description:
 * 打出评价
 */

public abstract class Action {

    //得到男性 的测评
    public abstract void getManResult(Man man);

    //得到女的 测评
    public abstract void getWomanResult(Woman woman);

}
