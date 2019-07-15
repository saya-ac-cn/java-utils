package ac.cn.saya.factory.simple;

/**
 * @Title: MessageUtilTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-12 21:27
 * @Description:
 * 发送消息简单工厂测试单元
 */

public class MessageUtilTest {

    /**
     * 发送短信
     */
    public static final int TYPE_PHONE = 1;
    /**
     * 发送邮件
     */
    public static final int TYPE_EMAIL = 2;

    /**
     * @描述 调度处理
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-07-12
     * @修改人和其它信息
     */
    public static Message createMessage(int type) {
        switch (type) {
            case TYPE_PHONE:
                return new Phone();
            case TYPE_EMAIL:
                return new Email();
            default:
                return new Phone();
        }
    }

    public static void main(String []args){
        Message message = MessageUtilTest.createMessage(MessageUtilTest.TYPE_EMAIL);
        message.sendMessage();
    }


}
