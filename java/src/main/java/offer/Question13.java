package offer;

/**
 * @Title: Question13
 * @ProjectName spyder
 * @Description: TODO
 * @Author Administrator
 * @Date: 2020/1/13 0013 17:14
 * @Description:题目：输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */

public class Question13 {

//    @Test
//    public void main(){
//        int[] array = {1,2,3,4,5,6,7,8,9,10};
//        reOrderArray(array);
//        Arrays.stream(array).forEach(System.out::println);
//    }

    /**
     * [2,4,6,1,3,5,7]
     * 对应输出应该为:
     * [1,3,5,7,2,4,6]
     * 你的输出为:
     * [1,3,5,7,3,5,7]
     * @param array
     */
    public void reOrderArray(int [] array) {
        int length = array.length;
        if (length < 0){
            throw new RuntimeException();
        }
        int [] result = new int[length];
        int start = 0,end = length - 1;
        for (int i = 0; i < length; i++) {
            if ((array[i] & 1) == 1){
                result[start++] = array[i];
            }
            if ((array[length-1-i] & 1) == 0){
                result[end--] = array[length-1-i];
            }
        }
        // 切记数组是引用对象，下面的方法不会改变array
        //array = result;
        for (int i = 0; i < result.length; i++) {
            array[i] = result[i];
        }
    }

}
