package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.Date;
import java.util.LinkedList;

public class ProducerConsumerModel {



    static class Producer implements Runnable{
        private EventStorage storage;

        public Producer(EventStorage storage){

            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                storage.put(new Date());
            }
        }
    }



    static class Consumer implements Runnable{
        private EventStorage storage;

        public Consumer(EventStorage storage){

            this.storage = storage;
        }



        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                storage.take();
            }
        }
    }


    static class EventStorage{

        private int maxsize;
        private LinkedList<Date> storage;

        public EventStorage(){
            storage = new LinkedList<>();
            maxsize = 10;
        }

        // 放入元素
        public synchronized void put(Date date){

            while (storage.size() == maxsize){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            storage.add(date);
            System.out.println("仓库里有了" + storage.size() + "个产品");
            notify();
        }

        public synchronized void take(){
            while (storage.size() == 0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("拿到了" + storage.poll() + ", 现在仓库还剩下" + storage.size());
        }

    }

    public static void main(String[] args) {
        EventStorage eventStorage = new EventStorage();
        Producer producer = new Producer(eventStorage);
        Consumer consumer = new Consumer(eventStorage);

        new Thread(producer).start();
        new Thread(consumer).start();


    }



}
