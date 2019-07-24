package ac.cn.saya.bridge;

/**
 * @Title: UpRightPhone
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-22 21:13
 * @Description:
 */

public class UpRightPhone extends Phone  {

    public UpRightPhone(Brand brand) {
        super(brand);
    }

    @Override
    protected void open() {
        super.open();
        System.out.println(" 直立式手机 ");
    }

    @Override
    protected void close() {
        super.close();
        System.out.println(" 直立式手机 ");
    }

    @Override
    protected void call() {
        super.call();
        System.out.println(" 直立式手机 ");
    }
}
