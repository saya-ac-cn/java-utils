package ac.cn.saya.factory.abstracts;

/**
 * @Title: DDR3
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-12 22:09
 * @Description:
 * 选择DDR3
 */

public class DDR3 implements Memory {

    public DDR3() {
    }

    /**
     * @描述 选择DDR3
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-07-12
     * @修改人和其它信息
     */
    @Override
    public void choise() {
        System.out.println("选择DDR3");
    }
}
