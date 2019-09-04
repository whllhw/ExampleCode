package 算法实验.alg;

import 算法实验.template.Base;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static 算法实验.alg.Exp1.*;

public class Exp1Test extends Base {
    public static void main(String[] args) throws FileNotFoundException {
        out.println(System.getProperty("user.dir"));
//        Scanner scanner = new Scanner(new File("G:\\ACM\\code\\src\\main\\java\\alg\\alg\\in.txt"));
//        test1(scanner);
//        test2(scanner);
//        test3(scanner);
    }

    public static void test1(Scanner scanner) {
        int n = scanner.nextInt();
        for (int j = 0; j < n; j++) {
            int size = scanner.nextInt();
            int k = scanner.nextInt();
            int[] nums = new int[size];
            for (int i = 0; i < size; i++) {
                nums[i] = scanner.nextInt();
            }
            System.out.println(topK(nums, 0, nums.length - 1, k));
        }
    }

    public static void test2(Scanner scanner) {
        int n = scanner.nextInt();
        println(roundRobinTour(n));
    }

    public static void test3(Scanner scanner) {
        int caseLen = scanner.nextInt();
        for (int j = 0; j < caseLen; j++) {
            int sizePoint = scanner.nextInt();
            List<Exp1.Point> pointList = new ArrayList<>();
            for (int i = 0; i < sizePoint; i++) {
                pointList.add(new Exp1.Point(scanner.nextDouble(), scanner.nextDouble()));
            }
            System.out.println(closetPoint(pointList));
        }
    }

    public static void testClosetPoint() {
        List<Exp1.Point> pointList = new ArrayList<>();
        pointList.add(new Exp1.Point(9.83, -81.96));
        pointList.add(new Exp1.Point(-88.29, 44.76));
        pointList.add(new Exp1.Point(21.97, -81.49));
        pointList.add(new Exp1.Point(2.44, -1.83));
        pointList.add(new Exp1.Point(-89.17, 63.58));
        pointList.add(new Exp1.Point(20, -49.92));
        pointList.add(new Exp1.Point(-81.21, -48.01));
        pointList.add(new Exp1.Point(-33.28, -49.09));
        pointList.add(new Exp1.Point(-54.05, 12.88));
        pointList.add(new Exp1.Point(-64.85, -53.12));
        pointList.add(new Exp1.Point(12.07, 64.91));
        pointList.add(new Exp1.Point(-72.9, -21.57));
        pointList.add(new Exp1.Point(12.93, -92.71));
        pointList.add(new Exp1.Point(-27.71, -0.19));
        pointList.add(new Exp1.Point(73.17, 32.17));
        Exp1.MutPoint a = closetPoint(pointList);
        System.out.println(a);
        Random r = new Random();
        r.setSeed(0);
        pointList.clear();
        for (int i = 0; i < 100000; i++) {
            pointList.add(new Exp1.Point(r.nextDouble() * 1000000,
                    r.nextDouble() * 1000000));
        }
        System.out.println(closetPoint(pointList));
    }
}
