package tencent;

import java.util.Arrays;
import java.util.Scanner;

public class Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int L = sc.nextInt();
        struct[] st = new struct[n];
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            st[i] = new struct(a, b, b - a);
        }
        Arrays.sort(st, (t1, t2) -> {
            if (t1.start < t2.start) {
                return -1;
            } else if (t1.start == t2.start) {
                return Integer.compare(t2.length, t1.length);
            } else {
                return 1;
            }
        });
        int pos = 0;
        int posI = 0;
        int counter = 0;
        while (pos < L) {
            while (st[posI].end <= pos) {
                posI++;
                if (posI >= st.length) {
                    System.out.println(-1);
                    return;
                }
            }
            pos += st[posI].length;
            counter++;
        }
        System.out.println(counter);
    }

    static class struct {
        int start;
        int end;
        int length;

        struct(int start, int end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }
    }
}
