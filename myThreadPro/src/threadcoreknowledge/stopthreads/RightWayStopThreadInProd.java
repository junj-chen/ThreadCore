package threadcoreknowledge.stopthreads;

/**
 *  最佳实践： 在 catch 了 InterruptedException 之后的优先选择是 在方法签名中 抛出异常
 *  那么在run 方法中 会 强制 try / catch
 */
public class RightWayStopThreadInProd  implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println("go");
            try {
                throwInMethod();  // 调用子方法。 子方法抛出异常后，进行异常捕获，run方法只能 使用try / catch捕获
            } catch (InterruptedException e) {
                e.printStackTrace();
                // 做出 异常处理的方法， 比如保存日志，停止程序
                System.out.println("保存日志");

            }
        }
    }

    private void throwInMethod() throws InterruptedException {
        /**
         *  考虑 出现的 异常是否需要 暴露给上一层的调用者
         */
//        try {
//            System.out.println(Thread.currentThread().getName());
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // 抛出异常
         Thread.sleep(2000);

    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();

        Thread.sleep(1000);

        thread.interrupt(); // 中断
    }
}
