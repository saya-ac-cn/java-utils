package ac.cn.saya.factory.method;

/**
 * @Title: KafkaSend
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-12 21:44
 * @Description:
 * 使用kafka发送邮件
 */

public class KafkaSend implements MessageSendMethod {

    public KafkaSend() {
    }

    @Override
    public Message choiseSendMethod() {
        return new Email();
    }

}
