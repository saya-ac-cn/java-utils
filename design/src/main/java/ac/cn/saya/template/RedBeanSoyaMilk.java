package ac.cn.saya.template;

/**
 * @Title: RedBeanSoyaMilk
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-01 21:58
 * @Description:
 * 红豆豆浆
 */

public class RedBeanSoyaMilk extends SoyaMilk {

    /**
     * 添加不同的配料， 抽象方法, 子类具体实现
     */
    @Override
    void addCondiments() {
        System.out.println(" 加入上好的红豆 ");
    }
}
