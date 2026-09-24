import java.util.Stack;

class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack<>();
        
        for (String op : operations) {
            if (op.equals("+")) {
                // Last score nikaalo
                int top = stack.pop();
                // Second last score dekho
                int newScore = top + stack.peek();
                // Waapas dono ko stack mein daalo
                stack.push(top);
                stack.push(newScore);
            } else if (op.equals("D")) {
                // Last score ka double karke daalo
                stack.push(2 * stack.peek());
            } else if (op.equals("C")) {
                // Last score ko remove (invalidate) kar do
                stack.pop();
            } else {
                // String integer hai, parse karke push karo
                stack.push(Integer.parseInt(op));
            }
        }
        
        // Final total score calculate karo
        int totalSum = 0;
        for (int score : stack) {
            totalSum += score;
        }
        
        return totalSum;
    }
}