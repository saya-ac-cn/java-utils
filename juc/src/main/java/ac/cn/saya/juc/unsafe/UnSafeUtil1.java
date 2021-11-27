package ac.cn.saya.juc.unsafe;

/**
 * @Title: UnSafeUtil1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-10-20 23:45
 * @Description:
 * Unsafe操作1
 * https://blog.csdn.net/luoyoub/article/details/79918104
 */

public class UnSafeUtil1 {

    public static void main(String[] args) {
//        Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
//        try {
//            // allocateInstance(Class<?> var1)不调用构造方法生成对象
//            User user = (User)unsafe.allocateInstance(User.class);
//            user.setId(1);
//            user.setName("saya");
//            System.out.println(user.toString());
//            System.out.println("------>");
//            // objectFieldOffset(Field var1)返回成员属性在内存中的地址相对于对象内存地址的偏移量
//            Field name = user.getClass().getDeclaredField("property");
//            unsafe.putObject(user, unsafe.objectFieldOffset(name), "pandora");
//            System.out.println(user.toString());
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }catch (NoSuchFieldException e){
//            e.printStackTrace();
//        }
    }

}
