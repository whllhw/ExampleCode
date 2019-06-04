package offer;

import java.util.Arrays;

public class T44 {
    public static void main(String[] args) {
        T44 t = new T44();
        System.out.println(t.isContinuous(new int[]{
                1, 2, 3, 4, 5}));
    }

    /**
     * 扑克牌顺子
     * 判断给定的序列是否是顺子，
     * 0可以替换成任何数字
     */
    public boolean isContinuous(int[] numbers) {
        // 思路：
        // 1. 排序
        // 2. 统计王的个数
        // 3. 统计剩下的数的”间隔“
        if (numbers == null || numbers.length == 0) {
            return false;
        }

        int kingSum = 0;
        Arrays.sort(numbers);
        for (int pos = 0; pos < numbers.length; pos++) {
            if (numbers[pos] == 0)
                kingSum++;
            else
                break;
        }

        int small = kingSum;
        int big = small + 1;
        int flag = 0;
        while (big < numbers.length) {
            // 存在对子，不可能是顺子了
            if (numbers[small] == numbers[big])
                return false;
            // 3.统计间隔
            flag += numbers[big] - numbers[small] - 1;
            small = big;
            big++;
        }
        // 间隔数小于等于王的数量即是顺子
        return kingSum >= flag;
    }
}
