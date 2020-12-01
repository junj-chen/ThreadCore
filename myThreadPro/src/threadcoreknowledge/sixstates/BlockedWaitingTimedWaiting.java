package threadcoreknowledge.sixstates;

/**
 *  展示 blocked TimeWaiting Waiting状态
 */
public class BlockedWaitingTimedWaiting implements Runnable{

    public static void main(String[] args) {

        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();

        Thread thread1 = new Thread(runnable);
        thread1.start();  // 启动线程

        Thread thread2 = new Thread(runnable);
        thread2.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 该线程获取 锁，但是在执行 Thread.sleep(5000); 处于 TIMED_WAITING 状态
        System.out.println(thread1.getState());
        // 没有获取 锁，处于Blocked
        System.out.println(thread2.getState());

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(thread1.getState());
        System.out.println(thread2.getState());

    }

    // 被synchronize 修饰的方法
    private synchronized  void syn(){
        try {
            Thread.sleep(5000);
            wait();  // 导致线程 进入 waiting状态
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        syn(); // 线程启动执行的方法
    }
}
