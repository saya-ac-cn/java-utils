package ac.cn.saya.collect;

import org.junit.Test;

import java.util.HashMap;

/**
 * @Title: MapUtils
 * @ProjectName java-utils
 * @Description: TODO
 * @Author saya
 * @Date: 2020/6/20 22:35
 * @Description:
 */

public class MapUtils {

    @Test
    public void mapTypeErrorTest(){
        HashMap<Integer, String> map = new HashMap<>(8);
        map.put(1,"hello");
        map.put(2,"world");
        // 下面这个取不到，因为key的类型不一致
        System.out.println(map.get((short)1));
    }

}
