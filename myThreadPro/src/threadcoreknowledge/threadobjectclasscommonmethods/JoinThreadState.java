package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * join 加入后，主线程的状态为 waiting
 */
public class JoinThreadState {

    public static void main(String[] args) throws InterruptedException {
        Thread threadMain = Thread.currentThread();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println(threadMain.getState());// 主线程中加入 子线程后，状态 为waiting
                    System.out.println("Thread0 执行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        thread.start();
        System.out.println("等待子线程执行结束");
        thread.join();
        System.out.println("子线程执行完毕");

    }

}
