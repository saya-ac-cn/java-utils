package offer;

/**
 * @Title: Question7
 * @ProjectName spyder
 * @Description: TODO
 * @Author Administrator
 * @Date: 2020/1/2 0002 15:10
 * @Description:题目：大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。n<= 39
 */

public class Question7 {

    public static void main(String[] args) {
        System.out.println(Fibonacci(3));
    }

    /**
     * 经典求法:
     * * 除了第一项和第二项，所有的数列的值都是前一项和前一项的前一项的加和，转换成函数也就是f(n) = f(n-1) + f(n-2)
     * * 递归n次，时间复杂度O(2^n)
     * 顺序求法:
     * 因为斐波那契数列可以从左到右顺序的求出每一项的值，因此只需要顺序计算到n项即可，时间复杂度为O(n)的，我们可以把它看成在单链表的最后插入一个和倒数第二个指针指向的值来决定的
     * @param n
     * @return
     */
    public static int Fibonacci(int n) {
        // f(0) = 0,f(1) = 1,f(2)=f(1)+f(0)=1
        if (n < 0){
            return 0;
        }else if (1 == n){
            return 1;
        }else {
            int first = 0,second = 1,result = 0;
            for (int i = 2;i <= n;i++){
                result = first + second;
                first = second;
                second = result;
            }
            return result;
        }
    }

}
