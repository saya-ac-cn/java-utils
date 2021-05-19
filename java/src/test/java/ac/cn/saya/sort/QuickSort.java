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

    public static void sort2(int array[],int start,int end){
        // 左右下标
        int left = start;
        int right = end;
        // 中间值
        int middle = array[(start+end)/2];
        int temp = 0;
        // 让比middle大的数放到右侧，比middle小的数放到左侧
        while (left < right){
            // 左边一直找，直到找到比它小的数为止
            while (array[left] > middle){
                left = left + 1;
            }
            // 右边一直找，直到找到比它大的数为止
            while (array[right] < middle){
                right = right - 1;
            }
            // 当左右指针已经浮动到一起了时，说明左侧的数据都大于middle，右侧的数据都小于middle
            if (left >= right){
                break;
            }
            // 交换
            temp = array[left];
            array[left] = array[right];
            array[right] = temp;

        }
        // 说明左右段已经是有序的，在这里设置结束条件
        if (left == right){
            left = left + 1;
            right = right - 1;
        }
        // 向左递归
        if (start < right){
            sort2(array,start,right);
        }
        // 向右递归
        if (end > left){
            sort2(array,left,end);
        }
    }

}
