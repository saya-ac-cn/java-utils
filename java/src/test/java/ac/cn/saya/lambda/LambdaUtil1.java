package ac.cn.saya.lambda;

import org.junit.Test;

/**
 * @Title: LambdaUtil1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-01 22:19
 * @Description:
 */

public class LambdaUtil1 {

    @Test
    public void test1(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("线程执行");
            }
        };
        r1.run();
        System.out.println("------------");
        Runnable r2 = () -> System.out.println("线程执行");
        r2.run();
    }

}
