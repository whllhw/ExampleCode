package offer2020.baidu;

import java.io.BufferedInputStream;
import java.util.*;

public class T3 {
    private static int maxLen;

    // 100%
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        int[] weight = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            weight[i] = sc.nextInt();
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (weight[a] < weight[b]) {
                if (!map.containsKey(a))
                    map.put(a, new LinkedList<>());
                map.get(a).add(b);
            } else if (weight[b] < weight[a]) {
                if (!map.containsKey(b))
                    map.put(b, new LinkedList<>());
                map.get(b).add(a);
            }
        }
        for (int i = 1; i <= n; i++) {
            helper(i, map, 1);
        }
        System.out.println(maxLen);
        sc.close();
    }

    private static void helper(int cur, Map<Integer, List<Integer>> map, int len) {
        maxLen = Math.max(len, maxLen);
        if (!map.containsKey(cur)) {
            return;
        }
        for (Iterator<Integer> it = map.get(cur).iterator(); it.hasNext(); ) {
            int i = it.next();
            helper(i, map, len + 1);
        }
    }
}
