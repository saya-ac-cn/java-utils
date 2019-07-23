package ac.cn.saya.adapter.interfaceadapter;

/**
 * @Title: TestUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-21 21:27
 * @Description:
 */

public class TestUtil {

    public static void main(String[] args) {
        SessionListenr sessionListenr = new SessionListenr() {
            @Override
            public void after() {
                System.out.println("登录验证");
            }
        };
        sessionListenr.after();
    }

}
