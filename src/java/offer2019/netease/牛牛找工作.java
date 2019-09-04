package offer2019.netease;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class 牛牛找工作 {
    public static void main(String[] args) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int key = sc.nextInt();
            int value = sc.nextInt();
            map.put(key, value);
        }
        Iterator<Integer> iterator = map.keySet().iterator();
        int best = Integer.MIN_VALUE;
        int cur;
        while (iterator.hasNext()) {
            cur = iterator.next();
            if (map.get(cur) > best) {
                best = map.get(cur);
            }
            map.put(cur, best);
        }
        for (int i = 0; i < n; i++) {
            int ans = sc.nextInt();
            Map.Entry<Integer, Integer> entry = map.floorEntry(ans);
            if (entry != null) {
                System.out.println(entry.getValue());
            } else {
                System.out.println(0);
            }
        }
    }
}
