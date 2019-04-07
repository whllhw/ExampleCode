package offer;

import java.io.PrintStream;

public class T10 {
    static PrintStream out = System.out;

    public static void main(String[] args) {
        solveW(9);
        solveW(-1);
        solveW(Integer.MAX_VALUE);
        solveW(Integer.MIN_VALUE);
    }

    /**
     * P78页，求二进制中1的个数
     * 测试集：9 -1 0x8000_0000 0x7fff_ffff
     * 最佳方法：
     * 1100 & (1100 - 1) = 1100 & 1011
     * = 1000 将最右边的1去除掉了！
     * 拓展：
     * 1. 判断是不是2的整数次方
     * 只有一位1
     * 2. 判断两个数改变多少位才能相同：
     * 1. 异或
     * 2. 统计1的个数
     */
    static void solveW(int x) {
        int counter = 0;
        while (x != 0) {
            counter++;
            x = (x - 1) & x;
        }
        out.println(counter);
    }

    /**
     * 普通解法，移动32次判断每位
     */
    static void solve(int x) {
        int ans = 0;
        int flag = 1;
        int counter = 0;
        while (counter++ < 32) {
            if ((x & flag) > 0) {
                ans++;
            }
            flag <<= 1;
        }
        out.println(x < 0 ? ans + 1 : ans);
    }

    static void solveBug(int x) {
        // 边界值异常 cx = 0
        // 0x8000_0000_0000_0000
        // 因为补码=反码+1，则反码=补码-1 再求个反得到原码
        // -Integer.MIN_VALUE = Integer.MIN_VALUE
        // 补码  0x8000_0000 - 1
        // 反码  0x7fff_ffff
        // 求反  0x8000_0000
        // 故相同
        int cx = 0;
        if (x < 0) {
            x = -x;
        }
        while (x > 0) {
            if ((x & 1) == 1) {
                cx++;
            }
            x >>= 1;
        }
        out.println(cx);
    }
}
