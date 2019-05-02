package ac.cn.saya.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @Title: LambdaUtil2
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-01 22:28
 * @Description:
 * 1、举例：（o1，o2）-> Integer.copare(o1,o2)
 * 2、格式：
 *      -> : Lambda操作符 或 箭头操作符
 *      -> 左边：Lambda形参列表（其实就是方法中的形参列表）
 *      -> 右边：Lambda体（其实就是重写的抽象方法的方法体）
 * 3、本质是接口的实例
 */

public class LambdaUtil2 {

    /**
     * @描述 方式1：无参数，无返回值
     * @参数  []
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-05-01
     * @修改人和其它信息
     */
    @Test
    public void test1(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("线程执行");
            }
        };
        r1.run();
        System.out.println("------------");
        Runnable r2 = () -> System.out.println("线程执行");
        r2.run();
    }

    /**
     * @描述 有参无返回值
     * @参数  []
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-05-01
     * @修改人和其它信息
     */
    @Test
    public void test2(){
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("测试~");
        System.out.println("-------------");
        Consumer<String> con2 = (String s) -> { System.out.println(s);};
        con2.accept("测试lambda");
    }

    /**
     * @描述 数据类型可以省略，因为可有编译器推断得出，成为类型推断
     * @参数  []
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-05-01
     * @修改人和其它信息
     */
    @Test
    public void test3(){
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("测试~");
        System.out.println("-------------");
        Consumer<String> con2 = (s) -> { System.out.println(s);};
        con2.accept("测试lambda");
    }

    /***
     * @描述 lambda若只需要一个参数时，参数ed小括号可以省略
     * @参数  []
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-05-01
     * @修改人和其它信息
     */
    @Test
    public void test4(){
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("测试~");
        System.out.println("-------------");
        Consumer<String> con2 = s -> { System.out.println(s);};
        con2.accept("测试lambda");
    }

    /**
     * @描述 lambda 需要有两个或以上的参数时，多条执行语句，并且可以有返回值
     * @参数  []
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-05-01
     * @修改人和其它信息
     */
    @Test
    public void test5(){
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1,Integer o2) {
                System.out.println(o1.compareTo(o2) > 0 ? ">=" : '<');
                return o1.compareTo(o2);
            }
        };
        com1.compare(11,12);
        System.out.println("-------------");
        Comparator<Integer> com2 = (o1,o2) -> {
            System.out.println(o1.compareTo(o2) > 0 ? ">=" : '<');
            return o1.compareTo(o2);
        };
        com2.compare(12,23);
    }

    /***
     * @描述 当lambda只有一条语句时，return与大括号若有，都可省略
     * @参数  []
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-05-01
     * @修改人和其它信息
     */
    @Test
    public void test6(){
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1,Integer o2) {
                return o1.compareTo(o2);
            }
        };
        System.out.println(com1.compare(11,12) > 0 ? ">=" : '<');
        System.out.println("-------------");
        Comparator<Integer> com2 = (o1,o2) ->  o1.compareTo(o2);
        System.out.println(com2.compare(14,12) > 0 ? ">=" : '<');
    }

    /**
     * @描述 调用自己的函数式接口
     * @参数  []
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-05-02
     * @修改人和其它信息
     */
    @Test
    public void test7(){
        IPrint print = (s) -> System.out.println(s);
        print.print("diy functionInterface");
    }

}
