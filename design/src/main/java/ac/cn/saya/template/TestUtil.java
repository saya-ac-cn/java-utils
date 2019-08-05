package ac.cn.saya.template;

/**
 * @Title: TestUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-01 22:01
 * @Description: 模板模式测试单元
 */

public class TestUtil {

    public static void main(String[] args) {
        System.out.println("----制作红豆豆浆----");
        SoyaMilk redBeanSoyaMilk = new RedBeanSoyaMilk();
        redBeanSoyaMilk.make();

        System.out.println("----制作纯豆浆----");
        SoyaMilk peanutSoyaMilk = new CleanSoyaMilk();
        peanutSoyaMilk.make();
    }

}
