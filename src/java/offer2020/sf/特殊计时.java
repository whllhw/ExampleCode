package offer2020.sf;

import java.util.LinkedList;
import java.util.Scanner;

public class 特殊计时 {

    public static void main(String[] args) {

        short ix, jx;
        int counter = 0;
        for (ix = 1, jx = 5; ix != jx; ix += 5, jx += 7) {
            counter++;
        }
        System.out.println(counter);

        Scanner sc = new Scanner(System.in);
        String[] strs = sc.next().split(":");
        // 0-9 表示 0-9
        // A-Z 表示 10-35
        // AC 64%
        LinkedList<Integer> ret = new LinkedList<>();
        for (int i = 35; i >= 2; i--) {
            try {
                int h = Integer.valueOf(strs[0], i);
                int m = 0;
                if (h == 0) {
                    // h 无限制了！
                    for (int j = 60; j >= 2; j--) {
                        try {
                            m = parseInt(strs[1], j);
                            if (m < 60) {
                                ret.push(j);
                            }
                        } catch (NumberFormatException e) {
                            break;
                        }
                    }
                    break;
                }
                m = Integer.valueOf(strs[1], i);
                if (h < 24 && m < 60) {
                    ret.push(i);
                }
            } catch (NumberFormatException ignored) {
                break;
            }
        }
        if (ret.isEmpty()) {
            System.out.println("-1");
            return;
        }
        System.out.print(ret.pop());
        while (!ret.isEmpty()) {
            System.out.print(" ");
            System.out.print(ret.pop());
        }
    }

    private static int parseInt(String s, int radix) {
        int result = 0;
        int i = 0, len = s.length();
        int limit = -Integer.MAX_VALUE;
        int multmin;
        int digit;

        if (len > 0) {
            multmin = limit / radix;
            while (i < len) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                digit = digit(s.charAt(i++), radix);
                if (digit < 0) {
                    throw new NumberFormatException();
                }
                if (result < multmin) {
                    throw new NumberFormatException();
                }
                result *= radix;
                if (result < limit + digit) {
                    throw new NumberFormatException();
                }
                result -= digit;
            }
        } else {
            throw new NumberFormatException();
        }
        return result;
    }

    private static int digit(char ch, int radix) {
        if (ch >= 'A' && ch <= 'Z') {
            return radix - (10 + ch - 'A');
        } else if (ch >= '0' && ch <= '9') {
            return radix - (ch - '0');
        }
        return -1;
    }
}
