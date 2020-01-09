package offer;


/**
 * @Title: Question10
 * @ProjectName spyder
 * @Description: TODO
 * @Author Administrator
 * @Date: 2020/1/9 0009 16:36
 * @Description:题目：我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 */

public class Question10 {

    public static void main(String[] args){
        System.out.println(RectCover(5));
    }

    /**
     * 斐波那契数列
     * @param target
     * @return
     */
    public static int RectCover(int target) {
        if (target <= 2) {
            return target;
        }
        int first = 1, second = 2, third = 0;
        for (int i = 3; i <= target; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }

}
