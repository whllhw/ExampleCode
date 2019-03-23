import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

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

    public static void testWeakHashMap() {
        // CopyOnWriteArrayList<Integer> copy = new CopyOnWriteArrayList<>();
        // WeakReference<Integer> wkf = new WeakReference<>();
        // ReferenceQueue<Integer> ;
        WeakHashMap<Integer, Byte[]> wk = new WeakHashMap<>();
        HashMap<Integer,Byte[]> map = new HashMap<>();
        
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            wk.put(i, new Byte[1 << 20]);
            wk.size();
        }
    }

    public static char testLinkedHashMap(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>(s.length(),1);
        for (int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            if (map.containsKey(x)) {
                map.put(x, map.get(x) + 1);
            } else {
                map.put(x, 1);
            }
        }
        Iterator iter = map.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<Character, Integer> e  = (Map.Entry<Character, Integer>) iter.next();
            if (e.getValue() == 1){
                return e.getKey();
            }
        }
        return 0;
    }
    public static int FirstNotRepeatingChar(String str){
        if (str == null || str.length() == 0){
            return -1;
        }
        HashMap<Character,Integer> map = new HashMap<>(str.length(),1);
        // Iterator iter = new LinkedHashMap<>().values()
        for(int i=0;i<str.length();i++){
            char x = str.charAt(i);
            if (map.containsKey(x)){
                map.put(x,map.get(x) + 1);
            }else{
                map.put(x,1);
            }
        }
        for(int i=0;i<str.length();i++){
            if (map.get(str.charAt(i)) == 1){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = "qwersadwqeq";
        System.out.println(testLinkedHashMap(s));
        System.out.println(FirstNotRepeatingChar(s)); 
    }

}