package threadcoreknowledge.stopthreads;

/**
 *  最佳实践2： 在 catch 子语句中 调用 Thread。currentThread().interrupt() 来恢复设置中断状态
 *  以便于在后续的执行中，依然能够检查到刚才发生了中断，回到 RightWayStopThreadInProd 补上中断，让它跳出
 *
 */
public class RightWayStopThreadInProd2 implements Runnable{
    @Override
    public void run() {
        while (true){

            // 判断是否有中断异常的信号
            if(Thread.currentThread().isInterrupted()){
                System.out.println("接收到中断信号");
                break;
            }
            System.out.println("go");
            reInterrupt();  // 调用子方法, 子方法中 没有 抛出异常，所以不能 try / catch

        }
    }

    private void reInterrupt(){
        // 抛出异常
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // 捕获到 中断异常后 再进行 抛出中断， 以便于上层 检查是否出现了 中断信号
            Thread.currentThread().interrupt();  // 如果注释该语句，则上层无法接收到 中断信号
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();

        Thread.sleep(1000);

        thread.interrupt(); // 中断
    }
}
