package ac.cn.saya.reflect.unreflect;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * @Title: UnReflectTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-05-22 21:06
 * @Description: 防止对象通过反射、序列化创建
 */

public class UnReflectTest {

    public static void main(String[] args) throws Exception{
        Singleton sc1 = Singleton.getInstance();
        Singleton sc2 = Singleton.getInstance();
        // sc1，sc2是同一个对象
        System.out.println(sc1);
        System.out.println(sc2);

        // 通过反射的方式直接调用私有构造器（通过在构造器里抛出异常可以解决此漏洞）
        Class<Singleton> clazz = (Class<Singleton>) Class.forName("ac.cn.saya.reflect.unreflect.Singleton");
        Constructor<Singleton> c = clazz.getDeclaredConstructor(null);
        // 跳过权限检查
        c.setAccessible(true);
        Singleton sc3 = c.newInstance();
        Singleton sc4 = c.newInstance();
        // sc3，sc4不是同一个对象
        System.out.println("通过反射的方式获取的对象sc3：" + sc3);
        System.out.println("通过反射的方式获取的对象sc4：" + sc4);

        // 通过反序列化的方式构造多个对象（类需要实现Serializable接口）

        // 1. 把对象sc1写入硬盘文件
        FileOutputStream fos = new FileOutputStream("object.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(sc1);
        oos.close();
        fos.close();

        // 2. 把硬盘文件上的对象读出来
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.out"));
        // 如果对象定义了readResolve()方法，readObject()会调用readResolve()方法。从而解决反序列化的漏洞
        Singleton sc5 = (Singleton) ois.readObject();
        // 反序列化出来的对象，和原对象，不是同一个对象。如果对象定义了readResolve()方法，可以解决此问题。
        System.out.println("对象定义了readResolve()方法，通过反序列化得到的对象：" + sc5);
        ois.close();
    }

}
