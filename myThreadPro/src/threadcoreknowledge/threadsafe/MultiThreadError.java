package threadcoreknowledge.threadsafe;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  线程安全情况1： 运行结果出错
 *
 *  演示 计数不准确，找出出错的位置
 */
public class MultiThreadError implements Runnable{

    int index = 0;
    static MultiThreadError instance = new MultiThreadError();
    static AtomicInteger realIndex = new AtomicInteger();
    static AtomicInteger wrongIndex = new AtomicInteger();
    //
    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);


    // 用于标志是否已经被标志
    final boolean[] marked = new boolean[10000000];

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("表面上的运行结果：" + instance.index); // 打印目前的计数结果
        System.out.println("真正的运行结果：" + realIndex); // 打印目前的计数结果
        System.out.println("错误的运行结果：" + wrongIndex); // 打印目前的计数结果

    }

    @Override
    public void run() {
        marked[0] = true;
        for (int i = 0; i < 10000; i++) {
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;
            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet(); // 增加1

            synchronized (instance){
                if (marked[index] && marked[index - 1]){
                    System.out.println("发生错误 " + index);
                    wrongIndex.incrementAndGet();
                }
                marked[index] = true;
            }

        }
    }
}
