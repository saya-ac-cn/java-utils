package ac.cn.saya.factory.abstracts;

/**
 * @Title: ComputerUtilTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-12 22:23
 * @Description:
 * 抽象工厂方法测试单元
 */

public class ComputerUtilTest {

    public static void main(String []args){
        Computer server = new Server();
        server.choiseCPU().choise();
        server.choiseMemory().choise();
        server.choiseHardDisk().choise();
    }

}
