import java.util.HashMap;
import java.util.Map;

class LRUCache {
    
    // Doubly Linked List Node structure
    class Node {
        int key, value;
        Node prev, next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head, tail; // Dummy Head aur Tail pointers

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        
        // Dummy head aur tail initialize karte hain
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        
        // Key milti hai toh us node ko access hone ki waja se 'Most Recently Used' (Head ke paas) shift kar do
        Node node = map.get(key);
        remove(node);
        insertAtHead(node);
        
        return node.value;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            // Agar key pehle se hai, toh uski value update karo aur front mein le aao
            Node node = map.get(key);
            node.value = value;
            remove(node);
            insertAtHead(node);
        } else {
            // Agar capacity full hai, toh sabse purana (Tail ke paas wala) node remove kar do
            if (map.size() == capacity) {
                Node lruNode = tail.prev;
                map.remove(lruNode.key);
                remove(lruNode);
            }
            
            // Naya node banao, map mein daalo aur head ke paas insert karo
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            insertAtHead(newNode);
        }
    }
    
    // Helper function: Node ko DLL se nikaalna
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    // Helper function: Node ko Head ke turant baad (MRU side) attach karna
    private void insertAtHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}