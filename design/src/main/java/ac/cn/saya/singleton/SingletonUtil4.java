package ac.cn.saya.singleton;

/**
 * @Title: SingletonUtil1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-10 20:45
 * @Description:
 * 单例模式（懒汉式-线程安全）
 */

public class SingletonUtil4 {

    public static void main(String []args){
        for (int i = 0 ; i < 10 ; i++){
            new Thread(() -> {
                System.out.println((SingletonClassUitl4.getInstance()).hashCode());
            }).start();
        }
    }

}

/**
 * @描述 懒汉式（线程安全）
 * @参数
 * @返回值
 * @创建人  saya.ac.cn-刘能凯
 * @创建时间  2019-07-10
 * @修改人和其它信息
 */
class SingletonClassUitl4{

    /**
     * 本类内部创建对象实例
     */
    private static SingletonClassUitl4 instance;

    /**
     * @描述 构造器私有化
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-07-10
     * @修改人和其它信息
     */
    private SingletonClassUitl4() {
    }


    /**
     * @描述 提供一个公有的静态方法，当使用到该方法时，才会创建insane
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-07-10
     * @修改人和其它信息
     */
    public static synchronized SingletonClassUitl4 getInstance(){
        if (instance == null){
            instance = new SingletonClassUitl4();
        }
        return instance;
    }

}
