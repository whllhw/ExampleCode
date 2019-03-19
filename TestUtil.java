import java.util.*;

public class TestUtil {
    public static void main(String[] args) throws InterruptedException {
        HashMap<Integer, Integer> map = new HashMap<>();
        // 插入线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (map) {
                    for (int i = 0; i < 10000; i++) {
                        map.put(i, i);
                    }
                }
            }
        });
        Thread read = new Thread(new Runnable() {

            @Override
            public void run() {
                // synchronized (map) {
                for (int i = 10000; i < 100000; i++) {
                    // if (!map.containsKey(i) || map.get(i) != i) {
                    // System.out.println(i);
                    // }
                    map.put(i, i);
                }
                // }
            }
        });
        thread.start();
        // Thread.sleep(1);
        read.start();
        thread.join();
        read.join();
        for (int i = 0; i < 100000; i++) {
            if (!map.containsKey(i) || map.get(i) != i) {
                System.out.println(i);
            }
        }
    }

}