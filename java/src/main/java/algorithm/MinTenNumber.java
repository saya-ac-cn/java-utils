package algorithm;

import java.util.Arrays;

/**
 * @Title: MinTenNumber
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-12-13 21:00
 * @Description: 题目：求一亿个数里面最小的10个数
 * 首先建立节点个数为10的最大堆，然后考虑每一个新的值，让他和堆顶比较，比堆顶大的元素直接抛弃，如果比堆顶小的数字，让他替换堆顶，然后调整堆。
 */
public class MinTenNumber {

    public static void main(String[] args) {
        /**
         * 只要array[k]>array[2k] && array[k]>array[2k+1]那就是最大堆了，
         * 此外，这里暂时用20个数代替1亿个
         */
        int[] array = {0, 1, 2, 3, 4, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 6, 5};
        // 建立10个数的最大堆
        builMaxHeap(array,10);
        System.out.println(Arrays.toString(array));
        for(int i=11;i<array.length;i++){
            //如果这个元素小于堆顶，和堆顶交换，然后重新调整堆
            if(array[i]<array[0]){
                swap(array, i, 0);
                heapify(array, 0, 10);
            }

        }
        System.out.println(Arrays.toString(array));
        System.out.println("最小的10个数字为：");
        for(int i=0;i<10;i++){
            System.out.print(array[i]+" ");
        }
    }

    /**
     * 构建大顶堆
     * @param arry
     * @param len
     */
    public static void  builMaxHeap(int[] arry,int len){
        // 从最后一个非叶节点开始向前遍历，调整节点性质，使之成为大顶堆
        for (int i=(int)Math.floor(len/2)-1;i>=0;i--){
            heapify(arry,i,len);
        }
    }

    public static void heapify(int[] arr,int i,int len){
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

    public static void swap(int[] arry, int i, int j) {
        int temp = arry[i];
        arry[i] = arry[j];
        arry[j] = temp;
    }
}
