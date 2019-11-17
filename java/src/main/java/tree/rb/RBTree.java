package tree.rb;

/**10, 40, 30, 60, 90, 70, 20, 50, 80
 * @Title: RBTree
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-11-16 11:02
 * @Description:
 * 红黑树实现
 * 禁止在自动调整代码格式
 * 演示：https://www.cs.usfca.edu/~galles/visualization/RedBlack.html
 * 参考：https://www.cnblogs.com/skywang12345/p/3624343.html
 *      https://blog.csdn.net/eson_15/article/details/51144079
 */

public class RBTree<T extends Comparable<T>> {

    /**
     * 根节点
     */
    private RBNode<T> root;

    /**
     * 红黑树标志
     */
    private static final boolean RED = false;
    private static final boolean BLACK = false;

    public RBTree() {
        root = null;
    }

    /**
     * 获得父节点
     *
     * @param node
     * @return
     */
    public RBNode<T> getParent(RBNode<T> node) {
        return node != null ? node.parent : null;
    }

    /**
     * 设置父节点
     *
     * @param node
     * @param parent
     */
    public void setParent(RBNode<T> node, RBNode<T> parent) {
        if (node != null) {
            node.parent = parent;
        }
    }

    /**
     * 获得节点的颜色
     *
     * @param node
     * @return
     */
    public boolean colorOf(RBNode<T> node) {
        return node != null ? node.color : BLACK;
    }

    /**
     * 判断节点的颜色
     *
     * @param node
     * @return
     */
    public boolean isRed(RBNode<T> node) {
        return (node != null) && (node.color == RED) ? true : false;
    }

    public boolean isBlack(RBNode<T> node) {
        return !isRed(node);
    }

    /**
     * 设置节点的颜色
     *
     * @param node
     */
    public void setRed(RBNode<T> node) {
        if (node != null) {
            node.color = RED;
        }
    }

    public void setBlack(RBNode<T> node) {
        if (node != null) {
            node.color = BLACK;
        }
    }

    public void setColor(RBNode<T> node, boolean color) {
        if (node != null) {
            node.color = color;
        }
    }

    /**************** 查找红黑树中键值为key的节点 ***************/
    public RBNode<T> search(T key) {
        return search(root,key);
    }


    private RBNode<T> search(RBNode<T> x, T key) {
        while (null != x){
            int cmp = key.compareTo(x.key);
            if (cmp < 0){
                x = x.left;
            }else if (cmp > 0){
                x = x.right;
            }else {
                return x;
            }
        }
        return x;
    }

    /*************对红黑树节点x进行左旋操作 ******************/
    /**
     * 左旋示意图：对节点y进行右旋
     *     p                       p
     *    /                       /
     *   x                       y
     *  / \                     / \
     * lx  y      ----->       x  ry
     *    / \                 / \
     *   ly ry               lx ly
     * 左旋做了三件事：
     * 1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
     * 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
     * 3. 将y的左子节点设为x，将x的父节点设为y
     *
     * @param x
     */
    private void leftRotate(RBNode<T> x) {
        /// 1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
        /// x 和 ly间设置
        RBNode<T> y = x.right;
        x.right = y.left;
        if (null != y.left) {
            y.left.parent = x;
        }
        /// 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
        /// p 和 y间设置
        y.parent = x.parent;
        if (null == x.parent) {
            ///如果x的父节点为空，则将y设为根节点
            this.root = y;
        } else {
            if (x == x.parent.left) {
                /// 如果x是左子节点
                /// 则将y设为左子节点
                x.parent.left = y;
            } else {
                /// 否则将y设为右子节点
                x.parent.right = y;
            }
        }
        /// 3. 将y的左子节点设为x，将x的父节点设为y
        /// x 和 y间设置
        y.left = x;
        x.parent = y;
    }

    /*************对红黑树节点y进行右旋操作 ******************/
    /**
     * 右旋示意图：对节点y进行右旋
     *        p                   p
     *       /                   /
     *      y                   x
     *     / \                 / \
     *    x  ry   ----->      lx  y
     *   / \                     / \
     * lx  rx                   rx ry
     * 右旋做了三件事：
     * 1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
     * 2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
     * 3. 将x的右子节点设为y，将y的父节点设为x
     *
     * @param y
     */
    private void rightRotate(RBNode<T> y) {
        /// 1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
        /// y he rx间设置
        RBNode<T> x = y.left;
        y.left = x.right;
        /// 2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
        /// p he x间设置
        x.parent = y.parent;
        if (null == y.parent) {
            /// 如果y的父节点为空，则将x设为根节点
            this.root = x;
        } else {
            if (y == y.parent.right) {
                /// 如果y是右子节点
                /// 则将x设为右子节点
                y.parent.right = x;
            } else {
                /// 否则将x设为右子节点
                y.parent.left = x;
            }
        }
        /// 3. 将x的右子节点设为y，将y的父节点设为x
        /// x 和 y间设置
        x.right = y;
        y.parent = x;
    }


    /*********************** 向红黑树中插入节点 **********************/
    public void insert(T key) {
        RBNode<T> node = new RBNode<T>(key, RED, null, null, null);
        if (node != null) {
            insert(node);
        }
    }


    /**
     * 将节点插入到红黑树中，这个过程与二叉搜索树是一样的
     *
     * @param node
     */
    private void insert(RBNode<T> node) {
        // 最后node的父节点
        RBNode<T> current = null;
        // 临时节点，用于向下搜索
        RBNode<T> x = this.root;
        // 1、找到待插入的位置
        while (null != x) {
            current = x;
            if (node.key.compareTo(x.key) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        // 找到了位置，将当前current作为node的父节点
        node.parent = current;
        // 2、判断node是插在左子节点还是右子节点
        if (null == current) {
            this.root = node;
        } else {
            if (node.key.compareTo(current.key) < 0) {
                current.left = node;
            } else {
                current.right = node;
            }
        }

    }

    /**
     * 重新为红黑树着色
     *1 叔叔结点也为红色结点
     *     将叔叔和父亲结点改为黑色，爷爷结点改为红色，未完，然后又将爷爷结点当作插入结点看待，一直进行上
     *     面的操作，直到当前结点为根结点，然后将根结点变成黑色
     *2 叔叔结点为黑色结点的情况下：
     *    2.1 （父亲结点为左孩子，插入结点也为左孩子）||（父亲结点为右孩子，插入结点也为右孩子）
     *         将父亲结点和爷爷结点的颜色互换（爷爷红，父黑），然后针对爷爷结点进行一次右旋
     *    2.2 （父亲结点为左孩子，插入结点为右孩子）||（父亲结点为右孩子，插入结点为左孩子）
     *         针对父结点进行左旋，此时左旋后的情况必定是2.1的情况，然后按照2.1的情况处理
     * @param node
     */
    private void insertFixUp(RBNode<T> node) {
        // 定义父节点和祖父节点
        RBNode<T> parent, grandParent;
        /// 需要休整的条件，父节点存在且颜色是红色
        while (((parent = getParent(node)) != null) && isRed(parent)) {
            // 获得祖父节点
            grandParent = getParent(parent);
            if (parent == grandParent.left){
                //若父节点是祖父节点的左子节点
                //拿到叔叔节点
                RBNode<T> uncle = grandParent.right;
                //叔叔节点也是红色，对应上面1
                if (null != uncle && isRed(uncle)){
                    //将叔叔和父亲结点改为黑色
                    setBlack(uncle);
                    setBlack(parent);
                    //爷爷结点改为红色
                    setRed(grandParent);
                    //将位置放到祖父节点处,将爷爷结点当作插入结点看待
                    node = grandParent;
                    continue;
                }
                //叔叔节点是黑色，且当前节点是右子节点，对应上面2.2，左右
                if (node == parent.right){
                    //针对父结点进行左旋
                    leftRotate(parent);
                    //然后将父节点和自己调换一下，为下面右旋做准备
                    RBNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }
                //叔叔节点是黑色,父亲结点为左孩子，插入结点也为左孩子,左左
                setBlack(parent);
                setRed(grandParent);
                rightRotate(grandParent);
            }else {
                //若父节点是祖父节点的右子节点
                //拿到叔叔节点
                RBNode<T> uncle = grandParent.left;
                //叔叔节点也是红色，对应上面1
                if (null != uncle && isRed(uncle)){
                    //将叔叔和父亲结点改为黑色
                    setBlack(uncle);
                    setBlack(parent);
                    //爷爷结点改为红色
                    setRed(grandParent);
                    //将位置放到祖父节点处,将爷爷结点当作插入结点看待
                    node = grandParent;
                    continue;
                }
                //叔叔节点是黑色，且当前节点是左子节点，对应上面2.2，右左
                if (node == parent.left){
                    //针对父结点进行左旋
                    rightRotate(parent);
                    //然后将父节点和自己调换一下，为下面右旋做准备
                    RBNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }
                //叔叔节点是黑色,父亲结点为右孩子，插入结点也为右孩子,右右
                setBlack(parent);
                setRed(grandParent);
                leftRotate(grandParent);
            }
        }
        // 将根节点设为黑色
        setBlack(this.root);
    }

    /*********************** 删除红黑树中的节点 **********************/
    public void remove(T key) {
        RBNode<T> node;
        if ((node = search(key)) != null){
            remove(node);
        }
    }

    public void remove(RBNode<T> node){
        RBNode<T> child,parent,replace;
        boolean color;
        // 被删除节点的"左右孩子都不为空"的情况
        if ((node.left != null)&&(node.right != null)){
            // 被删节点的后继节点。(称为"取代节点")
            // 用它来取代"被删节点"的位置，然后再将"被删节点"去掉。
            // 获取后继节点
            replace = node.right;
            while (null != replace.left){
                replace = replace.left;
            }
            // "node节点"不是根节点(只有根节点不存在父节点)
            if (getParent(node) != null){
                // 要删除的节点不是根节点
                if (node == getParent(node).left){
                    getParent(node).left = replace;
                }else {
                    getParent(node).right = replace;
                }
            }else {
                // "node节点"是根节点，更新根节点。
                this.root = replace;
            }
            // child是"取代节点"的右孩子，也是需要"调整的节点"
            child = replace.right;
            // "取代节点"肯定不存在左孩子！因为它是一个后继节点
            parent = getParent(replace);
            // 保存"取代节点"的颜色
            color = colorOf(replace);
            // "被删除节点"是"它的后继节点的父节点"
            if (parent == node){
                parent = replace;
            }else {
                if (null != child){
                    setParent(child,parent);
                }
                parent.left = child;
                replace.right = node.right;
                setParent(node.right,replace);
            }
            replace.parent = node.parent;
            // 保持原来位置的颜色
            replace.color = node.color;
            replace.left = node.left;
            node.left.parent = replace;
            //如果移走的后继节点颜色是黑色，重新修整红黑树
            if (BLACK == color){
                removeFixUp(child, parent);
            }
            node = null;
            return;
        }
        if (null == node.left){
            child = node.left;
        }else {
            child = node.right;
        }
        parent = node.parent;
        // 保存"取代节点"的颜色
        color = colorOf(node);
        // "node节点"不是根节点
        if (null != parent){
            if (parent.left == node){
                parent.left = child;
            }else {
                parent.right = child;
            }
        }else {
            this.root = child;
        }
    }

    /**
     * node表示待修正的节点，即后继节点的子节点（因为后继节点被挪到删除节点的位置去了）
     * @param node
     * @param parent
     */
    private void removeFixUp(RBNode<T> node, RBNode<T> parent) {
        RBNode<T> other;
        while ((null == node || isBlack(node))&&(node != this.root)){
            //node是左子节点
            if (parent.left == node){
                // node的兄弟节点
                other = parent.right;
                // node的兄弟节点other是红色的
                if (isRed(other)){
                    setBlack(other);
                    setRed(parent);
                    leftRotate(parent);
                    other = parent.right;
                }
                //node的兄弟节点other是黑色的，且other的两个子节点也都是黑色的
                if ((null == other.left || isBlack(other.left))&&(null == other.right || isBlack(other.right))){
                    setRed(other);
                    node = parent;
                    parent = getParent(node);
                }else {
                    //node的兄弟节点other是黑色的，且other的左子节点是红色，右子节点是黑色
                    if (null == other.right || isBlack(other.right)){
                        setBlack(other.left);
                        setRed(other);
                        rightRotate(other);
                        other = parent.right;
                    }
                    //node的兄弟节点other是黑色的，且other的右子节点是红色，左子节点任意颜色
                    setColor(other,colorOf(parent));
                    setBlack(parent);
                    setBlack(other.right);
                    leftRotate(parent);
                    node = this.root;
                    break;
                }
            }else {
                other = parent.left;
                // node的兄弟other是红色的
                if (isRed(other)){
                    setBlack(other);
                    setRed(parent);
                    rightRotate(parent);
                    other = parent.left;
                }
                if ((null == other.left || isBlack(other.left))&&(null == other.right||isBlack(other.right))){
                    //node的兄弟other是黑色，且other的俩个子节点都是黑色的
                    setRed(other);
                    node = parent;
                    parent = getParent(node);
                }else {
                    // node的兄弟other是黑色的，并且other的左子节点是红色，右子节点为黑色
                    if (null == other.left || isBlack(other.left)){
                        setBlack(other.right);
                        setRed(other);
                        leftRotate(other);
                        other = parent.left;
                    }
                    // node的兄弟other是黑色的；并且other的左子节点是红色的，右子节点任意颜色
                    setColor(other,colorOf(parent));
                    setBlack(parent);
                    setBlack(other.left);
                    rightRotate(parent);
                    node = this.root;
                    break;
                }
            }
            if (node != null){
                setBlack(node);
            }
        }
    }

}
