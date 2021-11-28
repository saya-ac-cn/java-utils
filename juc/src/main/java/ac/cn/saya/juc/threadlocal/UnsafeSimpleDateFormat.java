package ac.cn.saya.juc.threadlocal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title: UnsafeSimpleDateFormat
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2/27/21 21:13
 * @Description: 线程不安全的SimpleDateFormat测试用例
 */

public class UnsafeSimpleDateFormat {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Date parse(String date){
        Date parse = null;
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(500);
        for (int i = 0; i < 500; i++) {
            Thread thread = new Thread(() -> {
                Date parse = parse("2020-12-12 12:12:12");
                System.out.println("当前日期：" + parse);
            });
            executorService.execute(thread);
        }
        executorService.shutdown();
        System.out.println("线程执行完毕");
    }

}
