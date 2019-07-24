package ac.cn.saya.bridge;

/**
 * @Title: TestUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-22 21:19
 * @Description:
 */

public class TestUtil {

    public static void main(String[] args) {
        //获取折叠式手机 (样式 + 品牌 )
        Phone phone1 = new FoldedPhone(new Apple());
        phone1.open();
        phone1.call();
        phone1.close();

        System.out.println("=======================");

        UpRightPhone phone2 = new UpRightPhone(new Huawei());
        phone2.open();
        phone2.call();
        phone2.close();
    }

}
