package tree;

/**
 * @Title: BinaryTreeUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-06-19 21:34
 * @Description:
 * 二叉树
 */

public class BinaryTreeUtil {

    public static void main(String[] args){
        BinaryTree tree = new BinaryTree();
        BinaryTreeNode root = new BinaryTreeNode(1,"A");
        BinaryTreeNode node2 = new BinaryTreeNode(2,"B");
        BinaryTreeNode node3 = new BinaryTreeNode(3,"C");
        BinaryTreeNode node4 = new BinaryTreeNode(4,"D");

        //手动定义二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        tree.setRoot(root);

        System.out.println("前序遍历");
        tree.preOrder();

        System.out.println("中序遍历");
        tree.infixOrder();

        System.out.println("后序遍历");
        tree.postOrder();
    }

}

// 定义二叉树
class BinaryTree{
    private BinaryTreeNode root;

    public void setRoot(BinaryTreeNode node){
        this.root = node;
    }

    // 前序遍历
    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 中序遍历
    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 后序遍历
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * @描述 前序查找node，没有时返回null
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-19
     * @修改人和其它信息
     */
    public BinaryTreeNode preOrderSearch(int no){
        if (root != null){
            return root.preOrderSearch(no);
        }else{
            return null;
        }
    }

    /**
     * @描述 中序查找node，没有时返回null
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-19
     * @修改人和其它信息
     */
    public BinaryTreeNode infixOrderSearch(int no){
        if (root != null){
            return root.infixOrderSearch(no);
        }else{
            return null;
        }
    }

    /**
     * @描述 后序查找node，没有时返回null
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-19
     * @修改人和其它信息
     */
    public BinaryTreeNode postOrderSearch(int no){
        if (root != null){
            return root.postOrderSearch(no);
        }else{
            return null;
        }
    }

}

class BinaryTreeNode{
    private int no;
    private String name;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(int no, String name) {
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

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinaryTreeNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

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

    //中序遍历
    public void infixOrder(){
        // 前序递归遍历左子树
        if(this.left != null){
            this.left.infixOrder();
        }
        // 输出父节点信息
        System.out.println(this);
        // 前序递归遍历右子树
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder(){
        // 前序递归遍历左子树
        if(this.left != null){
            this.left.postOrder();
        }
        // 前序递归遍历右子树
        if (this.right != null){
            this.right.postOrder();
        }
        // 输出父节点信息
        System.out.println(this);
    }

    /**
     * @描述 前序查找node，没有时返回null
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-19
     * @修改人和其它信息
     */
    public BinaryTreeNode preOrderSearch(int no){
        // 比较当前节点是不是
        if (this.no == no){
            return this;
        }
        // 判断当前节点的左子树是否为空，如果不文科，则递归前序查找
        // 如果左递归前序查找，找到节点，则返回
        BinaryTreeNode resNode = null;
        if(this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null){
            // 在左子树中找到
            return resNode;
        }
        // 右递归前序查找，找到节点，则返回，否则继续判断
        // 当前节点的右子节点是否为空，如果不为空，则继续右递归查找
        if (this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * @描述 中序查找node，没有时返回null
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-19
     * @修改人和其它信息
     */
    public BinaryTreeNode infixOrderSearch(int no){
        // 判断当前节点的左子树是否为空，如果不文科，则递归中序查找
        // 如果左递归前序查找，找到节点，则返回
        BinaryTreeNode resNode = null;
        if(this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null){
            // 在左子树中找到
            return resNode;
        }
        // 比较当前节点是不是
        if (this.no == no){
            return this;
        }
        // 右递归中序查找，找到节点，则返回，否则继续判断
        // 当前节点的右子节点是否为空，如果不为空，则继续右递归查找
        if (this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * @描述 后序查找node，没有时返回null
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-19
     * @修改人和其它信息
     */
    public BinaryTreeNode postOrderSearch(int no){
        // 判断当前节点的左子树是否为空，如果不文科，则递归后序查找
        // 如果左递归前序查找，找到节点，则返回
        BinaryTreeNode resNode = null;
        if(this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null){
            // 在左子树中找到
            return resNode;
        }
        // 右递归后序查找，找到节点，则返回，否则继续判断
        // 当前节点的右子节点是否为空，如果不为空，则继续右递归查找
        if (this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null){
            // 在右子树中找到
            return resNode;
        }
        // 比较当前节点是不是
        if (this.no == no){
            return this;
        }

        return resNode;
    }

}

