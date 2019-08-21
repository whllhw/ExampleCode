import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class poj3684_弹性碰撞 {
    public static void main(String[] args) {
        // 弹性碰撞问题直接让两者相穿越，不用考虑交换位置速度等。
        // 但是最后求距离时需要加上2Ri

        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int caseSize = sc.nextInt();
        final double g = 10d;
        while (caseSize > 0) {
            caseSize--;
            int N = sc.nextInt();
            int H = sc.nextInt();
            int R = sc.nextInt();
            int T = sc.nextInt();
            double[] h = new double[N];

            for (int i = 0; i < N; i++) {
                h[i] = solve(T - i, H, g);
            }
            // 碰撞后的相对位置不变，故要进行排序
            // 排序后的距离才是原位置
            Arrays.sort(h);
            for (int i = 0; i < N; i++) {
                out.print(String.format("%.2f ", h[i] + 2 * R * i / 100.0));
            }
            out.println();
        }


        sc.close();
        out.close();
    }

    private static double solve(int T, int H, double g) {
        if (T < 0)
            return H;
        double t = Math.sqrt(2 * H / g);
        int k = (int) (T / t);
        double d;
        if (k % 2 == 0) {
            d = T - k * t;
        } else {
            d = k * t + t - T;
        }
        return H - g * d * d / 2;
    }
}
