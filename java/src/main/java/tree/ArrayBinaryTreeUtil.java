package tree;

/**
 * @Title: ArrayBinaryTreeUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-06-20 20:34
 * @Description:
 * 实现二叉树的数组存储
 */

public class ArrayBinaryTreeUtil {

    public static void main(String [] args){
        int[] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder(0);
    }

}

class ArrayBinaryTree{

    private int [] arr;//存储数据节点的数组

    public ArrayBinaryTree(int [] arr){
        this.arr = arr;
    }

    public void preOrder(){
        preOrder(0);
    }

    //编写一个方法，完成顺序存储二叉树的前序遍历
    public void preOrder(int index){
        // 假如数组为空
        if(arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        // 输出当前这个元素
        System.out.println(arr[index]);
        // 向左递归遍历
        if((index * 2 + 1) < arr.length){
            preOrder(index * 2 + 1);
        }
        // 向右递归遍历
        if((index * 2 + 2) < arr.length){
            preOrder(index * 2 + 2);
        }
    }
}
