package ac.cn.saya.factory.abstracts;

/**
 * @Title: Workstation
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-12 22:19
 * @Description:
 * 工作站配置（DDR3，机械硬盘，酷睿i7）
 */

public class Workstation implements Computer{

    public Workstation() {
    }

    /**
     * @描述 选择处理器
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-07-12
     * @修改人和其它信息
     */
    @Override
    public CPU choiseCPU() {
        return new Core();
    }

    /**
     * @描述 选择内存
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-07-12
     * @修改人和其它信息
     */
    @Override
    public Memory choiseMemory() {
        return new DDR3();
    }

    /**
     * @描述 选择硬盘
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-07-12
     * @修改人和其它信息
     */
    @Override
    public HardDisk choiseHardDisk() {
        return new HDD();
    }
}
