package ac.cn.saya.func;

import org.junit.Test;

import java.util.function.*;

/**
 * @Title: FunctionUtilTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-06-07 21:55
 * @Description:
 * Function-函数接口
 * Function<T,R> 接收一个参数并返回结果的函数
 * BiFunction<T,U,R> 接受两个参数并返回结果的函数
 *
 * DoubleFunction<R> 接收一个double类型的参数并返回结果的函数
 * DoubleToIntFunction 接收一个double类型的参数并返回int结果的函数
 * DoubleToLongFunction 接收一个double类型的参数并返回long结果的函数
 *
 * IntFunction<R> 接收一个int类型的参数并返回结果的函数
 * IntToDoubleFunction 接收一个int类型的参数并返回double结果的函数
 * IntToLongFunction 接收一个int类型的参数并返回long结果的函数
 *
 * LongFunction<R> 接收一个long类型的参数并返回结果的函数
 * LongToDoubleFunction 接收一个long类型的参数并返回double结果的函数
 * LongToIntFunction 接收一个long类型的参数并返回int结果的函数
 *
 * ToDoubleBiFunction<T,U> 接收两个参数并返回double结果的函数
 * ToDoubleFunction<T> 接收一个参数并返回double结果的函数
 *
 * ToIntBiFunction<T,U> 接收两个参数并返回int结果的函数
 * ToIntFunction<T> 接收一个参数并返回int结果的函数
 *
 * ToLongBiFunction<T,U> 接收两个参数并返回long结果的函数
 * ToLongFunction<T> 接收一个参数并返回long结果的函数
 *
 */

public class FunctionUtilTest {

    /**
     * @描述 Function<T, R>
     *     接收一个参数并返回结果的函数
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-07
     * @修改人和其它信息
     * R  apply(T t)	将此参数应用到函数中
     * Function<T, R>  andThen(Function<? super R,? extends V> after)	返回一个组合函数，该函数结果应用到after函数中
     * Function<T, R>  compose(Function<? super V,? extends T> before)	返回一个组合函数，首先将入参应用到before函数，再将before函数结果应用到该函数中
     */
    @Test
    public void function(){
        Function<String, String> function = a -> a + " Jack!";
        System.out.println(function.apply("Hello"));//Hello Jack!

        Function<String, String> function1 = a -> a + " Bob!";
        String greet = function.andThen(function1).apply("Hello");// 把function1加入到function后
        System.out.println(greet); // Hello Jack! Bob!

        String greet1 = function.compose(function1).apply("Hello");// 把function1加入到function前
        System.out.println(greet1); // Hello Bob! Jack!
    }

    /**
     * @描述 BiFunction 接受两个参数并返回结果的函数
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-07
     * @修改人和其它信息
     * R apply(T t, U u) 将参数应用于函数执行
     * BiFunction<T,U,V> andThen(Function<? super R,? extends V> after) 返回一个组合函数，after函数应用于该函数之后
     */
    @Test
    public void biFunction(){
        BiFunction<String,String,String> fun1 = (a, b) -> a +":"+b;
        System.out.println(fun1.apply("127.0.0.1","8080"));

        Function<String,String> fun2 = a -> a + "/api";
        System.out.println(fun1.andThen(fun2).apply("/lab","/user"));
    }

    /**
     * @描述 DoubleFunction<R> 接收一个double类型的参数并返回结果的函数
     * @参数  []
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-07
     * @修改人和其它信息
     * R apply(double value) 根据给定参数执行函数
     */
    @Test
    public void doubleFunction(){
        DoubleFunction<Boolean> function = x -> {
            if (x > 60){
                return true;
            }else{
                return false;
            }
        };
        System.out.println(function.apply(61));
    }

     /**
      * @描述 DoubleToIntFunction 接收一个double类型的参数并返回int结果的函数
      * @参数
      * @返回值
      * @创建人  saya.ac.cn-刘能凯
      * @创建时间  2019-06-07
      * @修改人和其它信息
      * int applyAsInt(T t) 根据给定参数执行函数
      */
    @Test
    public void doubleToIntFunction(){
        DoubleToIntFunction function = x -> {
            if (x > 10){
                return (new Double(x - 10)).intValue();
            }else {
                return 0;
            }
        };
        System.out.println(function.applyAsInt(12));
    }

    /**
     * @描述 DoubleToLongFunction 接收一个double类型的参数并返回long结果的函数
     * @参数  []
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-07
     * @修改人和其它信息
     * int applyAsLong(T t) 根据给定参数执行函数
     */
    @Test
    public void doubleToLongFunction(){
        DoubleToLongFunction function = x -> {
            if (x > 10){
                return (new Double(x - 10)).longValue();
            }else {
                return 0;
            }
        };
        System.out.println(function.applyAsLong(12));
    }

    /**
     * @描述 ToDoubleFunction<T> 接收一个参数并返回double结果的函数
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-07
     * @修改人和其它信息
     * double applyAsDouble(T t) 根据给定的参数执行函数
     */
    @Test
    public void toDoubleFunction(){
        ToDoubleFunction<Double> function = x -> {
            if (x >100){
                return x - 100;
            }else {
                return 0;
            }
        };
        System.out.println(function.applyAsDouble(90.0));
    }

    /**
     * @描述 ToDoubleBiFunction<T,U> 接收两个参数并返回double结果的函数
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-07
     * @修改人和其它信息
     * double applyAsDouble(T t, U u) 根据给定的参数执行函数
     */
    @Test
    public void toDoubleBiFunction(){
        ToDoubleBiFunction<Integer,Integer> function = (a, b) -> a.doubleValue() + b.doubleValue();
        System.out.println(function.applyAsDouble(12,45));
    }



}
