package ac.cn.saya.func;

import org.junit.Test;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @Title: OperatorUtilTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-06-09 22:33
 * @Description:
 * Operator-操作员
 */

public class OperatorUtilTest {

    /**
     * UnaryOperator<T> 提供单个参数，并且返回一个与输入参数类型一致的结果
     * BinaryOperator<T> 提供两个参数，并且返回结果与输入参数类型一致的结果
     * DoubleBinaryOperator 提供两个double参数并且返回double结果
     * DoubleUnaryOperator 提供单个double参数并且返回double结果
     * IntBinaryOperator 提供两个int参数并且返回int结果
     * IntUnaryOperator 提供单个int参数并且返回int结果
     * LongBinaryOperator 提供两个long参数并且返回long结果
     * LongUnaryOperator 提供单个long参数并且返回long结果
     */

    /**
     * @描述 UnaryOperator<T> 提供单个参数，并且返回一个与输入参数类型一致的结果
     * @参数  []
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-09
     * @修改人和其它信息
     * T apply(T t)	将给定参数应用到函数中
     * Function<T, R>  andThen(Function<? super R,? extends V> after)	返回一个组合函数，该函数结果应用到after函数中
     * Function<T, R>  compose(Function<? super V,? extends T> before)	返回一个组合函数，首先将入参应用到before函数，再将before函数结果应用到该函数中
     */
    @Test
    public void unaryOperator(){
        UnaryOperator<String> fun1 = str -> str+"-";
        System.out.println(fun1.apply("saya"));

        UnaryOperator<String> fun2 = str -> str + "!";
        System.out.println(fun1.andThen(fun2).apply("Hello"));//后
        System.out.println(fun1.compose(fun2).apply("Hello"));//前
    }

    /**
     * @描述 BinaryOperator 提供两个参数，并且返回结果与输入参数类型一致的结果
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-09
     * @修改人和其它信息
     * R apply(T t, U u) 根据给定参数执行函数
     * BiFunction<T,U,V> andThen(Function<? super R,? extends V> after)  返回一个组合函数，after应用于该函数之后
     */
    @Test
    public void binaryOperator(){
        BinaryOperator<String> fun = (a, b) -> a+b;
        System.out.println(fun.apply("hello","saya"));
        Function<String, String> fun2 = a -> a + ";";
        System.out.println(fun.andThen(fun2).apply("hello","saya"));//后
    }

}
