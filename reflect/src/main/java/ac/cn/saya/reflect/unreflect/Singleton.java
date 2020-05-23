package ac.cn.saya.reflect.unreflect;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * @Title: Singleton
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-05-22 20:55
 * @Description: 防止单例对象被反射
 */

public class Singleton implements Serializable {

    private static final long serialVersionUID = -4234064801722216103L;

    private static class SingletonInstance {
        private static final Singleton instance = new Singleton();
    }

    /**
     * 对外通过此方法获取对象
     * @return
     */
    public static Singleton getInstance(){
        return SingletonInstance.instance;
    }

    /**
     * 防止通过反射获取多个对象漏洞
     */
    private Singleton(){
        if (null != SingletonInstance.instance){
            throw new RuntimeException();
        }
    }

    /**
     * 防止通过序列化获取多个对象
     * @return
     * @throws ObjectStreamException
     * 当从流中读取一个对象并准备将其返回给调用者时，调用了readResolve方法.
     * ObjectInputStream检查对象的类是否定义了readResolve方法。
     * 如果定义了方法，则调用readResolve方法以允许流中的对象指定要返回的对象
     */
    private Object readResolve() throws ObjectStreamException{
        return SingletonInstance.instance;
    }


}
