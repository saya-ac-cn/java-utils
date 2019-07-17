package ac.cn.saya.factory.abstracts;

/**
 * @Title: Core
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-12 22:12
 * @Description:
 * 酷睿处理器
 */

public class Core implements CPU{

    public Core() {
    }

    /**
     * @描述 选择酷睿处理器
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-07-12
     * @修改人和其它信息
     */
    @Override
    public void choise() {
        System.out.println("选择酷睿处理器");
    }
}
