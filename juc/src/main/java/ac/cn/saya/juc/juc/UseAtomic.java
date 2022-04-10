package ac.cn.saya.juc.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title: UseAtomic
 * @ProjectName java-utils
 * @Author saya
 * @Date: 2022/4/10 22:50
 * @Description: TODO
 * 多线程环境使用原子类保证线程安全
 */

public class UseAtomic {

    private AtomicInteger count = new AtomicInteger(0);

    public int getCount() {
        return count.get();
    }

    public void setCount() {
        count.getAndIncrement();
    }

}
