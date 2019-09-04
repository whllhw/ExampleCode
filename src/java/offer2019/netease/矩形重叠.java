package offer2019.netease;

import java.util.Scanner;

public class 矩形重叠 {

    // 题目给出n的数量很小，直接暴力求即可
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] x1 = new int[n];
        int[] x2 = new int[n];
        int[] y1 = new int[n];
        int[] y2 = new int[n];
        for (int i = 0; i < n; i++) {
            x1[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            y1[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            x2[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            y2[i] = sc.nextInt();
        }
        int ans = 0;
        int ret = 0;
        for (int x : x1) {
            for (int y : y1) {
                ans = 0;
                for (int i = 0; i < n; i++) {
                    // 重叠部分也是矩形，遍历所有可以组成的矩形，找到其中重叠最多即可！
                    if (x >= x1[i] && x < x2[i]
                            && y >= y1[i] && y < y2[i])
                        ans++;
                }
                ret = Math.max(ans, ret);
            }
        }
        System.out.println(ret);
    }

}
