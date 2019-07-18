package ac.cn.saya.prototype;

/**
 * @Title: Client
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-15 21:29
 * @Description:
 */

public class Client {

    public static void main(String[] args) {
        SheepEntity sheepEntity1 = new SheepEntity();
        // 使用clone
        SheepEntity sheepEntity2 = (SheepEntity)sheepEntity1.clone();
    }

}
