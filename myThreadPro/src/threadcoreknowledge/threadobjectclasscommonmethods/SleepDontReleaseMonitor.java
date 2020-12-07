package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 展示 线程 sleep的时候不释放 synchronize 的 monitor， 等sleep 时间到了之后正常结束才释放锁
 */
public class SleepDontReleaseMonitor implements Runnable{
    @Override
    public void run() {
        syn();
    }
    private synchronized void syn() {
        System.out.println("线程" +Thread.currentThread().getName() + "获取到了monitor" );

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程" +Thread.currentThread().getName() + "释放了monitor" );
    }

    public static void main(String[] args) {
        SleepDontReleaseMonitor monitor = new SleepDontReleaseMonitor();
        new Thread(monitor).start();
        new Thread(monitor).start();
    }
}
