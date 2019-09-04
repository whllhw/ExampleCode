package offer2020.jd;

import java.io.BufferedInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class 合唱队形 {
    /**
     * 69079936
     * 236011312
     * 77957850
     * 653604087
     * 443890802
     * 277126428
     * 755625552
     * 768751840
     * 993860213
     * 882053548
     * <p>
     * 【69079936】1
     * 【236011312 77957850】2 3
     * 【653604087 443890802 277126428】 4 5 6
     * 【755625552】7
     * 【768751840】8
     * 【993860213 882053548】9
     * 数据过27%
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        PrintStream out = new PrintStream(System.out);
        int n = sc.nextInt();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = sc.nextInt();
        }
        out.println(solve(ans));
        out.close();
        sc.close();
    }

    private static int solve(int[] ans) {
        // 连续递减数量的个数
        int counter = 0;
        int perNum = Integer.MAX_VALUE;
        int pos = 0;
        while (pos < ans.length) {
            while (pos < ans.length && ans[pos] <= perNum) {
                perNum = ans[pos];
                pos++;
            }
            counter++;
            perNum = Integer.MAX_VALUE;
        }
        return counter;
    }

}
