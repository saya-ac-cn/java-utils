package annotation.unit1;

import java.lang.reflect.Field;

/**
 * @Title: FieldLengthTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author saya
 * @Date: 2020/6/15 21:39
 * @Description:FieldLength注解测试
 * 参考1：https://www.jianshu.com/p/a7bedc771204
 * 参考2：https://www.cnblogs.com/jajian/p/9695055.html
 */

public class FieldLengthTest {

    /**
     * 使用自定义的注解
     */
    @FieldLength(descript = "用户名",length = 12)
    private String userName;

    public static void main(String[] args) {
        Class<FieldLengthTest> aClass = FieldLengthTest.class;
        // 得到所有字段
        for (Field f:aClass.getDeclaredFields()){
            // 判断这个字段是否含有FieldLength注解
            if (f.isAnnotationPresent(FieldLength.class)){
                FieldLength annotation = f.getAnnotation(FieldLength.class);
                System.out.println("字段:["+f.getName()+"],描述:["+annotation.descript()+"],长度:["+annotation.length()+"]");
            }
        }
    }

}
