package 剑指offer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;

public class T45 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        T45 t = new T45();
//        Method method0 = t.getClass().getMethod("solve", int.class, int.class);
//        timer(method0, t, 100000, 50);
        Method method = t.getClass().getMethod("solve1", int.class, int.class);
        timer(method, t, 5, 3);
    }

    public static void timer(Method method, Object o, Object... arg) throws InvocationTargetException, IllegalAccessException {
        long t1 = System.nanoTime();
        method.invoke(o, arg);
        long t2 = System.nanoTime();
        System.out.println(t2 - t1);
    }

    /**
     * 圆圈中最后剩下的数字
     * 0 1 2 ... n-1这个数排成圆圈，
     * 删除第m个数，直到剩下一个数。
     * 复杂度O(nm)
     */
    public int solve(int n, int m) {
        if (n == 0) {
            return -1;
        }
        // 模拟环形链表进行操作
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        // 注意此处的iterator删除元素时 next的操作，都是调用get
        // 复杂度为O(n)
        Iterator<Integer> iter = list.iterator();
        int counter = 0;
        // 计数走过的数目，当=m时删除当前节点
        while (list.size() > 1) {
            if (!iter.hasNext()) {
                iter = list.iterator();
            }
            iter.next();
            counter++;
            if (counter == m) {
                counter = 0;
                iter.remove();
            }
        }
        return list.get(0);
    }

    /**
     * 改进版模拟链表
     * 迭代器使用descendingIterator，每次操作复杂度都是O(1)
     * 但是是原来的倒序，故插入元素时也倒序插入
     */
    public void solve0(int n, int m) {
        // 模拟环形链表进行操作
        LinkedList<Integer> list = new LinkedList<>();
        // 倒序插入元素
        for (int i = 0; i < n; i++) {
            list.add(n - i - 1);
        }
        Iterator<Integer> iter = list.descendingIterator();
        int counter = 0;
        while (list.size() > 1) {
            if (!iter.hasNext()) {
                iter = list.descendingIterator();
            }
            System.out.println(iter.next());
            counter++;
            if (counter == m) {
                counter = 0;
                iter.remove();
            }
        }
        System.out.println(list.get(0));
    }

    /**
     * 终极改进版
     * 利用推导，用公式解决
     */
    public int solve1(int n, int m) {
        if (n < 1 || m < 1)
            return -1;
        int last = 0;
        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }
        return last;
    }
}
