package ac.cn.saya.factory.method;

/**
 * @Title: MessageUtilTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-12 21:47
 * @Description:
 * 工厂方法模式测试单元
 */

public class MessageUtilTest {

    public static void main(String []args){
        MessageSendMethod email = new KafkaSend();
        Message emailMessage = email.choiseSendMethod();
        emailMessage.sendMessage();
    }

}
