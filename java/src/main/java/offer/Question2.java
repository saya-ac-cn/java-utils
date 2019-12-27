package offer;


/**
 * @Title: Question2
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019/12/27 0027 16:01
 * @Description: 题目：请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * 思路：由于在java中的数组不是动态数组（c/c++是动态数组），所以本例才用数组切分，以空格为界先切，最后归并
 * 地址：https://cuijiahua.com/blog/2017/11/basis_2.html
 */

public class Question2 {

    public static void main(String[] args) {
        String parmer = "We Are Happy";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < parmer.length(); i++) {
            char c = parmer.charAt(i);
            if (c == 32){
                builder.append("%20");
            }else {
                builder.append(c);
            }
        }
        System.out.println(builder.toString());
    }

}
