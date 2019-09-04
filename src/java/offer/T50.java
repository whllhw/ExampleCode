package offer;

import java.util.Iterator;
import java.util.LinkedList;

public class T50 {
    public static void main(String[] args) {
        T50 t = new T50();
        TreeNode root = getTree();
        System.out.println(t.solve(root,
                new TreeNode(5), new TreeNode(4)));
    }

    public static TreeNode getTree() {
        T6 t = new T6();
        return t.reConstructBinaryTree(new int[]{
                3, 5, 6, 2, 7, 4, 1, 0, 8
        }, new int[]{
                6, 5, 7, 2, 4, 3, 0, 1, 8
        });
    }

    /**
     * 二叉数的最近公共祖先
     * 此题牛客上没有，leetcode上有
     * 1. 当树是二叉树排序树时为简单，只需要让当前的节点恰好位于两者之间，
     * 那么就是最近的公共祖先。
     * 2. 当是普通的二叉树/树时，若有指向父节点的指针也比较简单
     * 从该节点出发到根节点可以看成链表，那么，就是求相交链表的第一个交点
     * 3. 若没有父指针，则比较麻烦了
     */
    public TreeNode solve(TreeNode root, TreeNode val1, TreeNode val2) {
// 思路：（利用前序遍历记录路径，转化为第2个问题
// 1. 分别前序遍历，获取到该节点的路径
// 2. 路径用链表表示，java中使用逆序迭代器，（即是从根节点出发的路径）
// 3. 找到第一个交点即可
        LinkedList<TreeNode> path1 = new LinkedList<>();
        LinkedList<TreeNode> path2 = new LinkedList<>();
        // 1.
        if (!preVisit(path1, root, val1) || !preVisit(path2, root, val2)) {
            return null;
        }
        path1.push(val1);
        path2.push(val2);
        Iterator<TreeNode> fast = path1.descendingIterator();
        Iterator<TreeNode> slow = path2.descendingIterator();
        TreeNode x = null;
        while (slow.hasNext() && fast.hasNext()) {
            TreeNode p1 = slow.next();
            TreeNode p2 = fast.next();
            // 3.当出现不相等的节点时，上一个节点就是最近公共祖先
            if (p1.val == p2.val) {
                x = p1;
            } else {
                break;
            }
        }
        return x;
    }

    /**
     * 前序遍历获取节点路径
     */
    private boolean preVisit(LinkedList<TreeNode> path, TreeNode root, TreeNode target) {
        if (root == null) {
            return false;
        }
        if (root.val == target.val) {
            return true;
        }
        // 加入路径中
        path.push(root);
        boolean found = false;
        // 判断左右子树中是否存在target节点
        found = preVisit(path, root.left, target);
        found = found || preVisit(path, root.right, target);
        if (!found) {
            // 未找到，路径必定不包含当前节点，退栈
            path.pop();
        } else {
            return true;
        }
        return false;
    }
}
