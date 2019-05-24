package ac.cn.saya.compar;

import ac.cn.saya.entity.CatEntity;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Title: ComparatorUtilsTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-21 22:10
 * @Description:
 * Comparator示例
 */

public class ComparatorUtilsTest {

    @Test
    public void test(){
        CatEntity[] catList = new CatEntity[]{new CatEntity("狗",3),new CatEntity("猫",9),new CatEntity("鸟",1)};
        System.out.println("排序前");
        for (CatEntity item:catList){
            System.out.println(item.getName()+":"+item.getAge());
        }
        Arrays.sort(catList,new CatEntity());
        System.out.println("\n排序后");
        for (CatEntity item:catList){
            System.out.println(item.getName()+":"+item.getAge());
        }
    }
}
