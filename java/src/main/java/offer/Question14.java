package offer;

/**
 * @Title: Question14
 * @ProjectName spyder
 * @Description: TODO
 * @Author Administrator
 * @Date: 2020/1/14 0014 09:23
 * @Description:题目：输入一个链表，输出该链表中倒数第k个结点。
 */

public class Question14 {

//    @Test
//    public void main(){
//        ListNode root = new ListNode(0);
//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);
//        ListNode node6 = new ListNode(6);
//        root.next = node1;
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;
//        ListNode node = FindKthToTail(root, 1);
//        System.out.println(node.val);
//    }

    public ListNode FindKthToTail(ListNode head, int k) {
        if(null == head || k == 0){
            return null;
        }
        //两个指针都指向头结点
        ListNode temp, result;
        temp = result = head;
        int i = 0;
        //temp指针先跑，并且记录节点数，当temp指针跑了k-1个节点后，result指针开始跑，
        //当temp指针跑到最后时，result所指指针就是倒数第k个节点
        for (; temp != null; i++) {
            if (i >= k) {
                result = result.next;
            }
            temp = temp.next;
        }
        //如果节点个数小于所求的倒数第k个节点，则返回空
        return i < k ? null : result;
    }
}
