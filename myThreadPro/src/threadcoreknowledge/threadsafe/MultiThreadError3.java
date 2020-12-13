package threadcoreknowledge.threadsafe;

import java.util.HashMap;
import java.util.Map;

/**
 *  1. 解决 发布 与 溢出导致的问题， 返回原始引用的 副本
 */
public class MultiThreadError3 {

    private Map<String, String> statue;

    public MultiThreadError3(){
        statue = new HashMap<>();
        statue.put("1", "周一");
        statue.put("2", "周二");
        statue.put("3", "周三");
        statue.put("4", "周四");
    }
    // 出现溢出的情况
//    public Map<String, String> getStatus(){
//        return statue;
//    }

        public Map<String, String> getStatusImproved(){
        return new HashMap<>(statue);
    }


    public static void main(String[] args) {
        MultiThreadError3 multiThreadError3 = new MultiThreadError3();

        System.out.println(multiThreadError3.getStatusImproved().get("1"));
        multiThreadError3.getStatusImproved().remove("1");
        System.out.println(multiThreadError3.getStatusImproved().get("1"));
    }

}
