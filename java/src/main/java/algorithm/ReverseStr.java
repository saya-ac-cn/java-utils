package algorithm;

import java.util.Arrays;

/**
 * @Title: ReverseStr
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-12-16 22:21
 * @Description:
 * 题目：写一个字符串反转函数
 */

public class ReverseStr {

    public static void main(String[] args) {
        char[] str = {'1','2','3','4','5'};
        strrev(str);
    }

    /**
     * @描述 字符串反转（循环交换两边对称位置的数，一直到中间）
     * @参数  字符数组
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/12/16 0016
     * @修改人和其它信息
     */
    public static void strrev(char[] str){
        char temp;
        int border = str.length/2;
        for (int i = 0; i < border;i++){
            temp = str[i];
            str[i] = str[str.length -i- 1];
            str[str.length -i- 1] = temp;
        }
        System.out.println("倒排结果>>>");
        Arrays.asList(str).stream().forEach(s -> System.out.print(s));
    }

}
