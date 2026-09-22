class MyLinkedList {

    // Node structure
    class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    private Node dummy; // Dummy head node pointer
    private int size;   // Number of actual elements

    public MyLinkedList() {
        dummy = new Node(-1); // Initial dummy node
        size = 0;
    }

    public int get(int index) {
        // Index out of bounds check
        if (index < 0 || index >= size) {
            return -1;
        }

        Node curr = dummy.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        // Invalid index condition
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }

        // Target index se ek pehle wale node tak traverse karte hain
        Node prev = dummy;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        // Naya node attach karte hain
        Node newNode = new Node(val);
        newNode.next = prev.next;
        prev.next = newNode;

        size++;
    }

    public void deleteAtIndex(int index) {
        // Index out of bounds check
        if (index < 0 || index >= size) {
            return;
        }

        // Target index se ek pehle wale node tak traverse karte hain
        Node prev = dummy;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        // Target node ko skip karke delete karte hain
        prev.next = prev.next.next;

        size--;
    }
}