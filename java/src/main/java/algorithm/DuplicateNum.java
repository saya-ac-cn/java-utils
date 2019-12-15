package algorithm;

/**
 * @Title: DuplicateNum
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-12-14 21:07
 * @Description:题目： 有 1 亿个数字，其中有 2 个是重复的，快速找到它，时间和空间要最优
 * https://www.cnblogs.com/AndyJee/p/4693099.html
 * 思路：
 * 1）排序
 * 将数组排序，然后扫描排序后的数组即可。
 * 时间复杂度：O(nlogn)，空间复杂度:O(1)
 * 2）哈希表
 * 从头到尾扫描数组，每扫描到一个数字，判断该数字是否在哈希表中，如果该哈希表还没有这个数字，那么加入哈希表，如果已经存在，则返回该数字（LinkedList）；
 * 时间复杂度：O(n)，空间复杂度:O(n)
 * 3）交换
 * 0~n-1正常的排序应该是A[i]=i；因此可以通过交换的方式，将它们都各自放回属于自己的位置；
 * 从头到尾扫描数组A，当扫描到下标为i的数字m时，首先比较这个数字m是不是等于i，
 * 如果是，则继续扫描下一个数字；
 * 如果不是，则判断它和A[m]是否相等，如果是，则找到了第一个重复的数字（在下标为i和m的位置都出现了m）；如果不是，则把A[i]和A[m]交换，即把m放回属于它的位置；
 * 重复上述过程，直至找到一个重复的数字；
 * 时间复杂度：O(n)，空间复杂度：O(1)
 */

public class DuplicateNum {

    public static void duplicate(int[] array){
        int len,temp;
        if (null == array || (len=array.length) <= 1){
            System.out.println("can not find");
            return;
        }
        for (int i = 0; i < len; i++) {
            while (array[i] != i){
                if (array[i] == array[array[i]]){
                    System.out.println("duplicate number:"+array[i]);
                    return;
                }
                temp = array[i];
                array[i] = array[temp];
                array[temp] = temp;
            }
        }
        System.out.println("can not find");
        return;
    }

    public static void main(String[] args) {
        int[] array = {0, 1, 2, 3, 4, 7, 8, 9, 10, 11, 12, 19, 13, 14, 15, 16, 17, 18, 19, 20, 6, 5};
        duplicate(array);
    }

}
