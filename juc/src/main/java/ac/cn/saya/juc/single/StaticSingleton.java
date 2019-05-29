package ac.cn.saya.juc.single;

/**
 * @Title: StaticSingleton
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-29 20:59
 * @Description:
 * 单例模式
 */

public class StaticSingleton {

    private StaticSingleton() {
        System.out.println("StaticSingleton is create");
    }

    private static class SingletonHolder{
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance(){
        return SingletonHolder.instance;
    }

    public static void main(String[] args){
        for (int i = 0; i<= 100; i++){
            new Thread(() ->{
                System.out.println((StaticSingleton.getInstance()).hashCode());
            }).start();
        }
    }

}
