package offer2020.sf;

import java.io.BufferedInputStream;
import java.util.Scanner;

// 圆形的面积最大
// 经过尽量少的操作就是使用尽量少的边长
public class 圈格子 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));

        int caseNum = sc.nextInt();
        for (int i = 0; i < caseNum; i++) {
            System.out.println(solve(sc.nextInt()));
        }
    }

    private static int solve(int target) {
        return (int) Math.sqrt(target * Math.PI) * 2;
    }
}


