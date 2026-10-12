import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCount = new int[31];
        
        // Step 1: Count the occurrences of each set bit across all numbers
        for (int num : nums) {
            for (int i = 0; i <= 30; i++) {
                if ((num & (1 << i)) != 0) {
                    bitCount[i]++;
                }
            }
        }
        
        long mod = 1_000_000_007L;
        long totalSum = 0;
        
        // Step 2: Greedily construct the k largest numbers
        for (int i = 0; i < k; i++) {
            long currentNum = 0;
            for (int bit = 30; bit >= 0; bit--) {
                if (bitCount[bit] > 0) {
                    currentNum |= (1L << bit);
                    bitCount[bit]--; // Use this bit for the current number
                }
            }
            // Add its square to the total sum modulo 10^9 + 7
            totalSum = (totalSum + (currentNum * currentNum) % mod) % mod;
        }
        
        return (int) totalSum;
    }
}