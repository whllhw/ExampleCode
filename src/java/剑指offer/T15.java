package 剑指offer;

public class T15 {
    /**
     * 链表中倒数第k个结点
     * <p>
     * 注意代码鲁棒性
     *
     * @param head 头
     * @param k    倒数第k个
     * @return 结点
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        // 思路：
        // 指针赛跑，p先跑k-1，q再出发。p到终点，则q是倒数第k个节点
        // 鲁棒性：
        // 1. 输入k<=0
        // 2. head为空
        // 3. 输入k大于链表长度
        if (k <= 0)
            return null;
        if (head == null)
            return null;
        ListNode p = head;
        ListNode q = head;
        for (int i = 0; i < k - 1; i++) {
            if (p.next != null)
                p = p.next;
            else
                return null;
        }
        while (p.next != null) {
            p = p.next;
            q = q.next;
        }
        return q;
    }
}