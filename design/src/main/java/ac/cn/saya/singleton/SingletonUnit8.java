package ac.cn.saya.singleton;

/**
 * @Title: SingletonUnit8
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-11 20:49
 * @Description:
 * 通过枚举实现单列
 */

public class SingletonUnit8 {

    public static void main(String []args){
        for (int i = 0 ; i < 10 ; i++){
            new Thread(() -> {
                System.out.println((SingletonEnumUnit8.INSTANCE).hashCode());
            }).start();
        }
    }
}

enum SingletonEnumUnit8{
    // 属性
    INSTANCE;
    public void getInstance(){
        System.out.println("ok~");
    }
}
