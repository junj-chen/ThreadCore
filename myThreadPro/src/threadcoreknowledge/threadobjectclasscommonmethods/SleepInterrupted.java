package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *  每隔 1 秒输出当前时间， 被中断，观察
 *  Thread.slepp()
 *  TimeUnit.SECONDS.sleep()
 *
 */
public class SleepInterrupted implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++){
            System.out.println(new Date());

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("被中断了");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SleepInterrupted());
        thread.start();

        Thread.sleep(5000);
        thread.interrupt();  // 中断
    }
}
