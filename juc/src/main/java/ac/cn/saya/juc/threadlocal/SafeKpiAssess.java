package ac.cn.saya.juc.threadlocal;

import java.math.BigDecimal;

/**
 * 员工绩效考核
 * @Title: SafeKpiAssess
 * @ProjectName java-utils
 * @Description: TODO
 * @Author saya
 * @Date: 2022/2/9 22:25
 * @Description:
 */

/**
 * 员工绩效考核类
 */
class Kpi{

    /**
     * 员工绩效指标
     */
    private static ThreadLocal<BigDecimal> performance = ThreadLocal.withInitial(()->BigDecimal.ZERO);

    /**
     * 设置员工业绩
     * @param val
     */
    public void setPerformance(BigDecimal val){
        if (null != val){
            performance.set(val);
        }
    }

    /**
     * 获取员工业绩
     * @return
     */
    public BigDecimal getPerformance() {
        return performance.get();
    }

    /**
     * 删除员工业绩
     */
    public void delPerformance(){
        performance.remove();
    }
}

public class SafeKpiAssess {

    public static void main(String[] args) {
        Kpi kpi = new Kpi();
        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    kpi.setPerformance(BigDecimal.valueOf(i+1));
                }
                System.out.println(Thread.currentThread().getName()+"考核结果："+kpi.getPerformance());
            }finally {
                kpi.delPerformance();
            }
        },"员工A").start();
        new Thread(()->{
            try {
                for (int i = 0; i < 15; i++) {
                    kpi.setPerformance(BigDecimal.valueOf(i+1));
                }
                System.out.println(Thread.currentThread().getName()+"考核结果："+kpi.getPerformance());
            }finally {
                kpi.delPerformance();
            }
        },"员工B").start();
        new Thread(()->{
            try {
                for (int i = 0; i < 20; i++) {
                    kpi.setPerformance(BigDecimal.valueOf(i+1));
                }
                System.out.println(Thread.currentThread().getName()+"考核结果："+kpi.getPerformance());
            }finally {
                kpi.delPerformance();
            }
        },"员工C").start();
    }

}
