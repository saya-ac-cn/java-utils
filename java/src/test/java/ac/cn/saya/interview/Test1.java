package ac.cn.saya.interview;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Title: Test1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-05-30 13:26
 * @Description:
 * https://developer.aliyun.com/article/757502
 */

public class Test1 {

    @Test
    public void question1() {
        float f_var = 4620.125f;
        double d_var = 4620.125d;
        // true
        System.out.println((f_var - d_var) == 0.0);
    }

    @Test
    public void question2() {
        double var1 = 0.8;
        double var2 = 0.7;
        double var3 = 0.6;
        double result1 = var1 - var2;
        double result2 = var2 - var3;
        // result1 和 result2 并不相等 double不适合用于精度高的计算，如果追求高精度建议采用BigDecimal
        System.out.println(result1);
        System.out.println(result2);
    }

    @Test
    public void question3() {
        Question3OverLoad overLoad = new Question3OverLoad();
        // overLoad.print(null)编译出错
        //overLoad.print(null);
    }

    @Test
    public void question4() {
        Question4OverLoad overLoad = new Question4OverLoad();
        // 默认6 是int 型， 但此类没有int，因故，将调用double，重载匹配原则，int double Integer Double
        overLoad.print(6);
    }

    @Test
    public void question5() {
        // 直接抛出空指针异常
        String var = null;
        /**
         * 在switch语句中，表达式的值不能是null，否则会在运行时抛出NullPointerException。
         * 在case子句中也不能使用null，否则会出现编译错误。
         * 基本数据类型：byte, short, char, int
         * 包装数据类型：Byte, Short, Character, Integer
         * 枚举类型：Enum
         * 字符串类型：String（Jdk 7+ 开始支持）
         */
        switch (var) {
            case "1":
                System.out.println("1");
                break;
            case "2":
                System.out.println("1");
                break;
            case "null":
                System.out.println("1");
                break;
            default:
                System.out.println("1");
                break;
        }
    }

    @Test
    public void question6() {
        /**
         * Double和Float 数据类型支持无穷大
         * https://blog.csdn.net/Fine_Cui/article/details/106320969
         * 参见源码：
         * double POSITIVE_INFINITY = 1.0 / 0.0; //正无穷大
         * double NEGATIVE_INFINITY = -1.0 / 0.0; //负无穷大
         * double NaN = 0.0d / 0.0; //非数值类型
         */
        // 输出Infinity
        System.out.println(1.0 / 0);
        /**
         * 但下面的写法就会出现异常
         */
        System.out.println(1 / 0);
    }

    @Test
    public void question7() {
        // NaN 解析见上
        System.out.println(0.0 / 0.0);
    }

    @Test
    public void question8() {
        /**
         * 本题考查类的实例化顺序：
         * 父类静态变量、
         * 父类静态代码块、
         * 子类静态变量、
         * 子类静态代码块、
         * 父类非静态变量（父类实例成员变量）、
         * 父类构造函数、
         * 子类非静态变量（子类实例成员变量）、
         * 子类构造函数。
         *
         * 依次输出：
         * static parent.
         * static child.
         * Parent class.
         * Question8LoadOrderParent
         * child class.
         * Question8LoadOrder
         */
        new Question8LoadOrder();
    }

    @Test
    public void question9() {
        Question9 question = new Question9();
        /**
         * 考查 形参 和实参的传递 ，以及字符串常量池
         */
        question.test();
    }

    @Test
    public void question10() {
        /**
         * 考查类的加载顺序
         * child-method1null
         * child-method2null
         * child-method1child
         * child-method2child
         */
        new Question10();
    }

    @Test
    public void question11() {
        try {
            throw new IOException();
        }catch (FileNotFoundException e){
            System.out.println("file not found");
        }catch (IOException e) {
            System.out.println("IOException");
        }catch (Exception e){
            System.out.println("Exception");
        }
    }

    @Test
    public void question12(){
        System.out.println("i++");
        int i = 0;
        System.out.println(i++);
        int j = 0;
        System.out.println(j++);
        System.out.println(j);
        System.out.println("++i");
        int i1 = 0;
        System.out.println(++i1);
        int j2 = 0;
        System.out.println(++j2);
        System.out.println(j2);
        int count = 0;
        for (int k = 0; k < 10; k++) {
            /**
             *  iload 5 //加载count到操作数栈，操作数栈为5
             *  iinc 5 by 1 //count自增，count=1
             *  istore 5 //把操作数栈的数据存到局部变量表的count里，count=0
             *  1.执行count=count（count=0）; JVM把count的值（其值为0）拷贝到临时变量区。   
             *  2.再执行count++;  ，
             *  3.当count++运行完毕，存存放在临时变量区的count=0的拷贝值（一直从未改变过）又赋给了count。所以最后结束，count还是原来的最初值。
             */
            count = count++;
        }
        System.out.println(count);
    }

    @Test
    public void question18(){
        System.out.println(Math.round(-18.5));
    }

    @Test
    public void question19(){
        /**
         * 在Java中，整形类型是有范围的，最大值为Integer.MAX_VALUE，即2147483647，最小值为Integer.MIN_VALUE即-2147483648。
         * 对整形最大值加1，2147483648（越界了），那么此时值为多少呢？结果是-2147483648
         */
        System.out.println(Integer.MAX_VALUE+1 < Integer.MAX_VALUE);
    }

}

class Question3OverLoad {
    public void print(String s) {
        System.out.println("String=" + s);
    }

    public void print(Integer i) {
        System.out.println("Integer=" + i);
    }
}

class Question4OverLoad {
    public void print(double s) {
        System.out.println("double=" + s);
    }

    public void print(Integer i) {
        System.out.println("Integer=" + i);
    }
}

class Question8LoadOrderParent {
    public Question8LoadOrderParent() {
        System.out.println("Question8LoadOrderParent");
    }

    {
        System.out.println("Parent class.");
    }

    static {
        System.out.println("static parent.");
    }

}

class Question8LoadOrder extends Question8LoadOrderParent {
    public Question8LoadOrder() {
        System.out.println("Question8LoadOrder");
    }

    {
        System.out.println("child class.");
    }

    static {
        System.out.println("static child.");
    }

}

class Question9 {
    String s = new String("678");
    char[] ch = {'a', 'b', 'c'};
    String a = new String("123");

    public void change(String str, char ch1[]) {
        // String 虽说是引用类型，但考虑到字符串常量池的作用，这里依然不变这里修改了形参，java里面只有值传递
        str = "9999";
        ch1[1] = 'd';
    }

    public void change(String var) {
        var = "121212";
    }

    public void test() {
        change(s, ch);
        change(a);
        System.out.println(s + " and " + String.valueOf(ch));
        System.out.println(a);
    }

}

class Question10Parent {
    private String name = "parent";

    public void method1() {
        System.out.println("parent-method1" + name);
    }

    public void method2() {
        System.out.println("parent-method2" + name);
    }

    public Question10Parent() {
        method1();
        method2();
    }

}

class Question10 extends Question10Parent {
    private String name = "child";

    public void method1() {
        System.out.println("child-method1" + name);
    }

    public void method2() {
        System.out.println("child-method2" + name);
    }

    public Question10() {
        super();
        method1();
        method2();
    }

}