package ac.cn.saya.singleton;

/**
 * @Title: SingletonUtil1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-10 20:45
 * @Description:
 * 单例模式（饿汉式-静态代码块）
 */

public class SingletonUtil2 {

    public static void main(String []args){
        for (int i = 0 ; i < 10 ; i++){
            new Thread(() -> {
                System.out.println((SingletonClassUitl2.getInstance()).hashCode());
            }).start();
        }
    }

}

/**
 * @描述 饿汉式（静态代码块）
 * @参数
 * @返回值
 * @创建人  saya.ac.cn-刘能凯
 * @创建时间  2019-07-10
 * @修改人和其它信息
 */
class SingletonClassUitl2{

    /**
     * 本类内部创建对象实例
     */
    private static SingletonClassUitl2 instance;

    /**
     * 在静态代码块中创建单例对象
     */
    static {
        instance = new SingletonClassUitl2();
    }

    /**
     * @描述 构造器私有化
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-07-10
     * @修改人和其它信息
     */
    private SingletonClassUitl2() {
    }


    /**
     * @描述 提供一个公有的静态方法，返回实例对象
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-07-10
     * @修改人和其它信息
     */
    public static SingletonClassUitl2 getInstance(){
        return instance;
    }

}
