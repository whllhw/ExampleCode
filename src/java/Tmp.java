import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Tmp {
    // 帆软
    // AC 66.7%
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] src = new int[n][m];
        int index = sc.nextInt();
        int transfromIndex = sc.nextInt();
        int refIndex = sc.nextInt();
        HashSet<Integer> indexSet = new HashSet<>();
        HashMap<POS, Integer> map = new HashMap<>();
        HashSet<Integer> transformSet = new HashSet<>();
        int tmp;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            boolean first = false;
            int line = 0;
            for (int j = 0; j < m; j++) {
                tmp = sc.nextInt();
                if (j == index) {
                    if (!indexSet.contains(tmp)) {
                        ans += tmp;
                        indexSet.add(tmp);
                        first = true;
                    }
                } else if (j != refIndex && j != transfromIndex) {
                    line += tmp;
                }
                src[i][j] = tmp;
            }
            if (first) {
                ans += line;
            }
        }
        for (int i = 0; i < n; i++) {
            int idx = src[i][index];
            int trasn = src[i][transfromIndex];
            transformSet.add(trasn);
            POS p = POS.of(idx, trasn);
            map.put(p, map.getOrDefault(p, 0) + src[i][refIndex]);
        }
        for (int i : transformSet) {
            for (int idx : indexSet) {
                ans += map.getOrDefault(POS.of(idx, i), -1);
            }
        }
        System.out.println(ans);
    }

    static class POS {
        int index;
        int ref;

        public POS(int index, int ref) {
            this.index = index;
            this.ref = ref;
        }

        public static POS of(int index, int ref) {
            return new POS(index, ref);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            POS pos = (POS) o;

            if (index != pos.index) return false;
            return ref == pos.ref;
        }

        @Override
        public int hashCode() {
            int result = index;
            result = 31 * result + ref;
            return result;
        }
    }
}
