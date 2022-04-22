package ac.cn.saya.juc.juc;

/**
 * @Title: UnUseAotmic
 * @ProjectName java-utils
 * @Author saya
 * @Date: 2022/4/10 22:47
 * @Description: TODO
 * 多线程环境不使用原子类保证线程安全
 */

public class UnUseAotmic {

    private volatile int number = 0;

    // 读取
    public int getNumber() {
        return number;
    }

    // 写入加锁保证原子性
    public synchronized void setNumber() {
        number++;
    }
}
