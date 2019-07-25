package ac.cn.saya.decorator;

/**
 * @Title: ConcreteComponent
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-23 22:43
 * @Description:
 * 具体构件角色（对应狗）
 */

public class ConcreteComponent implements Component {

    @Override
    public void function() {
        System.out.println("基本功能：呼吸+觅食+睡觉");
    }

}
