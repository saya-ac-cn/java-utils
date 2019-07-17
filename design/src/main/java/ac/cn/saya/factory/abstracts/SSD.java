package ac.cn.saya.factory.abstracts;

/**
 * @Title: SSD
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-12 22:04
 * @Description:
 * 固态硬盘
 */

public class SSD implements HardDisk {

    public SSD() {
    }

    /**
     * @描述 选择固态硬盘
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-07-12
     * @修改人和其它信息
     */
    @Override
    public void choise() {
        System.out.println("选择固态硬盘");
    }
}
