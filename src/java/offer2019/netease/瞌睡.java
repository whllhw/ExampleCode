package offer2019.netease;

import java.util.Scanner;

public class 瞌睡 {
    public static void main(String[] args) {
        // 即求解连续k个数的最大值
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        long ret = 0;
        for (int i = 0; i < n; i++) {
            if (sc.nextInt() == 1) {
                ret += A[i];
                A[i] = 0;
            }
        }
        long ans = 0;
        int pos = 0;
        for (int i = 0; i < n && i < k; i++) {
            ans += A[i];
        }
        long max = ans;
        pos = k;
        while (pos < n) {
            ans += A[pos] - A[pos - k];
            max = Math.max(ans, max);
            pos++;
        }
        System.out.println(ret + max);
    }
}
