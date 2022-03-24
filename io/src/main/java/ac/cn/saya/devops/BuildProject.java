package ac.cn.saya.devops;

import java.io.*;
import java.util.concurrent.TimeUnit;
// https://www.cnblogs.com/hdwang/p/9047471.html
// https://blog.csdn.net/dgywj/article/details/115868554?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~aggregatepage~first_rank_ecpm_v1~rank_v31_ecpm-1-115868554.pc_agg_new_rank&utm_term=java%E6%89%A7%E8%A1%8Cnpm%E5%91%BD%E4%BB%A4&spm=1000.2123.3001.4430

/**
 * 代码打包部署
 *
 * @Title: BuildProject
 * @ProjectName java-utils
 * @Description: TODO
 * @Author saya
 * @Date: 2022/3/24 13:36
 * @Description:
 */

public class BuildProject {

    public static void run1() {
        String[] params = new String[]{"/bin/sh","-c","/Users/saya/project/java/java-utils/io/src/main/resources/devops.sh /Users/saya/project/web/wisdom-iot wisdom-iot"};
        BufferedReader shellErrorResultReader = null;
        BufferedReader shellInfoResultReader = null;
        Process shellProcess = null;
        try {
            shellProcess = Runtime.getRuntime().exec(params);
            shellErrorResultReader = new BufferedReader(new InputStreamReader(shellProcess.getErrorStream()));
            shellInfoResultReader = new BufferedReader(new InputStreamReader(shellProcess.getInputStream()));
            String infoLine;
            while ((infoLine = shellInfoResultReader.readLine()) != null) {
                System.out.println("脚本文件执行信息:" + infoLine);
            }
            String errorLine;
            while ((errorLine = shellErrorResultReader.readLine()) != null) {
                System.out.println("脚本文件执行信息:" + errorLine);
            }
            // 等待程序执行结束并输出状态
            int exitCode = shellProcess.waitFor();
            if (0 == exitCode) {
                System.out.println("脚本文件执行成功:" + exitCode);
            } else {
                System.out.println("脚本文件执行失败:" + exitCode);
            }
        } catch (Exception e) {
            System.out.println("shell脚本执行错误" + e);
        } finally {
            if (null != shellInfoResultReader) {
                try {
                    shellInfoResultReader.close();
                } catch (IOException e) {
                    System.out.println("流文件关闭异常：" + e);
                }
            }
            if (null != shellErrorResultReader) {
                try {
                    shellErrorResultReader.close();
                } catch (IOException e) {
                    System.out.println("流文件关闭异常：" + e);
                }
            }
            if (null != shellProcess) {
                shellProcess.destroy();
            }
        }
    }

    public static void run2() {
        String[] params = new String[]{"/Users/saya/project/java/java-utils/io/src/main/resources/devops.sh", "/Users/saya/project/web/wisdom-iot", "wisdom-iot"};
        InputStream in = null;
        BufferedReader read = null;
        try {
            Process pro = Runtime.getRuntime().exec(params);
            pro.waitFor();
            in = pro.getInputStream();
            read = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = read.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != read) {
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        run1();
    }
}
