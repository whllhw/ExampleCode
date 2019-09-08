package offer2020.iqiyi;

import java.util.Scanner;

public class 红黑球 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        System.out.println(String.format("%.5f", cal(x, y)));
        sc.close();
    }

    private static double cal(int n, int m) {
        if (n >= 1 && m == 0) {
            return 1;
        }
        if (n <= 0 || m <= 0) {
            return 0;
        }
        double curr = n / (m + n + 0.0);
        double fail = allFail(n, m);
        double next1 = cal(n - 1, m - 2);
        double next0 = cal(n, m - 3);
        curr = curr + fail * (((m - 2) / (n + m - 2.0)) * next0 + (1 - (m - 2) / (n + m - 2.0)) * next1);
        return curr;
    }

    /**
     * A B 全部不胜利，可以进入下一轮
     * 进入下一轮的条件！！
     */
    private static double allFail(int n, int m) {
        if (m < 2) {
            return 0;
        }
        return m / (n + m + 0.0) * (m - 1) / (n + m - 1.0);
    }

// wrong:
//    private static double solve(int x, int y) {
//        System.out.println(x + " " + y);
//        // 无红球，B直接获胜
//        if (x == 0) {
//            return 0d;
//        }
//        // 当前直接抽中的概率
//        double ret = (double) x / (double) (x + y);
//        double ans = ret;
//        // 拿了黑球
//        y--;
//        if (x + y > 3) {
//            ans += (1 - ret) * (double) y / (double) (x + y) * solve(x - 1, y - 1);
//            ans += (1 - ret) * (double) y / (double) (x + y) * solve(x, y - 2);
//        } else if (x + y == 3) {
//            if (x == 1 || x == 2)
//                ans += (1 - ret) * (double) 1 / 3;
//        }
//        return ans;
//    }

}
