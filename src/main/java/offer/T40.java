package offer;

public class T40 {
    public static void main(String[] args) {
        T40 t = new T40();
        int[] res = new int[1];
        int[] res2 = new int[1];
        t.solve(new int[]{
                2, 4, 3, 6, 3, 2, 5, 5
        }, res, res2);
        System.out.println(res[0] + "," + res2[0]);
    }

    /**
     * 数组中只出现一次的数字
     * 当只有一个数字时使用一遍异或即可
     * 当有两个数字出现就不同了，但思路类似
     */
    private void solve(int[] nums, int[] a, int[] b) {
        // 1. 遍历数组，获取两个数的异或（必定不会为0，为0则表示两数相等）
        // 2. 异或结果获取二进制第一个1的位置，并根据1的位置进行划分数组
        // 3. （相同的数必定会划分到相同的位置，两个目标数会划分到不同的数组）
        // 4. 输出两个异或的结果即可，这样又回到只出现一次的数字
        if (nums == null) {
            return;
        }
        // 1. 获取两个数的异或
        int xor = 0;
        for (int i : nums) {
            xor ^= i;
        }
        int c = 0;
        int d = 0;
        // 2. 获取二进制第一个1的位置
        int flag = xor - (xor & (xor - 1));
        for (int i : nums) {
            if ((i & flag) > 0) {
                c ^= i;
            } else {
                d ^= i;
            }
        }
        a[0] = c;
        b[0] = d;
    }
}
