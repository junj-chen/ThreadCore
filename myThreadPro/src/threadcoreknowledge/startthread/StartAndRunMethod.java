package threadcoreknowledge.startthread;

/**
 *  演示 直接调用 run 方法 与 调用 start 方法
 */
public class StartAndRunMethod {
    public static void main(String[] args) {

        Runnable runnable = ()->{
            System.out.println(Thread.currentThread().getName());
        };

        // 直接调用 run方法
        runnable.run();

        new Thread(runnable).start();

    }

}
