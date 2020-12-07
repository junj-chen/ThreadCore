package threadcoreknowledge.uncaughtexception;

/**
 *
 */
public class CantCatchDirectly implements Runnable {

    public static void main(String[] args) throws InterruptedException {

        new Thread(new CantCatchDirectly(), "Mythread-1").start();
        Thread.sleep(300);
        new Thread(new CantCatchDirectly(), "Mythread-2").start();
        Thread.sleep(300);
        new Thread(new CantCatchDirectly(), "Mythread-3").start();
        Thread.sleep(300);
        new Thread(new CantCatchDirectly(), "Mythread-4").start();

    }

    @Override
    public void run() {
        try {
            throw new RuntimeException();
        }catch (RuntimeException e){
            System.out.println("Catch Exception");
        }
    }
}
