package threadcoreknowledge.createthreads;

/**
 *  继承 Thread方法实现线程
 */
public class TheadStyle extends Thread{

    @Override
    public void run() {
        System.out.println("继承 Thread 方法实现线程");
    }

    public static void main(String[] args) {
        new TheadStyle().start();
    }
}
