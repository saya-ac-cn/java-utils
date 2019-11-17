package tree.rb;

/**
 * @Title: RBNode
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-11-16 10:52
 * @Description: 红黑树节点类
 */

public class RBNode<T extends Comparable<T>> {

    /**
     * 颜色
     */
    public boolean color;

    /**
     * 关键字(键值)
     */
    public T key;

    /**
     * 左子节点
     */
    public RBNode<T> left;

    /**
     * 右子节点
     */
    public RBNode<T> right;

    /**
     * 父节点
     */
    public RBNode<T> parent;

    public RBNode(T key, boolean color, RBNode<T> parent, RBNode<T> left, RBNode<T> right) {
        this.key = key;
        this.color = color;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public T getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "" + key + (this.color == false ? "R" : "B");
    }


}
