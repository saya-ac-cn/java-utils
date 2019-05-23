package ac.cn.saya.string;

import org.junit.Test;

/**
 * @Title: StringEqualUtil1Test
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-23 21:46
 * @Description:
 */

public class StringEqualUtil1Test {

    @Test
    public void test1(){
        String a = "Programming";
        String b = new String("Programming");
        String c = "Program" + "ming";

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a.equals(b));
        System.out.println(a.equals(c));
        System.out.println(a.intern() == b.intern());
    }

}
