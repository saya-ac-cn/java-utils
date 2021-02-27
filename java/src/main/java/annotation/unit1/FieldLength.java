package annotation.unit1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: FieldLength
 * @ProjectName java-utils
 * @Description: TODO
 * @Author saya
 * @Date: 2020/6/15 21:35
 * @Description:
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldLength {

    public String descript();

    public int length();

}
