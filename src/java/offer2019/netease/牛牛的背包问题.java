package offer2019.netease;

import java.util.Scanner;

public class 牛牛的背包问题 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();

        int M = sc.nextInt();
        long sum = 0;
        int[] V = new int[size];
        for (int i = 0; i < size; i++) {
            V[i] = sc.nextInt();
            sum += V[i];
        }
        if (sum <= M) {
            System.out.println((int) Math.pow(2, V.length));
        } else {
            System.out.println(solve(M, V, 0));
        }
    }

    private static int solve(int M, int[] V, int pos) {
        if (pos == V.length) {
            return 1;
        }
        // 不放入该物品
        int a = solve(M, V, pos + 1);
        int b = 0;
        if (M >= V[pos]) {
            b = solve(M - V[pos], V, pos + 1);
        }
        return a + b;
    }
}
