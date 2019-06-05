package ac.cn.saya.func;

import org.junit.Test;

import java.util.function.*;

/**
 * @Title: PredicateUtilTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-06-05 21:21
 * @Description:
 * Predicate-谓语 返回布尔值
 */

public class PredicateUtilTest {

    /**
     * @描述 对给定的输入参数执行操作，返回一个boolean类型的结果（布尔值函数）
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-05
     * @修改人和其它信息
     * boolean  test(T t) 根据给定的参数进行判断
     * Predicate<T>  and(Predicate<? super T> other) 返回一个组合判断，将other以短路并且的方式加入到函数的判断中
     * Predicate<T>  or(Predicate<? super T> other) 返回一个组合判断，将other以短路或的方式加入到函数的判断中
     * Predicate<T>  negate() 将函数的判断取反
     */
    @Test
    public void predicate(){
        Predicate<Integer> fun = number -> {
            return number != 0;
        };
        System.out.println(fun.test(10));
        System.out.println(fun.negate().test(10));
        fun = fun.and(number -> number != 0);
        System.out.println(fun.test(10));
        fun = fun.or(number -> number != 0);
        System.out.println(fun.test(10));
    }

    /**
     * @描述 对给定的两个输入参数执行操作，返回一个boolean类型的结果（布尔值函数）
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-05
     * @修改人和其它信息
     * boolean  test(T t, U u) 根据给定的两个输入参数进行判断
     * BiPredicate<T,U>  and(BiPredicate<? super T,? super U> other) 返回一个组合判断，将other以短路并且的方式加入到函数的判断中
     * BiPredicate<T,U>  or(BiPredicate<? super T,? super U> other) 返回一个组合判断，将other以短路或的方式加入到函数的判断中
     * BiPredicate<T,U>  negate() 将函数的判断取反
     */
    @Test
    public void biPredicate(){
        BiPredicate<Integer,Integer> fun = (a,b) -> a == b;
        System.out.println(fun.test(10,20));
        System.out.println(fun.negate().test(10,20));
        fun = fun.and((a,b) -> a == b);
        System.out.println(fun.test(1,1));
        fun = fun.or((a,b) -> a == b);
        System.out.println(fun.test(1,1));
    }

    /**
     * @描述 对给定的double参数执行操作，返回一个boolean类型的结果（布尔值函数）
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-05
     * @修改人和其它信息
     * boolean  test(double value) 根据给定的参数进行判断
     * DoublePredicate  and(DoublePredicate other) 返回一个组合判断，将other以短路并且的方式加入到函数的判断中
     * DoublePredicate  or(DoublePredicate other) 返回一个组合判断，将other以短路或的方式加入到函数的判断中
     * default DoublePredicate negate() 将函数的判断取反
     */
    @Test
    public void doublePredicate(){
        DoublePredicate fun = a -> a >= 60;
        System.out.println(fun.test(80));
        System.out.println(fun.negate().test(80));
        fun = fun.and(a -> a >= 60);
        System.out.println(fun.test(59));
        fun = fun.or(a -> a >= 60);
        System.out.println(fun.test(60));
    }

    /**
     * @描述 对给定的int输入参数执行操作，返回一个boolean类型的结果（布尔值函数）
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-05
     * @修改人和其它信息
     * 同上
     */
    @Test
    public void intPredicate(){
        IntPredicate fun = a -> a >= 60;
        System.out.println(fun.test(80));
        System.out.println(fun.negate().test(80));
        fun = fun.and(a -> a >= 60);
        System.out.println(fun.test(59));
        fun = fun.or(a -> a >= 60);
        System.out.println(fun.test(60));
    }

    /**
     * @描述 对给定的long参数执行操作，返回一个boolean类型的结果（布尔值函数）
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-05
     * @修改人和其它信息
     * 同上
     */
    @Test
    public void longPredicate(){
        LongPredicate fun = a -> a >= 60;
        System.out.println(fun.test(80));
        System.out.println(fun.negate().test(80));
        fun = fun.and(a -> a >= 60);
        System.out.println(fun.test(59));
        fun = fun.or(a -> a >= 60);
        System.out.println(fun.test(60));
    }

}
