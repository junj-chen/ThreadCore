package threadcoreknowledge.createthreads;

/***
 * 同时使用 Runnable 和 run 方法
 */
public class BothRunnableThread {
    /**
     * 使用 匿名内部类 实现
     * @param args
     */
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable方法实现");
            }
        }){
            @Override
            public void run() {
                // run方法被重写，Runnable 对象不会被执行
                System.out.println("Thread方法实现");
            }
        }.start();
    }
}
