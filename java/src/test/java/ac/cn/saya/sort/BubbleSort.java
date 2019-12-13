package ac.cn.saya.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Title: BubbleSort
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-11-26 21:36
 * @Description:
 * 冒泡排序
 */

public class BubbleSort {

    @Test
    public void test(){
        int arr[] = {11, 6, 8, 5, 4, 7, 2, 0, 3, 1, 12, 13, 14, 15, 16, 17, 18, 19, 20, 10, 9};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void sort(int[] arry){
        int temp = 0;
        for (int i = 0; i < arry.length-1; i++){
            for (int j = 0;j < arry.length-1-i;j++){
                if (arry[j]>arry[j+1]){
                    temp = arry[j];
                    arry[j] = arry[j+1];
                    arry[j+1] = temp;
                }
            }
        }
    }

}
