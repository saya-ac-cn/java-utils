package offer;

/**
 * @Title: Question9
 * @ProjectName spyder
 * @Description: TODO
 * @Author Administrator
 * @Date: 2020/1/3 0003 11:21
 * @Description:题目：一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */

public class Question9 {

    public static void main(String[] args) {
        System.out.println(JumpFloorII(1));
        System.out.println(JumpFloorII(2));
        System.out.println(JumpFloorII(3));
    }

    /**
     * 当n=1时，结果为1；
     * 当n=2时，结果为2；
     * 当n=3时，结果为4；
     * 以此类推，我们使用数学归纳法不难发现，跳法f(n)=2^(n-1)。
     * @param target
     * @return
     */
    public static int JumpFloorII(int target) {
        if (0 == target){
            return 0;
        }
        int total = 1;
        for (int i = 1; i < target; i++) {
            total *= 2;
        }
        return total;
    }

}
