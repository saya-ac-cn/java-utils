package ac.cn.saya.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Title: HeapSort
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-11-29 21:08
 * @Description:堆排序
 * https://blog.csdn.net/qq_28063811/article/details/93034625
 */

public class HeapSort {

    @Test
    public void test() {
        int arr[] = {3, 1, -1, 10, 20};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    protected void sort(int[] arry) {
        if (null == arry || arry.length == 0){
            return;
        }
        int len = arry.length;
        // 构建大顶堆，其实就是把待排序序列，变成一个大顶堆结构的数组
        builMaxHeap(arry,len);
        // 交换堆顶和当前末尾的节点，重置大顶堆
        for (int i = len - 1; i > 0; i--) {
            swap(arry,0,i);
            len--;
            heapify(arry,0,len);
        }
    }


    protected void  builMaxHeap(int[] arry,int len){
        // 从最后一个非叶节点开始向前遍历，调整节点性质，使之成为大顶堆
        for (int i=(int)Math.floor(len/2)-1;i>=0;i--){
            heapify(arry,i,len);
        }
    }

    protected void heapify(int[] arr,int i,int len){
        // 先根据堆的性质，找出它左右节点的索引，如果不懂，自己画图脑补
        int leftIndex = 2 * i + 1;
        int rightIndex = 2 * i + 2;
        int largestIndex= i;
        if (leftIndex < len && arr[leftIndex]>arr[largestIndex]){
            // 如果有左节点，并且左节点的值更大，更新最大的索引
            largestIndex=leftIndex;
        }
        if (rightIndex < len && arr[rightIndex]>arr[largestIndex]){
            // 如果有右节点，并且右节点的值更大，更新最大的索引
            largestIndex=rightIndex;
        }
        if (largestIndex != i){
            // 如果最大值不是当前非叶子节点，那么就把当前节点和最大值的子节点互换
            swap(arr, i,largestIndex);
            // 互换后，子节点的值可能变了，如果该子节点也有自己的子节点，仍需要递归调整
            heapify(arr, largestIndex, len);
        }
    }

    protected void swap(int[] arry, int i, int j) {
        int temp = arry[i];
        arry[i] = arry[j];
        arry[j] = temp;
    }

}
