package threadcoreknowledge.stopthreads;

/**
 *  如果在执行过程中，每次循环都会调用 sleep 或者 wait方法，则不要每次迭代都检查是否需要中断
 */
public class RightWayStopThreadWithSleepEveryLoop {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = ()->{
            int num = 0;
            try {
                while (num < 10000 ){
                    if (num % 100 == 0){
                        System.out.println(num + "是 100 的倍数！");
                    }
//                    num++;
                    // 该睡眠（阻塞）是在 循环中， 与上一个方法中的注释对比， 该方法中去掉了 !Thread.currentThread().isInterrupted() 条件，但是一样会捕获到异常
                    Thread.sleep(10); // 说明在 睡眠的时候发生中断 通知可以进入到异常
                }

            } catch (InterruptedException e){  // 捕获到中断线程的异常，在sleep情况下， 使用try -- catch 进行异常的捕获
                System.out.println("进入异常！！");
                e.printStackTrace();
                System.out.println(Thread.currentThread().isInterrupted()); // false
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(2000); // 主线程 休眠
        thread.interrupt(); // 中断线程，子线程 在 sleep 的状态下被中断
    }
}
