package tree;

/**
 * @Title: ThreadedBinaryTreeUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-06-21 22:34
 * @Description:
 * 终须线索化二叉树
 */

public class ThreadedBinaryTreeUtil {

    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        TreeNode root = new TreeNode(1, "tom");
        TreeNode node2 = new TreeNode(3, "jack");
        TreeNode node3 = new TreeNode(6, "smith");
        TreeNode node4 = new TreeNode(8, "mary");
        TreeNode node5 = new TreeNode(10, "king");
        TreeNode node6 = new TreeNode(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试: 以 10 号节点测试
        TreeNode leftNode = node5.getLeft();
        TreeNode rightNode = node5.getRight();
        System.out.println("10 号结点的前驱结点是 =" +leftNode);//3
        System.out.println("10 号结点的后继结点是=" + rightNode); //1

    }
}

//定义 ThreadedBinaryTree 实现了线索化功能的二叉树
class ThreadedBinaryTree{
    private TreeNode root;

    //为了实现线索化，需要创建要给指向当前结点的前驱结点的指针
    //在递归进行线索化时，pre 总是保留前一个结点
    private TreeNode pre = null;

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    //重载 threadedNodes 方法
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    /**
     * @描述 对二叉树进行中序线索化
     * @参数 当前需要线索化的结点
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-21
     * @修改人和其它信息
     */
    public void threadedNodes(TreeNode node){
        //如果 node==null, 不能线索化
        if (node == null){
            return;
        }
        //1、先线索化左子树
        threadedNodes(node.getLeft());
        //2、线索化当前节点【有难度】

        //处理当前结点的前驱结点
        //以 8 结点来理解
        //8 结点的.left = null , 8 结点的.leftType = 1
        if (node.getLeft() == null){
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针的类型,指向前驱结点
            node.setLeftType(1);
        }
        //处理后继节点
        if (pre != null && pre.getRight() == null){
            //让前驱结点的右指针指向当前结点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        //!!! 每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;

        //3、线索化右子树
        threadedNodes(node.getRight());
    }

}

// 创建节点
class TreeNode{

    private int no;
    private String name;
    private TreeNode left; //默认 null
    private TreeNode right; //默认 null

    //说明
    //1. 如果 leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点
    //2. 如果 rightType == 0 表示指向是右子树, 如果 1 表示指向后继结点
    private int leftType;
    private int rightType;

    public TreeNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "TreeNode [no=" + no + ", name=" + name + "]"; }

}