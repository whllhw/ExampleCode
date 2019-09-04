package tencent;

import java.util.Scanner;
import java.util.TreeMap;

public class Q2_import {
    private static int size;
    private static int counter = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        size = (int) Math.pow(2, n);
        int[] Q = new int[size];
        for (int i = 0; i < size; i++) {
            Q[i] = sc.nextInt();
        }
        int m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            solve(Q, sc.nextInt());
        }

    }

    private static void solve(int[] Q, int q) {
        // 对Q进行翻转
        int eachSize = (int) Math.pow(2, q);
        int pos = 0;
        int temp;
        while (pos < Q.length) {
            for (int i = pos; i < pos + eachSize / 2; i++) {
                temp = Q[i];
                Q[i] = Q[pos + eachSize + pos - i - 1];
                Q[pos + eachSize + pos - i - 1] = temp;
            }
            pos += eachSize;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < size; i++) {
            map.put(Q[i], i + 1);
        }
        counter = 0;
        int[] b = new int[size + 1];
        for (int key : map.descendingKeySet()) {
            int index = map.get(key);
            add(index, b);
            query(index - 1, b);
        }
        System.out.println(counter);
    }

    private static void add(int x, int[] b) {
        for (int i = x; i <= size; i += i & (-i)) {
            b[i]++;
        }
    }

    private static void query(int x, int[] b) {
        for (int i = x; i > 0; i -= i & (-i)) {
            counter += b[i];
        }
    }
}
