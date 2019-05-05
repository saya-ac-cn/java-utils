package ac.cn.saya.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Title: StreamUtilTest1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-04 23:15
 * @Description:
 */

public class StreamUtilTest1 {

    @Test
    public void test1(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        // filter(Predicate p) --接收Lambda，从流中排出某些元素
        Stream<Integer> stream = list.stream();
        // 提取大于3的数据
        stream.filter(e -> e > 2).forEach(System.out::println);
        System.out.println("********************");

        // limit(n) --截断流，使其元素不超过给定数量
        list.stream().limit(4).forEach(System.out::println);
        System.out.println("********************");

        // skip(n) -- 跳过元素，返回一个扔掉前n的元素的流，若流中元素不足n个，则返回一个null
        list.stream().skip(4).forEach(System.out::println);
        System.out.println("********************");

        // distinct -- 筛选，通过流所生成元素的hashCode（）和equals()去除重复元素
        list.stream().distinct().forEach(System.out::println);

    }

    @Test
    public void test2(){
        List<String> list = Arrays.asList("as","adf","afguiuoi","io");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);

        list.stream().filter(str -> str.length() > 3).forEach(System.out::println);

    }

}
