package threadcoreknowledge.stopthreads;

/**
 * Run 方法内没有sleep 或者 wait方法时，停止线程
 */
public class RightWayStopThreadWithoutSleep implements Runnable{
    @Override
    public void run() {
        int num = 0;
        // !Thread.currentThread().isInterrupted() 用于检测是否有中断信号通知


        while ( !Thread.currentThread().isInterrupted() && num < Integer.MAX_VALUE / 2){
            if (num % 10000 == 0){
                System.out.println(num + "是 10000 的倍数");
            }
            num++;
            if (Thread.currentThread().isInterrupted()){
                System.out.println("*******************"); //  会打印 , 收到了中断信号
            }
        }

        System.out.println(Thread.currentThread().isInterrupted());

        System.out.println("任务结束！");
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();  // 启动子线程
        Thread.sleep(1000);  // 睡眠1 m
        thread.interrupt(); // 中断线程，interrupe 只是通知，并不会进行强制中断，中断线程需要 子线程的响应配合

    }
}
