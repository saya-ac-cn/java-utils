package offer;

/**
 * @Title: Question1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-12-26 22:23
 * @Description:剑指Offer（一）：二维数组中的查找 题目：在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 思路：首先选取数组中右上角的数字。如果该数字等于要查找的数字，查找过程结束；如果该数字大于要查找的数组，
 * 剔除这个数字所在的列；如果该数字小于要查找的数字，剔除这个数字所在的行。也就是说如果要查找的数字不在
 * 数组的右上角，则每一次都在数组的查找范围中剔除一行或者一列，这样每一步都可以缩小查找的范围，直到找到要查
 * 找的数字，或者查找范围为空。
 *
 * 题目地址：https://cuijiahua.com/blog/2017/11/basis_1.html
 */

public class Question1 {

    public static void main(String[] args) {
        int findValue = 7;
        int[][] arr = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        int rows = 0,cols = 0;
        if (arr != null && (rows = arr.length) > 0 && (cols = arr[0].length)>0){
            int row = 0;
            int col = cols - 1;
            while (row < rows && col >= 0){
                if (arr[row][col] == findValue){
                    System.out.println("find:"+arr[row][col]);
                    return;
                }else if(findValue < arr[row][col]){
                    col--;
                }else {
                    row++;
                }
            }
        }
        System.out.println("don't find");
        return;
    }

}
