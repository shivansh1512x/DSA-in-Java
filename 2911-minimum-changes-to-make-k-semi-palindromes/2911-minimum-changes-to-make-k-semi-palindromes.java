import java.util.Arrays;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        // Use a large value to represent infinity, avoiding integer overflow during addition
        int INF = 1000000; 
        
        // cost[i][j] stores the minimum changes to make s[i...j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int[] row : cost) {
            Arrays.fill(row, INF);
        }

        // Step 1: Precompute costs for all substrings s[i...j]
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int length = j - i + 1;
                
                // Try every proper divisor d of length
                for (int d = 1; d < length; d++) {
                    if (length % d == 0) {
                        int currentCost = 0;
                        
                        // Check each of the d groups
                        for (int group = 0; group < d; group++) {
                            int l = i + group;
                            int r = i + length - d + group;
                            while (l < r) {
                                if (s.charAt(l) != s.charAt(r)) {
                                    currentCost++;
                                }
                                l += d;
                                r -= d;
                            }
                        }
                        cost[i][j] = Math.min(cost[i][j], currentCost);
                    }
                }
            }
        }

        // Step 2: DP to partition s into k substrings
        // dp[i][p] = min changes for first i characters partitioned into p substrings
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            for (int i = 1; i <= n; i++) {
                // Note: j starts from 2 * (p - 1) at minimum since each valid semi-palindrome has length >= 2,
                // though starting from p - 1 works fine too because invalid states remain INF.
                for (int j = p - 1; j < i; j++) {
                    if (dp[j][p - 1] != INF && cost[j][i - 1] != INF) {
                        dp[i][p] = Math.min(dp[i][p], dp[j][p - 1] + cost[j][i - 1]);
                    }
                }
            }
        }

        return dp[n][k];
    }
}
