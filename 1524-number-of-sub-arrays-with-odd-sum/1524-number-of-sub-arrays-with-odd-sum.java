class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;
        int evenCount = 1; // Represents the initial empty prefix sum of 0
        int oddCount = 0;
        int currentSum = 0;
        int ans = 0;

        for (int num : arr) {
            currentSum += num;

            if (currentSum % 2 != 0) {
                // Odd prefix sum minus any previous even prefix sum gives an odd subarray sum
                ans = (ans + evenCount) % MOD;
                oddCount++;
            } else {
                // Even prefix sum minus any previous odd prefix sum gives an odd subarray sum
                ans = (ans + oddCount) % MOD;
                evenCount++;
            }
        }

        return ans;
    }
}
