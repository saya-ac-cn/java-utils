package ac.cn.saya.factory.abstracts;

/**
 * @Title: Server
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-12 22:21
 * @Description:
 * 服务器配置（至强处理器，DDR4，固态硬盘）
 */

public class Server implements Computer{

    public Server() {
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
        return new Xeon();
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
        return new DDR4();
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
        return new SSD();
    }
}
