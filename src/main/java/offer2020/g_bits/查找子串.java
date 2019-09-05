package offer2020.g_bits;

import java.util.Scanner;

public class 查找子串 {

    public static void main(String[] args) {
        // 输出子串位置的最大值
        // abcabc abc
        // 4
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int maxPos = 0;
        char[] target = str[1].toCharArray();
        char[] sources = str[0].toCharArray();
        for (int i = 0; i < sources.length; i++) {
            if (target[0] != sources[i]) {
                continue;
            }
            int j = i;
            int pos = 0;
            while (pos < target.length) {
                // 删除字符去匹配
                while (j < sources.length && target[pos] != sources[j]) {
                    j++;
                }
                if (j < sources.length) {
                    pos++;
                } else {
                    break;
                }
            }
            if (pos == target.length) {
                maxPos = Math.max(maxPos, i);
            }
        }
        System.out.println(maxPos == 0 ? 0 : maxPos + 1);
    }
}