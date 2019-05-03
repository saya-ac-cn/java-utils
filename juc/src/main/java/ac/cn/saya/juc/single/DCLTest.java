package ac.cn.saya.juc.single;

/**
 * @Title: DCLTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-03 23:05
 * @Description:
 * 双端检测
 * DCL (Double Check Lock)
 * 避免指令重排序
 */

public class DCLTest {

    /**
     * @描述 加入volatile是避免指令重排序
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-05-03
     * @修改人和其它信息
     */
    public static volatile DCLTest instance;

    private DCLTest() {
        System.out.println("调用对象的构造方法");
    }

    public static DCLTest getInstance(){
        if (instance == null){
            synchronized (DCLTest.class){
                if (instance == null){
                    instance = new DCLTest();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args){
        for (int i = 0 ; i < 10 ; i++){
            new Thread(() -> {
                DCLTest.getInstance();
            }).start();
        }
    }

}
