package ac.cn.saya.singleton;

/**
 * @Title: SingletonUtil1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-10 20:45
 * @Description: 单例模式（饿汉式-静态变量）
 */

public class SingletonUtil1 {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println((SingletonClassUitl1.getInstance()).hashCode());
            }).start();
        }
    }

}

/**
 * @描述 饿汉式（静态变量）
 * @参数
 * @返回值
 * @创建人 saya.ac.cn-刘能凯
 * @创建时间 2019-07-10
 * @修改人和其它信息
 */
class SingletonClassUitl1 {

    /**
     * @描述 构造器私有化
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-07-10
     * @修改人和其它信息
     */
    private SingletonClassUitl1() {
    }

    /**
     * 本类内部创建对象实例
     */
    private final static SingletonClassUitl1 instance = new SingletonClassUitl1();

    /**
     * @描述 提供一个公有的静态方法，返回实例对象
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-07-10
     * @修改人和其它信息
     */
    public static SingletonClassUitl1 getInstance() {
        return instance;
    }

}
