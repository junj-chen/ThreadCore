package threadcoreknowledge.stopthreads;

/**
 *  在 sleep的情况下中断线程， 在sleep情况下， 使用try -- catch 进行异常的捕获
 */
public class RightWayStopThreadWithSleep {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = ()->{
            int num = 0;
            try {
                while (num < 300 && !Thread.currentThread().isInterrupted()){
                    if (num % 100 == 0){
                        System.out.println(num + "是 100 的倍数！");
                    }
                    num++;  // 如果该句 注销， 去掉 !Thread.currentThread().isInterrupted() 的方法，该程序会如何执行？
                    //  该线程会一直执行，不会被打断，不能被异常捕获，因为interrupt只是通知 线程需要中断，但是不能强制，没有措施让该线程退出循环，所以一直执行，与下一种方法 进行对比
                }

                 // 线程休眠 1 s
                 Thread.sleep(1000);
            } catch (InterruptedException e){  // 捕获到中断线程的异常，在sleep情况下， 使用try -- catch 进行异常的捕获,
                System.out.println("进入异常！！");
                e.printStackTrace();
    // 在sleep 的情况下， 捕获到 中断后，会消除 中断位， 所以在循环中出现 try catch 捕获 sleep 异常可能会出现不能中断的情况
                System.out.println(Thread.currentThread().isInterrupted());
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5); // 主线程 休眠
        thread.interrupt(); // 中断线程，子线程 在 sleep 的状态下被中断
    }
}
