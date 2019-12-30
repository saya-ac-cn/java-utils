package offer;

import java.util.Arrays;

/**
 * @Title: Question4
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-12-30 20:56
 * @Description: 题目：输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回
 * 前序遍历：先访问根结点，再访问左子结点，最后访问右子结点。
 * 中序遍历：先访问左子结点，再访问根结点，最后访问右子结点。
 * 后序遍历：先访问左子结点，再访问右子结点，最后访问根结点。
 * <p>
 * 思路：前序遍历序列中，第一个数字总是树的根结点的值。在中序遍历序列中，根结点的值在序列的中间，左子树的结点的值位于根结点的值的左边，而右子树的结点的值位于根结点的值的右边。剩下的我们可以递归来实现
 * 地址：https://cuijiahua.com/blog/2017/11/basis_4.html
 */

public class Question4 {

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        System.out.println((reConstructBinaryTree(pre, in)).toString());
    }


    /**
     * 重建二叉树
     *
     * @param pre
     * @param in
     * @return 由前序遍历序列pre={1,2,4,7,3,5,6,8}可知根结点是1；
     * 则在中序遍历序列in={4,7,2,1,5,3,8,6}中找到1，便可知1所在位置的左侧是左子树,1所在位置的右侧是右子树；
     * 递归调用：将左子树和右子树分别看成一颗树，将其前序遍历序列、中序遍历序列分别传入到该方法中，便可得到左子树的根结点、右子树的根结点。此时需要用第一步得到的根结点连接它们；
     * 递归调用的终止条件：直到传入数组为空，说明已经没有节点，直接返回null。
     */
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        // 递归调用终止条件
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        // 通过前序遍历的性质得到二叉树的根节点
        TreeNode root = new TreeNode(pre[0]);
        // 在中序遍历的数组中找到根节点索引，索引前面的是左子树，索引后面的是右子树
        for (int i = 0; i < in.length; i++) {
            if (root.val == in[i]) {
                // 左右划分树
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, 1 + i), Arrays.copyOfRange(in, 0, i));
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
                //找到根结点位置便跳出循环
                break;
            }
        }
        //返回根结点
        return root;
    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
