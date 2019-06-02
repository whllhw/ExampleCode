package offer;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class T35 {
    public static void main(String[] args) {
        T35 t = new T35();
        System.out.println(t.solve("abaac"));
    }

    /**
     * 第一个只出现一次的字符
     * 使用util有点麻烦，迭代时要用迭代器
     */
    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        LinkedHashMap<Character, Integer> linkedHashMap = new LinkedHashMap<>();
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            char x = str.charAt(i);
            if (set.contains(x)) {
                linkedHashMap.remove(x);
            } else {
                set.add(x);
                linkedHashMap.put(x, i);
            }
        }
        Set<Map.Entry<Character, Integer>> res = linkedHashMap.entrySet();
        if (res.isEmpty()) {
            return -1;
        } else {
            return res.iterator().next().getValue();
        }
    }

    /**
     * 改进方法
     * 直接使用int[] 进行缓存，
     * 扫描一遍记录出现的次数，
     * 再扫描一遍记录次数为1的位置进行返回
     * 复杂度O(n)
     */
    public int solve(String str) {
        int[] map = new int[257];
        for (int i = 0; i < str.length(); i++) {
            map[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (map[str.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }
}
