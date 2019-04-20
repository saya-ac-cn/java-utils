package ac.cn.saya.juc.volatiles;

/**
 * @Title: VolatileUtil1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-04-20 21:58
 * @Description:
 * Volatile关键字
 * 可见性：当多个线程操作共享数据是时，可以保证内存中的数据可见
 *      相较于synchronized是一个较轻量级的同步策略
 * 1、Volatile 不具备互斥性
 * 2、Volatile 不能保证变量的原子性
 */

public class VolatileUtil1 {

    public static void main(String[] args){
        VolatileThreadUtil1 thread = new VolatileThreadUtil1();
        new Thread(thread).start();
        while (true){
            /// 加入synchronized 可以避免但效率低
//            synchronized (thread){
//                if(thread.isFlag()){
//                    System.out.println("------");
//                    break;
//                }
//            }
            if(thread.isFlag()){
                System.out.println("------");
                break;
            }

        }
    }

}

class VolatileThreadUtil1 implements Runnable{

    private volatile boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        }catch (InterruptedException e){

        }
        flag = true;
        System.out.println("标志位状态:"+flag);
    }
}