package threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用 interrupt 进行通知线程中断
 */
public class WrongWayVolatileFixed {

    // 使用 生产者 与 消费者
    public static void main(String[] args) throws InterruptedException {

        WrongWayVolatileFixed wrongWayVolatileFixed = new WrongWayVolatileFixed();

        // 1. 共同的仓库
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);

        // 2. 创建生产者
        Producer producer = wrongWayVolatileFixed.new Producer(storage);

        // 3. 创建生产者线程
        Thread producerThread = new Thread(producer);
        producerThread.start();
        // 暂停主线程，让队列满
        Thread.sleep(1000);

        // 4. 创建消费者
        Consumer consumer = wrongWayVolatileFixed.new Consumer(storage);
        // 5. 生产者进行消费
        while (consumer.needMoreNums()){
            System.out.println(consumer.storage.take() + " 被消费了");
            Thread.sleep(100);
        }

        // 6. 消费完成后，需要停止生产者
        System.out.println("消费者不需要更多数据了。");
        // 消费者不需要更多数据的情况下，通过 设置的 boolean 通知生产者停止生产

        producerThread.interrupt();  // 开启中断通知

    }


    // 生产者
    class Producer implements Runnable{
        BlockingQueue storage;

        public Producer(BlockingQueue storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                // volatile 具有可见性，
                while (num <= 100000 && !Thread.currentThread().isInterrupted()){
                    if (num % 100 == 0){
                        System.out.println(num + "是 100 的倍数, 被放到了仓库中--");
                        storage.put(num);
                    }
                    num ++;
//                Thread.sleep(1); // 注释 与 不注释 程序都可以正常执行
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("生产者停止运行！");
            }
        }
    }

    // 消费者
    class Consumer{
        BlockingQueue storage;

        public Consumer(BlockingQueue storage) {
            this.storage = storage;
        }

        // 判断是否需要更多
        public boolean needMoreNums(){
            if (Math.random() > 0.95){
                return false;
            }else {
                return true;
            }
        }
    }

}
