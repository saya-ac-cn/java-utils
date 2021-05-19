package ac.cn.saya.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Title: SelectSort
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-11-26 21:47
 * @Description:
 * 选择排序
 */

public class SelectSort {

    @Test
    public void test(){
        int arr[] = {11, 6, 8, 5, 4, 7, 2, 0, 3, 1, 12, 13, 14, 15, 16, 17, 18, 19, 20, 10, 9};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        sort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void sort(int[] arry){
        int minIndex = 0;
        int minValue = 0;
        for (int i = 0; i < arry.length; i++){
            minIndex = i;
            minValue = arry[i];
            for (int j = i+1; j<arry.length;j++){
                if (minValue > arry[j]){
                    minIndex = j;
                    minValue = arry[j];
                }
            }
            if (minIndex != i){
                arry[minIndex] = arry[i];
                arry[i] = minValue;
            }
        }
    }

    public static void sort2(int array[]){
        for (int i = 0; i < array.length; i++) {
            int maxValue = array[i];
            int maxIndex = i;
            for (int j = i+1; j < array.length; j++) {
                if (maxValue < array[j]){
                    maxIndex = j;
                    maxValue = array[maxIndex];
                }
            }
            if (maxIndex != i){
                int temp = array[i];
                array[i] = maxValue;
                array[maxIndex] = temp;
            }
        }
    }

}
