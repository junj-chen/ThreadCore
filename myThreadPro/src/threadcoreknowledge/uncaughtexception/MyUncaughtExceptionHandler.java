package threadcoreknowledge.uncaughtexception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  实现自己的 MyUncaughtExceptionHandler
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private String name;

    public MyUncaughtExceptionHandler(String name){
        this.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        // 打印日志
        logger.log(Level.WARNING, "线程异常，终止啦" + t.getName(), e);
        System.out.println(name + " 捕获了异常 " + t.getName() + e);
    }
}
