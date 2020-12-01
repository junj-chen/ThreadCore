package threadcoreknowledge.stopthreads;


/**
 * 错误的停止方法： 用stop（）来停止线程，
 *      会导致线程运行到一半突然停止，没有办法完成一个基本单位的操作，
 *      造成脏数据
 *      （模拟军队领取军资）
 */

public class StopThread implements Runnable {

    @Override
    public void run() {
        // 模拟指挥军队，一共 5 个连队，每个连队 10 人，以连队为单位发送军资，叫到的士兵去发送武器
        for (int i = 0; i < 5; i++) {
            System.out.println("连队 " + i + "开始领取武器" );
            for (int j = 0; j < 10; j++) {
                System.out.println(j);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("连队 " + i + "已经领取完毕");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopThread());
        thread.start();
        Thread.sleep(1000);

        // 停止线程,出现 单位 （连队） 被终止了，导致没有完成 基本单位的操作
        thread.stop();
    }
}
