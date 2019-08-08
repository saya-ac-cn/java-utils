package ac.cn.saya.visitor;

/**
 * @Title: Client
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-06 22:00
 * @Description:
 */

public class Client {

    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.attach(new Man());
        objectStructure.attach(new Woman());
        // 考核
        Success success = new Success();
        objectStructure.display(success);
        Fail fail = new Fail();
        objectStructure.display(fail);
    }

}
