package 剑指offer;

public class T42 {
    public static void main(String[] args) {
        T42 t = new T42();
        System.out.println(t.LeftRotateString("abcdefg", 7));
    }

    /**
     * 左旋转字符串
     * abcXYZdef ,3 -> XYZdefabc
     * 受反转单词序列的启发
     */
    public String LeftRotateString(String str, int n) {
        // 实现思路：
        // 1. 反转全串    fedZYX|cba
        // 2. 反转前半串  XYZdef|cba
        // 3. 反转后半串  XYZdef|abc
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return str;
        }
        char[] ch = str.toCharArray();
        // 注意取模
        n %= ch.length;
        // 注意计算边界值，不要越界
        reverse(ch, 0, ch.length - 1);
        reverse(ch, 0, ch.length - n - 1);
        reverse(ch, ch.length - n, ch.length - 1);
        return new String(ch);
    }

    /**
     * 反转单词序列
     * <p>
     * i am a student. -> student. a am i
     */
    public String ReverseSentence(String str) {
        // 实现思路：
        // 1. 反转全串
        // 2. 反转单词
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return str;
        }
        char[] ch = str.toCharArray();
        // 1. 先反转整个字符串
        reverse(ch, 0, ch.length - 1);
        int end = 0;
        int start = 0;
        // 2. 对每个单词进行反转
        while (end < ch.length) {
            start = end;
            while (end + 1 < ch.length && ch[end + 1] != ' ') {
                end++;
            }
            reverse(ch, start, end);
            end += 2;
        }
        return new String(ch);
    }

    private final void reverse(char[] ch, int start, int end) {
        char tmp;
        for (int i = start; i <= (end + start) / 2; i++) {
            tmp = ch[i];
            ch[i] = ch[end - i + start];
            ch[end - i + start] = tmp;
        }
    }
}
