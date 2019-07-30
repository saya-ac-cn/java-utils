package ac.cn.saya.facade;

/**
 * @Title: DashBoard
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-25 22:29
 * @Description: 外观模式，仪表盘
 */

public class DashBoard {

    /**
     * 各个设备子系统
     */
    private Electric electric;
    private Oil oil;

    public DashBoard() {
        this.electric = Electric.getInstanc();
        this.oil = Oil.getInstanc();
    }

    /**
     * 启动系统
     */
    public void startOS() {
        electric.on();
        oil.on();
    }

    /**
     * 运行设备
     */
    public void runOS() {
        electric.run();
        oil.run();
    }

    /**
     * 关闭系统
     */
    public void closeOS() {
        electric.off();
        oil.off();
    }

}
