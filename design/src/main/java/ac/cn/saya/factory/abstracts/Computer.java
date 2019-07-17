package ac.cn.saya.factory.abstracts;

/**
 * @Title: Computer
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-12 22:15
 * @Description:
 * 选择计算机配置
 */

public interface Computer {

    /**
     * @描述 选择处理器
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-07-12
     * @修改人和其它信息
     */
    public CPU choiseCPU();

    /**
     * @描述 选择内存
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-07-12
     * @修改人和其它信息
     */
    public Memory choiseMemory();

    /**
     * @描述 选择硬盘
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-07-12
     * @修改人和其它信息
     */
    public HardDisk choiseHardDisk();

}
