package algorithm;

import java.util.Arrays;

/**
 * @Title: BitMapRepRemove
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-12-17 21:20
 * @Description:
 * BitMap的常见应用
 */

public class BitMapRepRemove {

    /**
     * 利用BitMap实现海量数据去重
     */
//    public static void main(String[] args) {
//        int[] array = {255, 1024, 1024, 1, 65536, 1, 1024, 8888, 9999, 1111, 8888};
//        int length = 65536 + 1;
//        BitMap bitMap = new BitMap(length);
//        int index = 0;
//        for(int num : array) {
//            if( bitMap.getBit(num) != 1) {
//                //未出现的元素
//                array[index] = num;
//                index = index + 1;
//                //设置标志位
//                bitMap.setBit(num);
//            }
//        }
//        array = Arrays.copyOf(array, index);
//        System.out.println(Arrays.toString(array));
//        System.out.println(array.length);
//    }

    /**
     * 在一堆数字中，只有一位是重复的，请找出来
     * @param args
     */
    public static void main(String[] args) {
        int[] array = {8888, 1024, 1024, 1, 65536, 1, 1024, 8888, 9999, 1111, 8888};
        int length = 65536 + 1;
        BitMap bitMap = new BitMap(length);
        for(int num : array) {
            if( bitMap.getBit(num) != 1) {
                //未出现的元素,设置标志位01
                bitMap.setBit(num);
            }else {
                // 说明存在了置为11
                bitMap.setBit_(num);
            }
        }
        for(int num : array) {
            if( bitMap.getBit(num) == 1) {
                System.out.println(num);
                return;
            }
        }
    }

}
