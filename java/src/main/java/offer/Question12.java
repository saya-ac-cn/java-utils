package offer;

/**
 * @Title: Question12
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-01-11 23:08
 * @Description:题目：给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。保证base和exponent不同时为0
 */

public class Question12 {

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(15));
        System.out.println(Integer.toBinaryString(16));
        System.out.println("15 & 1:"+(15 & 1));
        System.out.println("15 & 1:"+Integer.toBinaryString((15 & 1)));
        System.out.println("16 & 1:"+(16 & 1));
        System.out.println("16 & 1:"+Integer.toBinaryString((16 & 1)));
    }

    /**
     * @param base     底数
     * @param exponent 指数
     * @return
     */
    public double Power(double base, int exponent) {
        /**
         * 可能的情形：
         * base == 0 && exponent > 0 ->0
         * base == 0 && exponent == 0 ->未定义
         * base == 0 && exponent < 0 ->异常
         * base != 0 && exponent > 0 ->乘方
         * base != 0 && exponent == 0 ->1
         * base != 0 && exponent > 0 ->倒底数，反指数
         * 当n为偶数，a^n =（a^n/2）*（a^n/2）
         * 当n为奇数，a^n = a^[(n-1)/2] * a^[(n-1)/2] * a
         */
        if (base == 0) {
            if (exponent > 0) {
                return 0;
            } else if (exponent == 0) {
                return 0;
            } else {
                throw new RuntimeException();
            }
        } else {
            if (exponent > 0) {
                return power(base, exponent);
            } else if (exponent == 0) {
                return 1;
            } else {
                return 1 / power(base, -exponent);
            }
        }
    }

    public double power(double base, int exp) {
        if (exp == 1) {
            return base;
        }
        // 利用计算机位运算判断是否为奇数
        // 15 & 1 = 1
        // 16 & 1 = 0
        if ((exp & 1) == 0) {
            double tmp = power(base, exp >> 1);
            return tmp * tmp;
        } else {
            double tmp = power(base, (exp - 1) >> 1);
            return tmp * tmp * base;
        }
    }


}
