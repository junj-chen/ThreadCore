package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 展示 wait 和 notify的基本用法， 1. 研究代码的执行顺序 2. 证明wait释放锁资源
 */
public class Wait {

    // 1. 定义object方法
    public static Object object = new Object();

    static class Thread1 extends Thread{
        @Override
        public void run() {
            // 使用synchronize进行代码块的同步
            synchronized (object){
                System.out.println(Thread.currentThread().getName() + "开始执行了");

                try {
                    object.wait();  //wait 方法会释放锁资源， 可以被中断，所以需要捕获中断异常
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("线程" + Thread.currentThread().getName() + "获得了锁");
            }
        }
    }

    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                // 调用同一个对象进行 唤醒 wait的线程
                object.notify();
                System.out.println("线程" + Thread.currentThread().getName() + "调用了 notify()");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        Thread.sleep(10);
        thread2.start();
    }
}
