/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        // Dummy node simplifies handling edge cases like removing the head node
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        
        while (curr.next != null) {
            if (curr.next.val == val) {
                // Skip the node with matching value
                curr.next = curr.next.next;
            } else {
                // Advance pointer if no node was removed
                curr = curr.next;
            }
        }
        
        return dummy.next;
    }
}
