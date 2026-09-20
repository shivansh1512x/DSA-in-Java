class Solution {
    public double findMaxAverage(int[] nums, int k) {
        // Calculate sum of the first window of size k
        int currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
        }
        
        int maxSum = currentSum;
        
        // Slide the window across the array
        for (int i = k; i < nums.length; i++) {
            currentSum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return (double) maxSum / k;
    }
}