class Solution {
    public Node flatten(Node head) {
        if (head == null) return null;
        
        Node curr = head;
        // Stack ka istemaal agle nodes ko save karne ke liye
        java.util.Stack<Node> stack = new java.util.Stack<>();
        
        while (curr != null) {
            // Agar current node ka child hai
            if (curr.child != null) {
                // Agar curr.next exist karta hai, toh use stack mein daal do (baad ke liye)
                if (curr.next != null) {
                    stack.push(curr.next);
                }
                
                // Child ko main list mein attach karo
                curr.next = curr.child;
                curr.child.prev = curr;
                
                // Child pointer ko null kar do (har level par mandatory hai)
                curr.child = null;
            }
            
            // Agar hum current branch ke end tak pahunch gaye hain aur stack mein bache hue nodes hain
            if (curr.next == null && !stack.isEmpty()) {
                // Stack se agla node nikaalo aur attach kar do
                Node nextNode = stack.pop();
                curr.next = nextNode;
                nextNode.prev = curr;
            }
            
            // Aage bado
            curr = curr.next;
        }
        
        return head;
    }
}