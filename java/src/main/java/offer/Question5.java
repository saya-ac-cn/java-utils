package offer;
import java.util.Stack;
/**
 * @Title: Question5
 * @ProjectName spyder
 * @Description: TODO
 * @Author Administrator
 * @Date: 2019/12/31 0031 16:34
 * @Description:题目：用两个栈实现队列结构
 */

public class Question5 {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    /**
     * 进栈
     * @param node
     */
    public void push(int node) {
        stack1.push(node);
    }

    /**
     * 出栈
     * @return
     * 当stack2中不为空时，在stack2中的栈顶元素是最先进入队列的元素，可以弹出。
     * 如果stack2为空时，我们把stack1中的元素逐个弹出并压入stack2。
     * 由于先进入队列的元素被压倒stack1的栈底，经过弹出和压入之后就处于stack2的栈顶，
     * 有可以直接弹出。如果有新元素d插入，我们直接把它压入stack1即可
     */
    public int pop() {
        if (stack2.empty()){
            while (stack1.size() > 0){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

}
