package ac.cn.saya.juc.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.SimpleFormatter;

/**
 * @Title: ThreadLocalUtil1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-10-17 22:41
 * @Description:
 */

public class ThreadLocalUtil1 {

    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();

    public static class ParseDate implements Runnable{
        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if (threadLocal.get() == null) {
                    threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }
                Date t = (threadLocal.get()).parse("2019-10-17 22:44:32" + i % 60);
                System.out.println(i + ":" + t);
            } catch (ParseException e) {
                e.printStackTrace();
            }finally {
                threadLocal.remove();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new ParseDate(i));
        }
        executorService.shutdown();
    }

}
