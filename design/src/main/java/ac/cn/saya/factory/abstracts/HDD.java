package ac.cn.saya.factory.abstracts;

/**
 * @Title: HDD
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-12 22:05
 * @Description:
 * 机械硬盘
 */

public class HDD implements HardDisk{
    public HDD() {
    }

    /**
     * @描述 选择机械硬盘
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-07-12
     * @修改人和其它信息
     */
    @Override
    public void choise() {
        System.out.println("选择机械硬盘");
    }
}
