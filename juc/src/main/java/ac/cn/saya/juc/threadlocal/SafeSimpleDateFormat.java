package ac.cn.saya.juc.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title: SafeSimpleDateFormat
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2/27/21 21:38
 * @Description: 使用ThreadLocal线程安全的SimpleDateFormat测试用例
 */

public class SafeSimpleDateFormat {

    private static ThreadLocal<SimpleDateFormat> formatterThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    private static SimpleDateFormat getSimpleDateFormat() {
        if (formatterThreadLocal.get() == null) {
            formatterThreadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        }
        return formatterThreadLocal.get();
    }

    private static Date parse(String date) {
        try {
            Date dateStr = getSimpleDateFormat().parse(date);
            return dateStr;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        } finally {
            // 为避免内存溢出，在使用完毕后，及时清理。
            formatterThreadLocal.remove();
        }
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
