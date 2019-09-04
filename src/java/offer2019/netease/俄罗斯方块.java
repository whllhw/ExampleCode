package offer2019.netease;

import java.util.Scanner;

public class 俄罗斯方块 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] docker = new int[n];
        for (int i = 0; i < m; i++) {
            docker[sc.nextInt() - 1]++;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, docker[i]);
        }
        System.out.println(min);
    }
}
