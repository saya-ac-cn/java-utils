package ac.cn.saya.template;

/**
 * @Title: CleanSoyaMilk
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-01 21:59
 * @Description:
 * 纯豆浆
 */

public class CleanSoyaMilk extends SoyaMilk{

    /**
     * 添加不同的配料， 抽象方法, 子类具体实现
     */
    @Override
    void addCondiments() {

    }

    /**
     * 钩子方法，决定是否需要添加配料
     */
    @Override
    boolean customerWantCondiments() {
        return false;
    }
}
