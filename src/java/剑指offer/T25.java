package 剑指offer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class T25 {
    private LinkedList<TreeNode> stack = new LinkedList<>();

    private ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    private int sum = 0;

    private int target;

    public static void main(String[] args) {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Iterator<Integer> iter = stack.descendingIterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

    /**
     * 二叉树中和为某一值
     *
     * @param root   root
     * @param target 目标值
     * @return 所有符合的路径
     */
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        // 思路：画出访问二叉树的访问过程
        // 找规律得知，用前序遍历可以实现，用栈记录访问的路径，当访问节点时压入，
        // 访问完左右子树时退栈，同时sum减去val值
        if (root == null)
            return ans;
        this.target = target;
        helper(root);
        return ans;
    }

    private void helper(TreeNode root) {
        // 加入路径
        stack.push(root);
        sum += root.val;
        // 当前sum等于目标值，且节点为叶子节点则把路径放入结果中
        if (sum == target && root.left == null && root.right == null) {
            ArrayList<Integer> path = new ArrayList<>();
            // 使用descendingIterator，才是正确的路径，否则是倒序的
            Iterator<TreeNode> iter = stack.descendingIterator();
            while (iter.hasNext()) {
                path.add(iter.next().val);
            }
            ans.add(path);
        }
        if (root.left != null) {
            helper(root.left);
        }
        if (root.right != null) {
            helper(root.right);
        }
        // 退栈时同时减去val
        stack.pop();
        sum -= root.val;
    }
}
