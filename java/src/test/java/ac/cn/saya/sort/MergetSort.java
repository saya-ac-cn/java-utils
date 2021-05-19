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

    public static void main(String[] args) {
        int array[] = {123,24,36,54,675,9,34325,235,43,654,7};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(array));
        int[] temp = new int[array.length];
        sort(array,0,array.length-1,temp);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] array,int left,int right,int[] temp){
        if (left < right){
            // 以中间为基准，进行左右划分
            int middle = (left + right) / 2;
            // 向左、右进行分解
            sort(array,left,middle,temp);
            System.out.println("左边");
            sort(array,middle+1,right,temp);
            // 合并
            merge(array,left,middle,right,temp);
        }
    }

    /**
     * 合并方法
     * @param array 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param middle 中间索引
     * @param right 右边有序序列的初始索引
     * @param temp 中转临时数组
     */
    public static void merge(int[] array,int left,int middle,int right,int[] temp){
        // 左边有序序列的初始索引
        int i =left;
        // 右边有序数组的初始索引
        int j = middle + 1;
        // 指示temp数组的索引
        int t = 0;
        // 先把左右两边有序的数据进行两两比较，并填充到temp数组
        // 直到有一边处理完毕为止
        while (i <= middle && j <= right){
            if (array[i] >= array[j]){
                // 如果左边的有序序列的当前元素，大于右边的有序序列的当前元素
                // 将左边的当前元素填充到temp数组，并移动下标
                temp[t] = array[i];
                t += 1;
                i += 1;
            }else {
                // 反之
                temp[t] = array[j];
                t += 1;
                j += 1;
            }
        }
        // 将余留的数据填入到temp
        while (i <= middle){
            temp[t] = array[i];
            t += 1;
            i += 1;
        }
        while (j <= right){
            temp[t] = array[j];
            t += 1;
            j += 1;
        }
        // 将temp数组的元素复制到array
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            array[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
        System.out.println(Arrays.toString(temp));
    }

}
