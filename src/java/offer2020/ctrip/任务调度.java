package offer2020.ctrip;

import java.util.Scanner;

public class 任务调度 {

}


class Main2 {

    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    // 输入
    // m=3
    // 任务耗时=1 5 3 4 2
    // 将任务分给m个分布式系统，每台服务器上的任务必须连续
    // 求运行完这些任务的最短时间
    // 第一台 1 5
    // 第二台 3
    // 第三台 4 2
    // 最短时间为 6
    // https://blog.csdn.net/qq_35314344/article/details/77504765
    static int schedule(int m, int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        // 理想化解
        int target = sum / m;
        int max = Integer.MIN_VALUE;
        int subSum = 0;
        int used = 0;
        int remain = sum;
        for (int i = 0; i < array.length; i++) {
            if (subSum + array[i] <= target) {
                subSum += array[i];
                remain -= array[i];
            } else {
//                // 若放到前一台机器上
//                // 会让剩余的平均值减小，那么就不放到前一台。
//                if ((m - used - 1) > 0 && (remain - array[i]) / (m - used - 1) <= target) {
//                    max = Math.max(max,subSum);
//                } else {
                max = Math.max(max, subSum + array[i]);
                remain -= array[i];
//                }
                subSum = 0;
                used++;
            }
        }
        return max;
    }

    /******************************结束写代码******************************/


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int size = in.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = in.nextInt();
        }
        int res = schedule(m, array);
        System.out.println(res);
    }
}
