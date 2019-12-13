package ac.cn.saya.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Title: InsertSort
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-11-26 21:58
 * @Description:插入排序
 */

public class InsertSort {

    @Test
    public void test(){
        int arr[] = {11, 6, 8, 5, 4, 7, 2, 0, 3, 1, 12, 13, 14, 15, 16, 17, 18, 19, 20, 10, 9};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 1 3 4 | 2 temp = 2 j = 3
     * 1 3 4 | 4 temp = 2 j = 3
     *
     * 1 3 3 | 4 temp = 2 j = 2
     * 1 2 3 | 4 temp = 2 j = 1
     * @param arry
     */
    public void sort(int[] arry) {
        int temp = 0;
        for (int i = 1; i < arry.length; i++) {
            //待排元素小于有序序列的最后一个元素时，向前插入
            if (arry[i] < arry[i - 1]) {
                temp = arry[i];
                for (int j = i; j >= 0; j--) {
                    if (j > 0 && arry[j - 1] > temp) {
                        arry[j] = arry[j-1];
                    } else {
                        arry[j] = temp;
                        break;
                    }
                }
            }
        }
    }

}
