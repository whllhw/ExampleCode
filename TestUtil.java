import java.util.*;

public class TestUtil {
    public static void testThread() throws InterruptedException {
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

    public static void testHashTable() {
        Map<Integer, Integer> map = new Hashtable<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            map.put(i, i);
        }
        long t2 = System.currentTimeMillis();

        long t3 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            map2.put(i, i);
        }
        long t4 = System.currentTimeMillis();

        System.out.println(t2 - t1);
        System.out.println(t4 - t3);
    }

    public static void main(String[] args) {
        testHashTable();
    }

}