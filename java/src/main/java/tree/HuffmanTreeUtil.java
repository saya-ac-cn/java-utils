package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Title: HuffmanTreeUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-06-23 22:05
 * @Description:霍夫曼树
 */

public class HuffmanTreeUtil {

    public static void main(String[] args) {
        int arr[] = { 13, 7, 8, 3, 29, 6, 1 };
        HuffmanTreeNode root = createHuffmanTree(arr);
        //测试一把
        preOrder(root);
    }


    //编写一个前序遍历的方法
    public static void preOrder(HuffmanTreeNode root) {
        if(root != null) { root.preOrder();
        }else{
            System.out.println("是空树，不能遍历~~");
        }
    }


    // 创建赫夫曼树的方法
    /**
     * @param arr 需要创建成哈夫曼树的数组
     * @return 创建好后的赫夫曼树的 root 结点
     */
    public static HuffmanTreeNode createHuffmanTree(int[] arr) {
        // 第一步为了操作方便
        // 1. 遍历 arr 数组
        // 2. 将 arr 的每个元素构成成一个 Node
        // 3. 将 Node 放入到 ArrayList 中
        List<HuffmanTreeNode> nodes = new ArrayList<>();
        for (int value:arr){
            nodes.add(new HuffmanTreeNode(value));
        }
        //我们处理的过程是一个循环的过程
        //最后数组里面只会剩一个元素
        while (nodes.size() > 1){
            //排序 从小到大
            Collections.sort(nodes);
            System.out.println("nodes =" + nodes);

            // 取出根节点权值最小的两个节点构成一个二叉树
            //(1) 取出权值最小的结点(二叉树)
            HuffmanTreeNode leftNode = nodes.get(0);
            //(2) 取出权值第二小的结点(二叉树)
            HuffmanTreeNode rightNode = nodes.get(1);

            //(3)构建一颗新的二叉树
            HuffmanTreeNode parent = new  HuffmanTreeNode(leftNode.getValue() + rightNode.getValue());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);

            //(4)从 ArrayList 删除处理过的二叉树
            nodes.subList(0,2).clear();
            //nodes.remove(leftNode);
            //nodes.remove(rightNode);
            //(5)将 parent 加入到 nodes
            nodes.add(parent);
        }
        //返回哈夫曼树的 root 结点
        return nodes.get(0);
    }
}

// 创建结点类
// 为了让 Node 对象持续排序 Collections 集合排序
// 让 Node 实现 Comparable 接口
class HuffmanTreeNode implements Comparable<HuffmanTreeNode> {

    private int value; // 结点权值
    private HuffmanTreeNode left; // 指向左子结点
    private HuffmanTreeNode right; // 指向右子结点

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public HuffmanTreeNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanTreeNode left) {
        this.left = left;
    }

    public HuffmanTreeNode getRight() {
        return right;
    }

    public void setRight(HuffmanTreeNode right) {
        this.right = right;
    }

    //写一个前序遍历
    //前序遍历
    public void preOrder(){
        // 先输出父节点信息
        System.out.println(this);
        // 前序递归遍历左子树
        if(this.left != null){
            this.left.preOrder();
        }
        // 前序递归遍历右子树
        if (this.right != null){
            this.right.preOrder();
        }
    }

    public HuffmanTreeNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node [value=" + value + "]"; }

    @Override
    public int compareTo(HuffmanTreeNode o) {
        return this.value - o.getValue();
    }
}

