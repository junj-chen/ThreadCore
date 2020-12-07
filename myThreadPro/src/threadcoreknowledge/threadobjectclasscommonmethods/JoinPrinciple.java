package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 *  实现 join 方法的等价方法
 */
public class JoinPrinciple {
    public static void main(String[] args) throws InterruptedException {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("Thread1 执行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        thread.start();
        System.out.println("等待子线程执行结束");
//        thread.join();
        // 等价于 上述的 join 方法
        synchronized (thread){
            thread.wait();// 陷入等待，但是 thread执行完毕后，会进行执行 通知方法
        }
        System.out.println("所有线程执行完毕");

    }
}
