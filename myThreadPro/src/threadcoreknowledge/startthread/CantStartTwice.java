package threadcoreknowledge.startthread;

/**
 *   演示调用两次 start 方法, 抛出异常
 */
public class CantStartTwice {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
