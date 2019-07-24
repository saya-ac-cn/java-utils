package ac.cn.saya.bridge;

/**
 * @Title: Phone
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-22 21:07
 * @Description:
 */

public abstract class Phone {

    //组合品牌
    private Brand brand;

    public Phone(Brand brand) {
        super();
        this.brand = brand;
    }

    protected void open() {
        this.brand.open();
    }

    protected void close() {
        brand.close();
    }

    protected void call() {
        brand.call();
    }

}
