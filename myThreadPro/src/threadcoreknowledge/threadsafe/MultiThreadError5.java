package threadcoreknowledge.threadsafe;

import java.util.HashMap;
import java.util.Map;

/**
 * 构造函数中进行 新开线程 进行操作
 */
public class MultiThreadError5 {
    private Map<String, String> statue;

    public MultiThreadError5(){

        // 构造函数进行 开启线程初始化
        new Thread(new Runnable() {
            @Override
            public void run() {
                statue = new HashMap<>();
                statue.put("1", "周一");
                statue.put("2", "周二");
                statue.put("3", "周三");
                statue.put("4", "周四");
            }
        }).start();
    }
    // 出现溢出的情况
    public Map<String, String> getStatus(){
        return statue;
    }

    public static void main(String[] args) {
        MultiThreadError5 multiThreadError5 = new MultiThreadError5();
        Map<String, String> status = multiThreadError5.getStatus();
        System.out.println(status.get("1"));  // 出现了 空指针异常的错误  Exception in thread "main" java.lang.NullPointerException
    }
}
