package ac.cn.saya.juc.volatiles;

/**
 * @Title: CompareAndSwapTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-04-20 22:58
 * @Description:
 * CAS算法
 */

public class CompareAndSwapTest {

    public static void main(String[] args){
        final CompareAndSwap cas = new CompareAndSwap();
        for(int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int expectedValue = cas.get();
                    boolean b = cas.compareAndSet(expectedValue,(int)(Math.random()*101));
                    System.out.println(b);
                }
            }).start();
        }
    }
}

class CompareAndSwap{
    private int value;

    // 获取内存值
    public synchronized int get(){
        return value;
    }

    // 比较&设置
    public synchronized int compareAndSwap(int expectedvalue,int newValue){
        int oldValue = value;
        if(oldValue == expectedvalue){
            this.value = newValue;
        }
        return oldValue;
    }

    // 外部设置
    public synchronized boolean compareAndSet(int expectedValue,int newValue){
        return expectedValue == compareAndSwap(expectedValue,newValue);
    }
}
