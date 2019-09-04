package 剑指offer;

public class T12 {
    private char[] ch;

    public static void main(String[] args) {
        T12 t12 = new T12();
        t12.printN0(3);
    }

    /**
     * 打印1到最大n位的数
     *
     * @param n 位数
     */
    public void printN(int n) {
        // 思路，用int类型会溢出
        // 可以使用字符串保存
        // 考察：主要体现在字符串进位+溢出判断

        this.ch = new char[n + 1];
        for (int i = 0; i < ch.length; i++) {
            ch[i] = '0';
        }
        while (!incr()) {
            print();
        }
    }

    private boolean incr() {
        int take = 1;
        for (int i = ch.length - 1; i >= 0; i--) {
            if (i == 0) {
                return true;
            }
            int temp = ch[i] - '0';
            if (temp + take == 10) {
                ch[i] = '0';
                take = 1;
            } else {
                ch[i] = (char) (temp + 1 + '0');
                return false;
            }
        }
        return true;
    }

    /**
     * 全排列方法求解
     *
     * @param n 位数
     */
    public void printN0(int n) {
        // 实现：
        // 调用分析，每层都控制下一层的数从0到9，
        // 最后一层直接打印即可。即可实现了全排列
        // for10  for10  for10
        // +--+   +--+    +--+
        // +  + ->+  + -> +  +
        // +--+   +--+    +--+
        if (n <= 0) {
            return;
        }
        ch = new char[n];
        for (int i = 0; i < 10; i++) {
            ch[0] = (char) (i + '0');
            recursive(0);
        }
    }

    private void recursive(int index) {
        if (index == ch.length - 1) {
            print();
            return;
        }
        for (int i = 0; i < 10; i++) {
            ch[index + 1] = (char) (i + '0');
            recursive(index + 1);
        }
    }

    private void print() {
        int pos = 0;
        while (pos < ch.length && ch[pos] == '0') {
            pos++;
        }
        for (int i = pos; i < ch.length; i++) {
            System.out.print(ch[i]);
        }
        System.out.println();
    }
}
