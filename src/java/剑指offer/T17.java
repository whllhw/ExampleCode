package 剑指offer;

public class T17 {
    /**
     * 合并两个排序的链表
     *
     * @param list1 递增链表1
     * @param list2 递增链表2
     * @return 新链表
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        // 考虑鲁棒性，注意输入为空的处理
        // 解法类似归并排序的过程。
        // 这里使用到递归去求解
        // 明显选出节点后，继续选节点的过程能使用递归完成。
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        ListNode node;
        if (list1.val < list2.val) {
            node = list1;
            node.next = Merge(list1.next, list2);
        } else {
            node = list2;
            node.next = Merge(list1, list2.next);
        }
        return node;
    }

}
