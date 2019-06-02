package offer;

public class T34 {
    /**
     * 丑数
     * ：质因子 2 3 5的数
     * 找出第index个丑数
     *
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution(int index) {
        // 思路：
        // 使用空间换时间
        // 丑数必然是由丑数生成的，某个数×2 ×3 ×5
        // 但是为了记录顺序，选取三个其中最小的数
        // 于是维护三个指针，当由其产生的值最小时，则移动到下一个位置
        if (index <= 0)
            return 0;
        int[] nums = new int[index];
        nums[0] = 1;
        int nextIndex = 1;
        int next2Index = 0;
        int next3Index = 0;
        int next5Index = 0;
        while (nextIndex < index) {
            int next = Math.min(nums[next5Index] * 5,
                    Math.min(nums[next2Index] * 2,
                            nums[next3Index] * 3));
            nums[nextIndex] = next;
            if (nums[next2Index] * 2 == next)
                next2Index++;
            if (nums[next3Index] * 3 == next)
                next3Index++;
            if (nums[next5Index] * 5 == next)
                next5Index++;
            nextIndex++;
        }
        return nums[index - 1];
    }
}
