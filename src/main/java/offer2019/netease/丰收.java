package offer2019.netease;

import java.util.Scanner;

public class 丰收 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int s;
        int sum = 0;
        int[] map = new int[size];
        for (int i = 0; i < size; i++) {
            s = sc.nextInt();
            sum += s;
            map[i] = sum;
        }
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int index = solve(map, sc.nextInt());
            if (index > 0) {
                System.out.println(index + 1);
            } else {
                System.out.println(-index);
            }
        }
//        Math.floor(); 获得大于等于
//        Math.ceil();  获得小于等于
//        Math.round(); 四舍五入
//        for (int i = 0; i < m; i++) {
//            int input = sc.nextInt();
//            System.out.println(
//                    treeMap.ceilingEntry(input).getValue()
//            );
//        }
    }

    private static int solve(int[] map, int key) {
        int left = 0;
        int right = map.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) >>> 1;
            int midVal = map[mid];
            if (midVal == key) {
                return mid;
            } else if (midVal > key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -(left + 1);
    }
}
