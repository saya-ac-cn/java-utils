package ac.cn.saya.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Title: ShellSort
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-11-27 20:52
 * @Description:
 * 希尔排序
 */

public class ShellSort {

    @Test
    public void test(){
        int arr[] = {3, 9, -1, 10, 20};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void sort(int[] arry){
        int temp = 0;
        // 通过循环逐步缩减步长值
        for (int stemp = arry.length/2; stemp > 0 ; stemp/=2) {
            // 遍历各组
            for (int i = stemp; i < arry.length; i++) {
                // 遍历各组中所有的元素，共stemp组，步长stemp
                for (int j = i-stemp; j >= 0 ; j-=stemp) {
                    if (arry[j] > arry[j+stemp]){
                        temp = arry[j];
                        arry[j] = arry[j+stemp];
                        arry[j+stemp] = temp;
                    }
                }
            }
        }
    }

}
