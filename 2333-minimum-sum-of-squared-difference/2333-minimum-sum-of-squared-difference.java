import java.util.Arrays;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        
        // The maximum possible difference given constraints: 10^5 - 0 = 10^5
        int maxDiff = 100000;
        long[] diffFreq = new long[maxDiff + 1];
        long totalDiffSum = 0;
        
        // Count frequencies of each absolute difference
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            diffFreq[diff]++;
            totalDiffSum += diff;
        }
        
        // If total operations k is greater than or equal to the sum of all differences, 
        // we can reduce all differences completely to 0.
        if (totalDiffSum <= k) {
            return 0;
        }
        
        // Greedily reduce the largest differences down to the next level
        for (int d = maxDiff; d > 0; d--) {
            if (diffFreq[d] == 0) {
                continue;
            }
            
            // If operations left cannot even decrement all elements at current max diff by 1
            if (k < diffFreq[d]) {
                diffFreq[d - 1] += k;     // 'k' elements drop down to d - 1
                diffFreq[d] -= k;         // 'k' elements leave the 'd' pool
                k = 0;                    // Used up all operations
                break;
            } else {
                // Decrement all elements at current max difference 'd' by 1 down to 'd - 1'
                k -= diffFreq[d];
                diffFreq[d - 1] += diffFreq[d];
                diffFreq[d] = 0;
            }
        }
        
        // Calculate the final minimum sum of squared differences
        long minSquaredSum = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (diffFreq[d] > 0) {
                minSquaredSum += diffFreq[d] * ((long) d * d);
            }
        }
        
        return minSquaredSum;
    }
}
