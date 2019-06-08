package ac.cn.saya.func;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.function.*;

/**
 * @Title: SupplierUtilTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-06-08 21:32
 * @Description:
 * Supplier-供应商
 * Supplier<T> 不提供输入参数，但是返回结果的函数
 * BooleanSupplier 不提供输入参数，但是返回boolean结果的函数
 * DoubleSupplier 不提供输入参数，但是返回double结果的函数
 * IntSupplier 不提供输入参数，但是返回int结果的函数
 * LongSupplier 不提供输入参数，但是返回long结果的函数
 */

public class SupplierUtilTest {

    /**
     * @描述 Supplier 无需提供输入参数，返回一个T类型的执行结果
     * @参数  []
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-08
     * @修改人和其它信息
     * T  get()	获取结果值
     */
    @Test
    public void supplier(){
        Supplier<String> fuc = () -> {
            LocalDate localDate = LocalDate.now();
            return String.valueOf(localDate);
        };
        System.out.println(fuc.get());
    }

    /**
     * @描述 BooleanSupplier 不提供输入参数，但是返回boolean结果的函数
     * @参数  []
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-08
     * @修改人和其它信息
     * boolean getAsBoolean() 获取函数的执行结果
     */
    @Test
    public void booleanSupplier(){
        BooleanSupplier fuc = () -> {
            // Long millisecond = Instant.now().toEpochMilli();  // 精确到毫秒
            Long second = Instant.now().getEpochSecond();// 精确到秒
            System.out.println(second);
            if (second % 2 == 0){
                return true;
            }else{
                return false;
            }
        };
        System.out.println(fuc.getAsBoolean());
    }

    /**
     * @描述 DoubleSupplier 不提供输入参数，但是返回double结果的函数
     * @参数  []
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-08
     * @修改人和其它信息
     * Double getAsDouble 获取函数的执行结果
     */
    @Test
    public void doubleSupplier(){
        DoubleSupplier fun = () -> {
            YearMonth yearMonth = YearMonth.now();
            return yearMonth.lengthOfMonth();
        };
        System.out.println(fun.getAsDouble());
    }

    /**
     * @描述 IntSupplier 不提供输入参数，但是返回int结果的函数
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-08
     * @修改人和其它信息
     * Integer getAsInt  获取函数的执行结果
     */
    @Test
    public void intSupplier(){
        IntSupplier fun = () -> {
            LocalDate now = LocalDate.now();
            return now.getYear();
        };
        System.out.println(fun.getAsInt());
    }

    /**
     * @描述 LongSupplier 不提供输入参数，但是返回long结果的函数
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-08
     * @修改人和其它信息
     * Long getAsLong  获取函数的执行结果
     */
    @Test
    public void longSupplier(){
        LongSupplier fun = () -> {
            Long second = Instant.now().getEpochSecond();// 精确到秒
            return second;
        };
        System.out.println(fun.getAsLong());
    }

}
