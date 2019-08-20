import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class poj3320_尺取法 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int a : A) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        int size = map.size();
        HashMap<Integer, Integer> curMap = new HashMap<Integer, Integer>();
        int pos = 0;
        while (pos < n) {
            curMap.put(A[pos], curMap.getOrDefault(A[pos], 0) + 1);
            pos++;
            if (curMap.size() == size) {
                break;
            }
        }
        int res = pos;
        int subject = size;
        int low = 0;
        for (; ; ) {
            // 一直移动，直到能满足条件——获取所有的点
            while (pos < n && subject < size) {
                int added = A[pos];
                pos++;
                int curSum = curMap.getOrDefault(added, 0);
                curMap.put(added, 1 + curSum);
                if (curSum == 0) {
                    subject++;
                }
            }
            // 终止条件，没办法符合获取所有点的条件
            if (subject < size) {
                break;
            }
            // 更新返回值
            res = Math.min(res, pos - low);
            // 删除末尾的值
            // 当为0时，科目减一
            int curSum = curMap.get(A[low]);
            curMap.put(A[low], curSum - 1);
            if (curSum == 1) {
                subject--;
            }
            low++;
        }
        out.println(res);
        sc.close();
        out.close();
    }

    static class HashMap<T1, T2> extends java.util.HashMap<T1, T2> {
        public HashMap() {
            super();
        }

        public T2 getOrDefault(Object key, T2 val) {
            if (super.containsKey(key)) {
                return super.get(key);
            }
            return val;
        }

    }
}
