package 剑指offer;

import java.util.LinkedList;

public class T27 {
    private TreeNode leftLast;

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(10, new TreeNode(6, new TreeNode(4), new TreeNode(8)
//        ), new TreeNode(14, new TreeNode(12), new TreeNode(16))
//        );
        TreeNode root = new TreeNode(1, new TreeNode(0), new TreeNode(2));
        T27 t = new T27();
        TreeNode head = t.Convert(root);
        System.out.println(head);
    }

    /**
     * 二叉搜索树与双向链表
     *
     * @param root 搜索树
     * @return
     */
    public TreeNode Convert(TreeNode root) {
        // 未改进版递归
        // 思路:递归结束条件，本级要返回的值，本级该做的事情
        // 定义返回链表头的函数
        // 同样使用中序遍历来获取有序链表
        // 在进行赋值时遍历到链表末尾才进行连接
        // 处理玩当前节点再对右子树进行处理

        // 注意判空
        if (root == null)
            return null;
        if (root.left == null && root.right == null)
            return root;
        // 获取左子树的链表头
        TreeNode left = Convert(root.left);
        TreeNode p = left;
        // 找到链表末尾
        while (p != null && p.right != null)
            p = p.right;
        // 进行连接
        if (left != null) {
            root.left = p;
            p.right = root;
        }
        // 遍历右子树
        TreeNode right = Convert(root.right);
        // 连接
        if (right != null) {
            right.left = root;
            root.right = right;
        }
        // 返回链表头
        return left == null ? root : left;
    }

    /**
     * 递归改进版
     *
     * @param root 节点
     * @return 链表头
     */
    public TreeNode convert0(TreeNode root) {
        // 添加了一个leftLast记录上次的链表末尾
        // 共两种情况时当前节点是末尾
        if (root == null)
            return null;
        if (root.left == null && root.right == null) {
            // 1. 叶子节点
            leftLast = root;
            return root;
        }
        TreeNode left = convert0(root.left);
        if (left != null) {
            leftLast.right = root;
            root.left = leftLast;
        }
        // 2.添加进了左子树，当前节点就是末尾了
        leftLast = root;
        TreeNode right = convert0(root.right);
        if (right != null) {
            root.right = right;
            right.left = root;
        }
        return left == null ? root : left;
    }

    /**
     * 非递归版本
     *
     * @param root
     * @return
     */
    public TreeNode convert1(TreeNode root) {
        // 思路：
        // 同中序遍历，不过需要flag记录链表头
        // 以及pre节点，用于连接
        if (root == null)
            return null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;
        TreeNode pre = null;
        boolean first = true;
        while (p != null || stack.size() != 0) {
            // 一直往左走
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            // 链表头则保存下来
            if (first) {
                root = p;
                pre = root;
                first = false;
            } else {
                // 连接
                // 并更新pre
                pre.right = p;
                p.left = pre;
                pre = p;
            }
            // 中序标准代码
            p = p.right;
        }
        return root;
    }

}
