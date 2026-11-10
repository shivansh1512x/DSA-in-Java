class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[i] stores the minimum cost to reach step i
        int[] dp = new int[n + 1];
        
        // Initialize DP array with a large value to find the minimum
        // Use Integer.MAX_VALUE / 2 to prevent arithmetic overflow
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE / 2;
        }
        
        dp[0] = 0; // Base case: starting at step 0 costs 0
        
        for (int i = 1; i <= n; i++) {
            // Check up to 3 steps backward (i-1, i-2, i-3)
            for (int jump = 1; jump <= Math.min(i, 3); jump++) {
                int prevStep = i - jump;
                // costs is 1-indexed in problem, so step i maps to costs[i - 1]
                int jumpCost = costs[i - 1] + (jump * jump);
                
                dp[i] = Math.min(dp[i], dp[prevStep] + jumpCost);
            }
        }
        
        return dp[n];
    }
}
