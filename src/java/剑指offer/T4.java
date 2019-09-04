package 剑指offer;

public class T4 {
    public static void main(String[] args) {
        System.out.println(new T4().replaceSpace("i am a  student"));
        System.out.println(new T4().replaceSpace(""));
        System.out.println(new T4().replaceSpace(null));
    }

    /**
     * 替换空格，复杂度O(n)
     *
     * @param ordinal 原始字符串
     * @return 替换后的字符串
     */
    public String replaceSpace(String ordinal) {
        // 思路：
        // 先获取空格的个数，即可确定处理后字符串的长度
        // 设置两个指针，p1 指向 ordinal的末尾
        // p2 指向 res的末尾
        // 当两个指针不相等时，需要继续移动
        // 遇到空格直接赋值%20，移动3格+1格
        // 遇到非空格复制
        // 两个指针相等时无需再移动
        // =====================
        // 从后往前复制，避免多次复制

        char[] str = ordinal.toCharArray();
        int size = str.length;

        for (char s : str) {
            if (s == ' ') {
                size += 2;
            }
        }
        char[] res = new char[size];
        int p1 = str.length - 1;
        int p2 = res.length - 1;

        while (p1 >= 0 && p2 >= 0) {
            if (str[p1] != ' ') {
                res[p2] = str[p1];
                p1--;
                p2--;
            } else {
                p1--;
                res[p2--] = '0';
                res[p2--] = '2';
                res[p2--] = '%';
            }
        }
        return new String(res);
    }
}
