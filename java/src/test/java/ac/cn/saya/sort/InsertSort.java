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
        sort2(arr);
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
            temp = arry[i];
            int j = 0;
            for (j = i-1; j >= 0; j--) {
                if (arry[j] < temp) {
                    arry[j+1] = arry[j];
                } else {
                    //arry[j+1] = temp;
                    break;
                }
//                if (j > 0 && arry[j - 1] > temp) {
//                    arry[j] = arry[j-1];
//                } else {
//                    arry[j] = temp;
//                    break;
//                }
            }
            if (j+1!=i){
                arry[j+1] = temp;
            }
        }
    }

    public void sort2(int[] array){
        for (int i = 1; i < array.length; i++) {
            int value = array[i];
            int index = i-1;
            while (index >= 0 && array[index]<value){
                array[index+1] = array[index];
                index--;
            }
            if (index+1!=i){
                array[index+1] = value;
            }
        }
    }

}
