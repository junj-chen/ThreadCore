package threadcoreknowledge.sixstates;

/**
 * 展示三种状态： new  RUNNABLE Terminated。即使正在运行，也是 Runable 状态，不是Running
 */
public class NewRunnableTerminated implements Runnable{

    public static void main(String[] args) {

        Thread thread = new Thread(new NewRunnableTerminated());
        // new 一个线程， 线程的状态是 new
        System.out.println(thread.getState());

        // 启动线程，线程的状态是 Runnable ，可运行的，可能没有抢到 CPU 资源
        thread.start();
        System.out.println(thread.getState());

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 目前线程是正在运行，但是状态是 Runnable， 而不是 Running状态
        System.out.println(thread.getState());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 线程结束运行后的状态是 terminated
        System.out.println(thread.getState());


    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
