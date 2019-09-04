package 剑指offer;

import java.util.ArrayList;
import java.util.List;

public class T6 {


    public static void main(String[] args) {
        T6 t = new T6();

        List<Integer> preorder = new ArrayList<>();
        List<Integer> inorder = new ArrayList<>();
        for (int i : new int[]{
                1, 2, 4, 7, 3, 5, 6, 8
        }) {
            preorder.add(i);
        }
        for (int i : new int[]{
                4, 7, 2, 1, 5, 3, 8, 6
        }) {
            inorder.add(i);
        }
        TreeNode root = t.construct(preorder, inorder);
        System.out.println(root);
        System.out.println(t.reConstructBinaryTree(new int[]{
                1, 2, 4, 7, 3, 5, 6, 8
        }, new int[]{
                4, 7, 2, 1, 5, 3, 8, 6
        }));
    }

    public TreeNode construct(List<Integer> preorder,
                              List<Integer> inorder) {
        if (preorder.size() == 0 && inorder.size() == 0) {
            return null;
        }
        if (preorder.size() != inorder.size()) {
            throw new IllegalArgumentException();
        }
        // 前序遍历，第一个为root
        int rootVal = preorder.get(0);
        TreeNode root = new TreeNode(rootVal, null, null);
        // 找到左节点 0到pos-1，都是左节点
        int pos = inorder.indexOf(rootVal);
        root.left = construct(
                preorder.subList(1, pos + 1),
                inorder.subList(0, pos)
        );
        // 右节点是剩下的数据
        root.right = construct(
                preorder.subList(pos + 1, preorder.size()),
                inorder.subList(pos + 1, inorder.size())
        );
        return root;
    }

    private TreeNode reConstructBinaryTree(int[] pre, int startPre, int stopPre, int[] in, int startIn, int stopIn) {
        if (startPre > stopPre || startIn > stopIn) {
            return null;
        }
        TreeNode root = new TreeNode(pre[startPre]);

        for (int i = startIn; i <= stopIn; i++) {
            if (in[i] == pre[startPre]) {
                root.left = reConstructBinaryTree(
                        pre, startPre + 1, startPre + i - startIn,
                        in, startIn, i - 1);
                root.right = reConstructBinaryTree(
                        pre, startPre + i - startIn + 1, stopPre,
                        in, i + 1, stopIn
                );
                break;
            }
        }
        return root;
    }

    /**
     * 重建二叉树
     * 提供前序遍历和中序遍历，重建二叉树
     *
     * @param pre 前序遍历
     * @param in  中序遍历
     * @return
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        // 思路
        // 由前序得到根节点，
        // 再由中序得到左右节点
        // 即可把原序列划分为左子树，右子树了，得到了子树的遍历，
        // 递归解决即可
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }
}

