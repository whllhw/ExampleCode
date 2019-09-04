package 剑指offer;

public class T24 {
    public static void main(String[] args) {
        T24 t = new T24();
        System.out.println(t.VerifySquenceOfBST(new int[]{
                        5, 7, 6, 9, 11, 10, 8
                })
        );
    }

    /**
     * 二叉搜索树的后序遍历序列
     * 判断是否是某二叉搜索树的后序遍历序列
     *
     * @param sequence 遍历序列
     * @return bool
     */
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0)
            return false;
        return helper(sequence, 0, sequence.length - 1);
    }

    private boolean helper(int[] sequence, int start, int end) {
        // 思路：（同第6题，找出根节点后递归）
        // 根据搜索树定义和后序遍历定义可知：
        // 最后一个数为根节点
        // 左节点都小于根节点
        // 右节点都大于根节点

        // 于是可以递归判断，找出左子树，右子树进行判断
        if (end - start + 1 <= 0)
            return true;
        int root = sequence[end];
        // 找出小于根节点的左子树
        int i = start;
        for (; i < end; i++) {
            if (sequence[i] > root)
                break;
        }
        // 判断右子树是否符合要求，可以提前知道结果
        for (int j = i; j < end; j++) {
            if (sequence[j] < root)
                return false;
        }
        boolean left, right;
        left = helper(sequence, start, i - 1);
        right = helper(sequence, i, end - 1);
        return left && right;
    }
}
