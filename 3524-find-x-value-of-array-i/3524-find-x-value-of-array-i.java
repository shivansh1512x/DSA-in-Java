class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k];
        
        for (int num : nums) {
            long[] nextDp = new long[k];
            int val = (int) (num % k);
            
            // Single-element subarray starting and ending at current index
            nextDp[val]++;
            
            // Extend existing subarrays ending at the previous index
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    nextDp[(r * val) % k] += dp[r];
                }
            }
            
            dp = nextDp;
            
            // Accumulate counts of all subarrays ending at the current index
            for (int x = 0; x < k; x++) {
                result[x] += dp[x];
            }
        }
        
        return result;
    }
}