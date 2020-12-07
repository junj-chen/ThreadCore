package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

/**
 *  演示sleep 不释放 lock (lock 需要手动释放)
 */
public class SleepDontReleaseLock implements Runnable {

    private static final Lock lock = new ReentrantLock();



    @Override
    public void run() {
        lock.lock();
        System.out.println("线程" + Thread.currentThread().getName() + "获取到了锁");
        try {
            Thread.sleep(5000);
            System.out.println("线程" + Thread.currentThread().getName() + "解锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SleepDontReleaseLock releaseLock = new SleepDontReleaseLock();

        new Thread(releaseLock).start();
        new Thread(releaseLock).start();

    }

}
