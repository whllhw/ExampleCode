package nlp;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 分词算法：最大正向匹配
 */
public class Main {
    private static final Pattern pattern = Pattern.compile("(\\d+) *(\\S+) *(\\d+) *");
    private final Logger logger = Logger.getLogger(Main.class.getName());
    private HashMap<String, Integer> wordFreq = new HashMap<>();
    private int maxLen = 5;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.loadFreq(new File("H:\\文档\\课件\\自然语言处理\\word_freq_list.txt"));
        String context = main.spilt(new File("H:\\文档\\课件\\自然语言处理\\pku_test.txt"));
        main.saveFile(context, new File("H:\\文档\\课件\\自然语言处理\\pku_test_result.txt"));
        System.out.printf("召回率：%f\n", main.recall(new File("H:\\文档\\课件\\自然语言处理\\pku_test_gold.txt"), context));
        Scanner sc = new Scanner(System.in);
        System.out.println("输入语句即可分词:");
        StringBuilder sb = new StringBuilder();
        while (true) {
            System.out.print("->");
            String s = sc.nextLine();
            if (s.length() == 0) {
                System.out.println(main.split(sb.toString()));
                sb = new StringBuilder();
                continue;
            }
            if ("exit".equals(s)) {
                break;
            }
            sb.append(s);
            sb.append("\n");
        }
    }

    /**
     * 载入字典到HashMap中，用于分词
     */
    public void loadFreq(File file) throws IOException {
        try (FileInputStream input = new FileInputStream(file)) {
            Scanner sc = new Scanner(input, "utf-8");
            String temp = sc.nextLine();
            System.out.println(temp);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                Matcher t = pattern.matcher(str);
                if (!t.find()) {
                    logger.warning(String.format("格式出错:%s", str));
                } else {
                    wordFreq.put(t.group(2), Integer.valueOf(t.group(3)));
                }
            }
        } catch (IOException e) {
            throw e;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        logger.info("载入字典完成");
    }

    /**
     * 读入文件进行分词
     */
    public String spilt(File file) throws IOException {
        String content = null;
        StringBuilder sb = new StringBuilder();
        try (FileInputStream input = new FileInputStream(file)) {
            Scanner sc = new Scanner(input, "utf-8");
            while (sc.hasNextLine()) {
                content = sc.nextLine();
                sb.append(doSplit(content));
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String split(String context) {
        return doSplit(context).toString();
    }

    /**
     * 最大正向匹配算法
     * 从后往前，取最大长度进行遍历，当词在字典中则分开
     * 思路：
     * 使用双指针，不停往前移动，
     * right = left
     * left = max(0,right - maxLen)
     */
    private StringBuilder doSplit(String content) {
        char[] ch = content.toCharArray();
        int left = Math.max(0, ch.length - maxLen);
        int right = ch.length;
        StringBuilder sb = new StringBuilder();
        while (left < right) {
            char[] x = new char[right - left];
            for (int i = left; i < right; i++) {
                x[i - left] = ch[i];
            }
            if (wordFreq.containsKey(new String(x)) || right - left == 1) {
                sb.insert(0, x);
                sb.insert(0, ' ');
                right = left;
                left = Math.max(0, right - maxLen);
            } else {
                left++;
            }
        }
        return sb;
    }

    /**
     * 统计召回率
     * 召回率：TP/(TP+FN)
     * 思路：
     * 找出与金标中正确的分词的个数，并找到分词的数量即可
     */
    public double recall(File gold, String context) throws IOException {
        // TP/(TP+FN)
        try (FileInputStream input = new FileInputStream(gold)) {
            Scanner scanner = new Scanner(input, "utf-8");
            String[] s = context.split("\n");
            int pos = 0;
            int TP = 0;
            int CASE = 0;
            while (scanner.hasNextLine()) {
                String goldTxt = scanner.nextLine();
                if (goldTxt.length() == 0) {
                    continue;
                }
                MultiReturn multiReturn = calu(goldTxt, s[pos]);
                TP += multiReturn.ans;
                CASE += multiReturn.CASE;
                pos++;
            }
            return (double) TP / (double) CASE;
        }
    }

    private MultiReturn calu(String gold, String s) {
        String[] g = gold.split(" ");
        String[] x = s.split(" ");
        LinkedList<String> list1 = new LinkedList<>();
        LinkedList<String> list2 = new LinkedList<>();
        MultiReturn multiReturn = new MultiReturn();
        for (int i = 0; i < g.length; i++) {
            if (g[i].length() > 0) {
                list1.add(g[i]);
            }
        }
        for (int i = 0; i < x.length; i++) {
            if (x[i].length() > 0) {
                list2.add(x[i]);
            }
        }
        multiReturn.CASE = list2.size();
        int ans = 0;
        while (!list1.isEmpty() && !list2.isEmpty()) {
            String s1 = list1.pop();
            String s2 = list2.pop();
            int sumS1 = s1.length();
            int sumS2 = s2.length();
            if (sumS1 == sumS2 && s1.equals(s2)) {
                ans++;
            }
            while (sumS1 != sumS2) {
                while (sumS1 < sumS2 && !list1.isEmpty()) {
                    s1 = list1.pop();
                    sumS1 += s1.length();
                }
                while (sumS2 < sumS1 && !list2.isEmpty()) {
                    s2 = list2.pop();
                    sumS2 += s2.length();
                }
            }
        }
        multiReturn.ans = ans;
        return multiReturn;
    }

    public void saveFile(String context, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            writer.write(context);
            writer.flush();
        }
    }

    static class MultiReturn {
        int ans;
        int CASE;
    }

}
