package threadcoreknowledge.threadsafe;

/**
 * 演示线程安全情况2： 出现死锁的情况
 */
public class MultiThreadError1 implements Runnable {

    int flag = 0;
    static Object o1 = new Object();  //作为锁资源
    static Object o2 = new Object();

    public static void main(String[] args) {

        MultiThreadError1 multiThreadError1 = new MultiThreadError1();
        MultiThreadError1 multiThreadError2 = new MultiThreadError1();

        multiThreadError1.flag = 0;
        multiThreadError2.flag = 1;

        new Thread(multiThreadError1).start();
        new Thread(multiThreadError2).start();

    }
    @Override
    public void run() {
        if (flag == 0){
            synchronized (o1){
                System.out.println("flag: " + flag);
                try {
                    Thread.sleep(1000);  //等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println("flag: " + flag);
                }
            }

        }
        if (flag == 1){
            synchronized (o2){
                System.out.println("flag: " + flag);
                try {
                    Thread.sleep(1000);  //等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println("flag: " + flag);
                }
            }

        }

    }
}
