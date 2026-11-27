class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freeCnt = new int[n];

        for (int i = 0; i < n; i++) {
            int fi = items[i][0];
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % fi == 0) {
                    cnt++;
                }
            }
            freeCnt[i] = cnt;
        }

        int[] dp = new int[budget + 1];

        // Step 1: 0/1 knapsack for the first copy of each item (unlocks free copies)
        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int val = 1 + freeCnt[i];
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + val);
            }
        }

        // Step 2: Unbounded knapsack for additional copies (1 item per copy)
        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            for (int b = price; b <= budget; b++) {
                dp[b] = Math.max(dp[b], dp[b - price] + 1);
            }
        }

        int ans = 0;
        for (int val : dp) {
            ans = Math.max(ans, val);
        }
        return ans;
    }
}
