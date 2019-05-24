package ac.cn.saya.compar;

import ac.cn.saya.entity.CarEntity;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Title: ComparableUtilsTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-21 21:59
 * @Description:
 * Comparable示例
 */

public class ComparableUtilsTest {

    @Test
    public void test(){
        CarEntity[] carList=new CarEntity[]{new CarEntity("特斯拉", 31),new CarEntity("沃尔沃", 30)};
        System.out.println("排序前");
        for (CarEntity item : carList)
        {
            System.out.println(item.getUid()+":"+item.getWeight());
        }
        Arrays.sort(carList);
        System.out.println("\n排序后");
        for (CarEntity item : carList)
        {
            System.out.println(item.getUid()+":"+item.getWeight());
        }
    }

}
