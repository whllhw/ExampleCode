package offer;

public class T31 {
    public static void main(String[] args) {
        T31 t = new T31();
        t.FindGreatestSumOfSubArray(new int[]{
                1, -2, 3, 10, -4, 7, 2, -5
        });
    }

    /**
     * 连续子数组的最大和
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        // 动态规划
        // dp(i) = nums[i]         dp(i-1) <= 0
        // dp(i) = nums[i]+dp(i-1) dp(i-1) > 0
        // 虽然加上一个负值，但能期望下一值更大，抵消掉

        // 当总和是负值时肯定不是最优的方法
        if (array == null)
            return 0;
        int dp = 0;
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (dp <= 0)
                dp = array[i];
            else
                dp += array[i];
            max = Math.max(max, dp);
        }
        return max;
    }
}
