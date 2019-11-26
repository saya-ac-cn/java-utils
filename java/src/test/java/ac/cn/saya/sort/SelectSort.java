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
        int arr[] = {3, 9, -1, 10, 20};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        sort(arr);
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
}
