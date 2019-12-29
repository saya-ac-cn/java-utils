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
 * https://www.cnblogs.com/miracleswgm/p/9199124.html
 */

public class QuickSort {

    @Test
    public void test() {
        int arr[] = {3, 1, -1, 10, 20};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        quick_sort(arr, 0, arr.length - 1);
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
        int temp = arr[left];

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

    void quick_sort(int arr[], int left, int right) {
        if (left > right)
            return;
        int j = partition_(arr, left, right);
        quick_sort(arr, left, j - 1);
        quick_sort(arr, j + 1, right);
    }

}
