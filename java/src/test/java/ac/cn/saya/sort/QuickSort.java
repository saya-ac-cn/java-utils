package ac.cn.saya.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Title: QuickSort
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-11-27 21:13
 * @Description:快速排序
 */

public class QuickSort {

    @Test
    public void test() {
        int arr[] = {3, 1, -1, 10, 20};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        quick_sort_(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    protected void swap(int[] arry, int i, int j) {
        int temp = arry[i];
        arry[i] = arry[j];
        arry[j] = temp;
    }

    int partition_(int arr[], int left, int right){  //找基准数 划分
        int i = left + 1;
        int j = right;
        int temp = arr[(left+right)/2];

        while (i <= j) {
            while (arr[i] < temp) {
                i++;
            }
            while (arr[j] > temp) {
                j--;
            }
            if (i < j)
                swap(arr, i++, j--);
            else {
                i++;
            }
        }
        swap(arr, j, left);
        return j;

    }

    void quick_sort_(int arr[], int left, int right) {
        if (left > right)
            return;
        int j = partition_(arr, left, right);
        quick_sort_(arr, left, j - 1);
        quick_sort_(arr, j + 1, right);
    }

}
