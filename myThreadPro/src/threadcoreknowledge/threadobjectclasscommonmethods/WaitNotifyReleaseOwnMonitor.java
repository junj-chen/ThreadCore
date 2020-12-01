package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 *  证明 wait 只释放当前的 那把锁
 */
public class WaitNotifyReleaseOwnMonitor {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println("ThreadA 获取到了 A锁");
                    synchronized (resourceB) {
                        System.out.println("ThreadA 获取到了 B锁");
                        try {
                            System.out.println("ThreadA 释放了 A锁");
                            resourceA.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println("ThreadB 获取到了 A锁");
                    System.out.println("ThreadB 尝试获取 B锁");
                    synchronized (resourceB) {
                        System.out.println("ThreadB 获取到了 B锁");
                    }
                }
            }
        });
        threadA.start();
        Thread.sleep(1000);
        threadB.start();
    }
}
