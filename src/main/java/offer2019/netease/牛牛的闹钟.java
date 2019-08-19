package offer2019.netease;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class 牛牛的闹钟 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        TreeMap<Integer, String> map = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            int hh = sc.nextInt();
            int mm = sc.nextInt();
            map.put(hh * 60 + mm, hh + " " + mm);
        }
        int delay = sc.nextInt();
        int schoolTime = sc.nextInt() * 60 + sc.nextInt();

        Map.Entry<Integer, String> entry = map.floorEntry(schoolTime - delay);
        System.out.println(((Map.Entry) entry).getValue());
    }
}
