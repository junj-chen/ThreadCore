package threadcoreknowledge.stopthreads;

/**
 *  如果 while中放置  try/catch ，则会导致中断失效， 这是因为 sleep 会使中断标志位失效，当在大循环中进行 try catch ,虽然可以捕获中断，但是会马上进入下一次循环
 */
public class CantInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = ()->{
            int num = 0;
            while (num < 10000 && !Thread.currentThread().isInterrupted() ){
                if (num % 100 == 0){
                    System.out.println(num + "是 100 的倍数！");
                }
                    num++;
                // 进行睡眠 ,循环内进行异常的捕获,
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("*******************"); // 不会打印
                }

                try {
                    Thread.sleep(10);// sleep 会消除中断的 标志位， 导致循环的中断标志位判断 !Thread.currentThread().isInterrupted() 失效
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(2000); // 主线程 休眠
        thread.interrupt(); // 中断线程
    }
}
