package threadcoreknowledge.stopthreads.volatiledemo;

/**
 * 演示 volatile的局限性， part1 看似可行
 */
public class WrongWayVolatile implements Runnable {

    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
//             volatile 具有可见性，
            while (num <= 100000 && !canceled){
                if (num % 100 == 0){
                    System.out.println(num + "是 100 的倍数");
                }
                num ++;
                Thread.sleep(1);  // 注释掉该语句，程序便不能 被 volatile 设置的标志位暂停
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile wrongWayVolatile = new WrongWayVolatile();
        Thread thread = new Thread(wrongWayVolatile);
        thread.start();

        Thread.sleep(5000);

        // 使用 volatile 修饰的变量 控制线程的结束
        wrongWayVolatile.canceled = true;
    }
}
