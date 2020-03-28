package ac.cn.saya.juc.interview;

import java.util.Arrays;

/**
 * @Title: SortUnit
 * @ProjectName spring-boot-webflux
 * @Description: TODO
 * @Author Administrator
 * @Date: 2020/3/10 0010 10:05
 * @Description:排序面试
 */

public class SortUnit {

    /**
     * @描述 冒泡排序
     * @参数 []
     * @返回值 void
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/10 0010
     * @修改人和其它信息
     */
    public void bubbleSort() {
        int[] arry = {1, 8, 678, 23478, 345, 890, 234354, 457547};
        int temp = 0;
        for (int i = 0; i < arry.length - 1; i++) {
            for (int j = 0; j < arry.length - i - 1; j++) {
                if (arry[j] > arry[j + 1]) {
                    temp = arry[j];
                    arry[j] = arry[j + 1];
                    arry[j + 1] = temp;
                }
            }
        }
        Arrays.toString(arry);
    }

    /**
     * @描述 快速排序
     * @参数  []
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020/3/10 0010
     * @修改人和其它信息
     */
    public void quickSort(){
        int[] arry = {3, 1, -1, 10, 20};
        quick_sort(arry,0,arry.length-1);
        System.out.println(Arrays.toString(arry));
    }

    /**
     * @描述 快速排序，
     * @参数  [arr, left, right]
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020/3/10 0010
     * @修改人和其它信息
     */
    protected void quick_sort(int[] array,int left,int right){
        if (left > right){
            return;
        }
        int i = partition(array, left, right);
        quick_sort(array,left,i-1);
        quick_sort(array,i+1,right);
    }

    /**
     * @描述  交换
     * @参数  [array, i, j]
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020/3/10 0010
     * @修改人和其它信息
     */
    protected void swap(int[] array,int i,int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * @描述 数组分区
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020/3/10 0010
     * @修改人和其它信息
     */
    protected int partition(int[] array,int left,int right){
        // 使用左边的第一个作为参考基准
        int temp = array[left];
        int i = left+1;
        int j = right;
        while (i <= j){
            while (array[i]<temp){
                i++;
            }
            while (array[j]>temp){
                j--;
            }
            if (i < j){
                swap(array,i++,j--);
            }else {
                i++;
            }
        }
        swap(array,j,left);
        return j;
    }

}
