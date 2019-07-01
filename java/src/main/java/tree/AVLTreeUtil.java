package tree;

/**
 * @Title: AVLTreeUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-06-29 15:35
 * @Description:
 * 平衡二叉树
 */

public class AVLTreeUtil {

    public static void main(String[] args) {
        int[] arr = { 10, 11, 7, 6, 8, 9 };
        //创建一个 AVLTree 对象
        AVLTree avlTree = new AVLTree();
        //添加结点
        for(int i=0; i < arr.length; i++) {
            avlTree.add(new AVLTreeNode(arr[i]));
        }

    }
}

// 创建 AVLTree
class AVLTree{

    private AVLTreeNode root;
    // 添加结点的方法
    public void add(AVLTreeNode node) {
        if (root == null) {
            // 如果 root 为空则直接让 root 指向 node
            root = node;
        } else {
            root.add(node);
        }
    }
}
class AVLTreeNode{
    private int value;
    private String name;
    private AVLTreeNode left;
    private AVLTreeNode right;

    public AVLTreeNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int no) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AVLTreeNode getLeft() {
        return left;
    }

    public void setLeft(AVLTreeNode left) {
        this.left = left;
    }

    public AVLTreeNode getRight() {
        return right;
    }

    public void setRight(AVLTreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "AVLTreeNode{" +
                "value=" + value +'}';
    }

    // 添加结点的方法
    // 递归的形式添加结点，注意需要满足二叉排序树的要求
    public void add(AVLTreeNode node) {
        if (node == null) {
            return;
        }
        // 判断传入的结点的值，和当前子树的根结点的值关系
        if (node.value < this.value) {
            // 如果当前结点左子结点为 null
            if (this.left == null) {
                this.left = node;
            }else {
                // 递归的向左子树添加
                this.left.add(node);
            }
        }else {
            // 添加的结点的值大于 当前结点的值
            if (this.right == null) {
                this.right = node;
            } else {
                // 递归的向右子树添加
                this.right.add(node);
            }
        }

        //当添加完一个结点后，如果: (右子树的高度-左子树的高度) > 1 , 左旋转
        if(rightHeigt() - leftHeigt() > 1) {
            //如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if(right != null && right.leftHeigt() > right.rightHeigt()) {
                //先对右子结点进行右旋转
                right.rightRotate();
                //然后在对当前结点进行左旋转
                leftRotate(); //左旋转..
            } else {
                //直接进行左旋转即可
                leftRotate();
            }
            return ; //必须要!!!
        }

        //当添加完一个结点后，如果 (左子树的高度 - 右子树的高度) > 1, 右旋转
        if(leftHeigt() - rightHeigt() > 1) {
            //如果它的左子树的右子树高度大于它的左子树的高度
            if(left != null && left.rightHeigt() > left.leftHeigt()) {
                //先对当前结点的左结点(左子树)->左旋转
                left.leftRotate(); //再对当前结点进行右旋转
                rightRotate();
            } else {
                //直接进行右旋转即可
                rightRotate();
            }
        }

    }

    //左旋转方法
    private void leftRotate(){
        // 创建一个新的节点，以当前根节点的值
        AVLTreeNode newNoew = new AVLTreeNode(value);
        // 把新的节点的左子树设置成当前节点的左子树
        newNoew.left = left;
        // 把新的节点的右子树设置成 当前节点的右子树的左子树
        newNoew.right = right.left;
        // 把当前节点的值替换成右节点的值
        value = right.value;
        // 把当前节点的右子树设置成 当前节点的右子树的右子树
        right = right.right;
        // 把当前节点的左子树设置成新的节点
        left = newNoew;
    }

    //右旋转
    private void rightRotate() {
        AVLTreeNode newNode = new AVLTreeNode(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    // 返回左子树的高度
    public int leftHeigt(){
        if (left == null){
            return 0;
        }
        return left.height();
    }

    // 返回左子树的高度
    public int rightHeigt(){
        if (right == null){
            return 0;
        }
        return right.height();
    }

    // 返回 以该结点为根结点的树的高度
    public int height(){
        return Math.max(left == null ? 0:left.height(),right ==null ?0:right.height())+1;
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

}
