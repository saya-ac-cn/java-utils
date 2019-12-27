package offer;

import java.util.Stack;

/**
 * @Title: Question3
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019/12/27 0027 16:20
 * @Description:
 * 题目：输入一个链表，返回一个反序的链表。
 * 思路：可以用栈的先进后出思想实现
 * 地址：https://cuijiahua.com/blog/2017/11/basis_3.html
 */

public class Question3 {

    public static void main(String[] args) {
        ListNode root = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        root.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        ListNode temp = root;
        Stack<ListNode> stack = new Stack<>();
        while (null != temp.next){
            stack.push(temp);
            temp = temp.next;
        }
        while (!stack.empty()){
            System.out.println((stack.pop()).val);
        }
    }

}
 class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}


