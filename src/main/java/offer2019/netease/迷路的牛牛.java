package offer2019.netease;

import java.util.Scanner;

public class 迷路的牛牛 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextInt();
        System.out.println(solve(sc.next().toCharArray()));
    }


    private static char solve(char[] ch) {
        char[] map = {
                'N', 'E', 'S', 'W'
        };
        int pos = 0;
        for (char c : ch) {
            if (c == 'R') {
                pos++;
                if (pos > map.length) {
                    pos -= map.length;
                }
            } else {
                pos--;
                if (pos < 0) {
                    pos += map.length;
                }
            }
        }
        return map[pos];
    }
}
