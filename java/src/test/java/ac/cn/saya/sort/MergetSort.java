package ac.cn.saya.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Title: MergetSort
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-11-28 22:32
 * @Description:归并排序
 */

public class MergetSort {

    @Test
    public void test() {
        int arr[] = {3, 1, -1, 10, 20};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arry, int left, int right) {
        if (left == right) {
            return;
        }
        //中间索引
        int mid = left + ((right - left) >> 1);
        //向左递归进行分解
        sort(arry, left, mid);
        //向右递归进行分解
        sort(arry, mid + 1, right);
        //合并
        merge(arry, left, mid, right);
    }

    public static void merge(int[] arry, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = 0;
        int minIndex = left;
        int maxIndex = mid + 1;
        // 比较左右两部分的元素，那个小，就把元素填入temp中
        while (minIndex <= mid && maxIndex <= right) {
            temp[i++] = arry[minIndex] < arry[maxIndex] ? arry[minIndex++] : arry[maxIndex++];
        }
        // 上面的循环退出后，把剩余的元素依次填入到temp中
        // 以下两个while只有一个会执行
        while (minIndex <= mid) {
            temp[i++] = arry[minIndex++];
        }
        while (maxIndex <= right) {
            temp[i++] = arry[maxIndex++];
        }
        // 把最终的排序的结果复制给原数组
        for (i = 0; i < temp.length; i++) {
            arry[left + i] = temp[i];
        }
    }

}
