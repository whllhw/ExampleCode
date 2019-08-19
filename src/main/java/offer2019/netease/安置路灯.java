package offer2019.netease;

import java.util.Scanner;

public class 安置路灯 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        System.out.println(solve("...XX....XX".toCharArray()));
        int i = sc.nextInt();
        while (i > 0) {
            i--;
            sc.nextInt();
            System.out.println(solve(sc.next().toCharArray()));
        }
    }

    private static int solve(char[] ch) {
        // 贪心法！使路灯照到足够远的地方
        int len = ch.length;
        if (len == 1) {
            if (ch[0] == '.')
                return 1;
            else
                return 0;
        }
        int ans = 0;
        int pos = 1;
        while (pos < len) {
            if (ch[pos - 1] == '.') {
                ans++;
                pos += 3;
            } else {
                pos++;
            }
        }
        pos -= 3;
        if (pos == len - 3) {
            if (ch[len - 1] == '.') {
                ans++;
            }
        }
        return ans;
    }
}
