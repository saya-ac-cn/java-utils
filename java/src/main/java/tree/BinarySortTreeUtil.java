package tree;

/**
 * @Title: BinarySortTreeUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-06-26 20:42
 * @Description:
 * 二叉排序树
 */

public class BinarySortTreeUtil {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        //循环的添加结点到二叉排序树
        BinarySortTree binarySortTree = new BinarySortTree();
        for(int i = 0; i< arr.length; i++) {
            binarySortTree.add(new BinarySortTreeNode(arr[i]));
        }
        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树~");
        binarySortTree.infixOrder(); // 1, 3, 5, 7, 9, 10, 12

    }
}

//创建二叉排序树
class BinarySortTree {
    private BinarySortTreeNode root;

    public BinarySortTreeNode getRoot() {
        return root;
    }

    /**
     * @描述
     /*1. 返回的 以 node 为根结点的二叉排序树的最小结点的值
     *2. 删除 node 为根结点的二叉排序树的最小结点
     * @参数 传入的结点(当做二叉排序树的根结点)
     * @返回值  返回的 以 node 为根结点的二叉排序树的最小结点的值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-27
     * @修改人和其它信息
     */
    public int findRightTreeMin(BinarySortTreeNode node){
        BinarySortTreeNode target = node;
        //循环的查找左子节点，就会找到最小值
        while (target.getLeft() != null){
            target = target.getLeft();
        }
        //这时 target 就指向了最小结点 //删除最小结点 delNode(target.value);
        return target.getValue();
    }

    //删除结点
    public void delNode(int value) {
        if (root == null){
            return;
        }else {
            //1.需求先去找到要删除的结点 targetNode
            BinarySortTreeNode targetNode = search(value);
            //如果没有找到要删除的节点
            if (targetNode == null){
                return;
            }
            //如果我们发现当前这颗二叉排序树只有一个结点
            if (root.getLeft() == null && root.getRight() == null){
                root = null;
                return;
            }
            //去找到 targetNode 的父结点
            BinarySortTreeNode parent = searchParent(value);
            //如果要删除的节点是叶子节点
            if (targetNode.getLeft() == null && targetNode.getRight() == null){
                //判断 targetNode 是父结点的左子结点，还是右子结点
                if (parent.getLeft() != null && parent.getLeft().getValue() == value){
                    //是左子节点
                    parent.setLeft(null);
                }else if (parent.getRight() != null && parent.getRight().getValue() == value){
                    //是右子节点
                    parent.setRight(null);
                }
            }else if (targetNode.getLeft() != null && targetNode.getRight() != null){
                //删除有两颗子树的节点
                int mainVal = findRightTreeMin(targetNode.getRight());
                targetNode.setValue(mainVal);
            }else {
                // 删除只有一颗子树的结点
                //如果要删除的结点有左子结点
                if (targetNode.getLeft() != null){
                    if (parent != null){
                        //如果 targetNode 是 parent 的左子结点
                        if (parent.getLeft().getValue() == value){
                            parent.setLeft(targetNode.getLeft());
                        }else {
                            // targetNode 是 parent 的右子结点
                            parent.setRight(targetNode.getLeft());
                        }
                    }else {
                        // 删根节点
                        root = targetNode.getLeft();
                    }
                }else {
                    // 如果要删除的结点有右子结点
                    if (parent != null){
                        //如果 targetNode 是 parent 的左子结点
                        if (parent.getLeft().getValue() == value){
                            parent.setLeft(targetNode.getRight());
                        }else {
                            //如果 targetNode 是 parent 的右子结点
                            parent.setRight(targetNode.getRight());
                        }
                    }else {
                        // 删根节点
                        root = targetNode.getRight();
                    }
                }
            }
        }
    }

    //查找要删除的结点
    public BinarySortTreeNode search(int value) {
        if(root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父结点
    public BinarySortTreeNode searchParent(int value) {
        if(root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //添加结点的方法
    public void add(BinarySortTreeNode node) {
        if(root == null) {
            //如果 root 为空则直接让 root 指向 node
            root = node;
        } else {
            root.add(node);
        }
    }


    //中序遍历
    public void infixOrder() {
        if(root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }


}


class BinarySortTreeNode{
    private int value;
    private BinarySortTreeNode left;
    private BinarySortTreeNode right;

    public BinarySortTreeNode() {
    }

    public BinarySortTreeNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinarySortTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinarySortTreeNode left) {
        this.left = left;
    }

    public BinarySortTreeNode getRight() {
        return right;
    }

    public void setRight(BinarySortTreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node [value=" + value + "]";
    }

    /**
     * @描述 查找要删除的结点
     * @参数 希望删除的结点的值
     * @返回值 如果找到返回该结点，否则返回 null
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-26
     * @修改人和其它信息
     */
    public BinarySortTreeNode search(int value) {
        if (value == this.value){
            //找到就是该结点
            return this;
        }else if(value < this.value){
            //如果查找的值小于当前结点，向左子树递归查找
            //如果左子结点为空
            if (this.left == null){
                return null;
            }
            return this.left.search(value);
        }else {
            //如果查找的值不小于当前结点，向右子树递归查找
            if (this.right == null){
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除结点的父结点
     * @param value 要找到的结点的值
     * @return 返回的是要删除的结点的父结点，如果没有就返回 null
     */
    public BinarySortTreeNode searchParent(int value){
        //如果当前结点就是要删除的结点的父结点，就返回
        if ((this.left != null && this.left.value==value)||(this.right != null && this.right.value == value)){
            return this;
        }else {
            //如果查找的值小于当前结点的值, 并且当前结点的左子结点不为空
            if (value < this.value && this.left != null){
                //向左子树递归查找
                return this.left.searchParent(value);
            }else if (value >= this.value && this.right != null){
                //向右子树递归查找
                return this.right.searchParent(value);
            }else {
                return null;
            }
        }
    }

    //添加结点的方法
    //递归的形式添加结点，注意需要满足二叉排序树的要求
    public void add(BinarySortTreeNode node){
        if (node == null){
            return;
        }

        //判断传入的节点的值，和当前子树的根之间的大小关系
        if (node.getValue() < this.value){
            //如果当前结点左子结点为 null
            if (this.left == null){
                this.left = node;
            }else {
                //递归的向左子树添加
                this.left.add(node);
            }
        }else {
            //添加的结点的值大于 当前结点的值
            if (this.right == null){
                this.right = node;
            }else {
                //递归的向右子树添加
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if(this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null) {
            this.right.infixOrder();
        }
    }


}
