import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        // Step 1: Pre-process matching parentheses pairs
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                int j = stack.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }

        // Step 2: Traverse using wormhole mechanics
        StringBuilder sb = new StringBuilder();
        int curr = 0;
        int direction = 1; // 1 for left-to-right, -1 for right-to-left

        while (curr < n) {
            char c = s.charAt(curr);
            if (c == '(' || c == ')') {
                // Jump to matching parenthesis and reverse traversal direction
                curr = pair[curr];
                direction = -direction;
            } else {
                // Collect character
                sb.append(c);
            }
            curr += direction;
        }

        return sb.toString();
    }
}