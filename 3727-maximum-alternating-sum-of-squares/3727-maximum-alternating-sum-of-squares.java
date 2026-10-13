import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        
        // Step 1: Calculate squares of all elements
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }
        
        // Step 2: Sort squares in ascending order
        Arrays.sort(squares);
        
        int k = n / 2;
        long score = 0;
        
        // Step 3: Subtract the k smallest squares (odd indices)
        for (int i = 0; i < k; i++) {
            score -= squares[i];
        }
        
        // Step 4: Add the remaining largest squares (even indices)
        for (int i = k; i < n; i++) {
            score += squares[i];
        }
        
        return score;
    }
}