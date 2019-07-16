package ac.cn.saya.factory.method;

/**
 * @Title: Email
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-12 21:25
 * @Description:
 * 发送邮件
 */

public class Email implements Message {

    public Email() {
    }

    /**
     * @描述 发送内容
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-07-12
     * @修改人和其它信息
     */
    @Override
    public void sendMessage() {
        System.out.println("发送邮件");
    }
}
