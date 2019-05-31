package offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class T28 {
    public static void main(String[] args) {
        T28 t = new T28();
        t.Permutation("aaA");
    }

    /**
     * 字符串的排列
     *
     * @param str string
     * @return 排列
     */
    public ArrayList<String> Permutation(String str) {
        // 要求的额外字典序（Collections.sort）
        if (str == null || str.length() == 0)
            return new ArrayList<>();
        ArrayList<String> ans = new ArrayList<>();
        char[] ch = str.toCharArray();
        helper(ch, 0, ans);
        Collections.sort(ans);
        return ans;
    }

    private void helper(char[] ch, int j, ArrayList<String> ans) {
        // 递归求全排列
        // 并需要去重（hashSet）
        // 从第一个字符开始，每个数与它后面的字符进行交换
        // 当要交换的两个字符相同则不进行交换
        // ----------------------------------------
        // 遍历所有可能出现在第一个（j）的字符
        // 固定第一个字符，求后面字符排列（递归）
        if (j == ch.length - 1) {
            ans.add(new String(ch));
            return;
        }
        HashSet<Character> hashSet = new HashSet<>();
        for (int i = j; i < ch.length; i++) {
            // 1. j == i 原先的序列添加
            // 2. 用hashSet保证不会交换相同的字符，去除了重复
            if (j == i || !hashSet.contains(ch[i])) {
                hashSet.add(ch[i]);
                char x = ch[i];
                ch[i] = ch[j];
                ch[j] = x;
                // 继续对后面的字符进行交换
                helper(ch, j + 1, ans);

                ch[j] = ch[i];
                ch[i] = x;
            }
        }
    }
}
