package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 *  1.  3 个线程， 线程1 和线程2 首先被阻塞，线程3唤醒他们。 notify notifyAll
 *  2.  展示 start 先执行不代表线程先启动 （由操作系统线程进行调度）
 */
public class WaitNotifyall implements Runnable{

    private static  final Object resourceA = new Object();
    @Override
    public void run() {
        synchronized (resourceA){
            System.out.println(Thread.currentThread().getName() + " 获取到了资源");


            try {
                System.out.println(Thread.currentThread().getName() + " 即将等待");
                resourceA.wait();

                System.out.println(Thread.currentThread().getName() + " 即将结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new WaitNotifyall();

        Thread threadA = new Thread(r);
        Thread threadB = new Thread(r);

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA){
//                    resourceA.notifyAll();  //进行资源的唤醒
                    resourceA.notify();  //进行资源的唤醒
                    System.out.println("线程C 进行了唤醒");
                }
            }
        });

        threadA.start();
        threadB.start();
        Thread.sleep(200);// 注释后可以看到 先执行 start 方法不一定会优先执行
        threadC.start();

    }
}
