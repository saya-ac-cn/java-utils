package ac.cn.saya.factory.method;

/**
 * @Title: ActiveMQSend
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-12 21:45
 * @Description:
 * 使用activeMQ发送短信
 */

public class ActiveMQSend implements MessageSendMethod {

    public ActiveMQSend() {
    }

    @Override
    public Message choiseSendMethod() {
        return new Phone();
    }
}
