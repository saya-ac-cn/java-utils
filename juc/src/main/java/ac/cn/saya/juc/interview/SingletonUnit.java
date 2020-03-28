package ac.cn.saya.juc.interview;

/**
 * @Title: Singleton
 * @ProjectName spring-boot-webflux
 * @Description: TODO
 * @Author Administrator
 * @Date: 2020/3/10 0010 11:49
 * @Description:单例模式
 */

public class SingletonUnit {
}

class lazy{
    private final static lazy instance = new lazy();

    private lazy() {
    }

    public static lazy getInstance(){
        return instance;
    }

}

class hungry{
    private static volatile hungry instance;

    private hungry(){}

    public static hungry getInstance(){
        if (null == instance){
            instance = new hungry();
        }
        return instance;
    }

}

// 双端检测
class dcl{

    private static volatile dcl instance;

    private dcl() {
    }

    public static dcl getInstance(){
        if (null == instance){
            synchronized (dcl.class){
                if (null == instance){
                    instance = new dcl();
                }
            }
        }
        return instance;
    }

}
