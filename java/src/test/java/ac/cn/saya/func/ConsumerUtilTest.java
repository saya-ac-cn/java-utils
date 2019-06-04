package ac.cn.saya.func;

import org.junit.Test;

import java.util.function.*;

/**
 * @Title: ConsumerUtilTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-06-04 21:07
 * @Description:
 * Consumer-消费者（接口）
 * https://blog.csdn.net/qq_29848473/article/details/79554472
 */

public class ConsumerUtilTest {

    @Test
    public void consumerTest () {
        consumerFunc(500, (x) -> System.out.println(x));
    }

    /**
     * @描述 Consumer<T>
     * @参数  [money, c]
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-04
     * @修改人和其它信息
     * 提供一个T类型的输入参数，不返回执行结果
     */
    public void consumerFunc (double money, Consumer<Double> c) {
        c.accept(money);
    }

    @Test
    public void biConsumerTest(){
        biConsumerFunc(10,20,(a,b) -> System.out.println(a+b));
    }

    /**
     * @描述 BiConsumer<T,U>
     * @参数  [a, b, c]
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-04
     * @修改人和其它信息
     * 提供两个自定义类型的输入参数，不返回执行结果
     */
    public void biConsumerFunc(int a, int b, BiConsumer<Integer,Integer> c){
        c.accept(a,b);
    }

    @Test
    public void doubleConsumerTest(){
        doubleConsumerFunc(90.5, a -> {
            if(a > 90){
                System.out.println("优秀");
            }else{
                System.out.println("垃圾");
            }
        });
    }

    /**
     * @描述 DoubleConsumer
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-04
     * @修改人和其它信息
     * 表示接受单个double值参数，但不返回结果的操作
     */
    public void doubleConsumerFunc(double a, DoubleConsumer c){
        c.accept(a);
    }

    @Test
    public void intConsumerTest(){
        intConsumer(1, a -> {
            if(a == 1){
                System.out.println("True");
            }else{
                System.out.println("False");
            }
        });
    }

    /**
     * @描述 IntConsumer
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-04
     * @修改人和其它信息
     * 表示接受单个int值参数，但不返回结果的操作
     */
    public void intConsumer(int a, IntConsumer c){
        c.accept(a);
    }

    @Test
    public void longConsumerTest(){
        LongConsumer c = (a) -> {
            System.out.println(a);
        };
        // 方式1运行
        longConsumer(8900,c);
        // 方式2运行
        c.accept(8000);
        // 方式3运行
        longConsumer(6000,a -> {
            System.out.println(a);
        });
    }

    /**
     * @描述 LongConsumer
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-04
     * @修改人和其它信息
     * 表示接受单个long值参数，但不返回结果的操作
     */
    public void longConsumer(long a, LongConsumer c){
        c.accept(a);
    }

    /**
     * @描述 ObjDoubleConsumer<T>
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-04
     * @修改人和其它信息
     * 表示接受object值和double值，但是不返回任何操作结果
     */
    @Test
    public void objDoubleConsumer(){
        ObjDoubleConsumer<String> doubleConsumer = (obj, doub)
                -> System.out.println(obj + doub);
        doubleConsumer.accept("金额￥：", 222.66);
        doubleConsumer.accept("金额$：", 1000);
    }

    /**
     * @描述 ObjIntConsumer<T>
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-04
     * @修改人和其它信息
     * 表示接受object值和int值，但是不返回任何操作结果
     */
    @Test
    public void objIntConsumer(){
        ObjIntConsumer<Boolean> c = (obj, x) ->{
            if(obj){
                System.out.println("生产线启用，值："+x+"有效");
            }else{
                System.out.println("生产线已关闭，值："+x+"无效");
            }
        };
        c.accept(true,1000);
        c.accept(false,2);
    }

    /**
     * @描述 ObjLongConsumer<T>
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-04
     * @修改人和其它信息
     * 表示接受object值和long值，但是不返回任何操作结果
     */
    @Test
    public void objLongConsumer(){
        ObjLongConsumer<String> c = (str,x) -> {
            System.out.println(x+"("+str+")");
        };
        c.accept("吨",60);
        c.accept("升",10);
        c.accept("千米",1000);
    }


}
