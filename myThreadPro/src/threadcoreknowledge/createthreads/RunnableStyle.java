package threadcoreknowledge.createthreads;

/**
 * 实现 runnable 接口实现线程
 */

public class RunnableStyle implements Runnable {

    @Override
    public void run() {
        System.out.println("实现Runnable接口实现多线程");
    }

    public static void main(String[] args) {
        new Thread(new RunnableStyle()).start();
    }


}
