package threadcoreknowledge.uncaughtexception;

/**
 * 使用自己写的 MyUncaughtExceptionHandler
 *
 */
public class UseOwnUncaughtExceptionHandler implements Runnable{

    public static void main(String[] args) throws InterruptedException {

        // 设置全局的 UncaughtExceptionHandler 用于捕获异常
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器1"));

        new Thread(new UseOwnUncaughtExceptionHandler(), "Mythread-1").start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "Mythread-2").start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "Mythread-3").start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "Mythread-4").start();
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
