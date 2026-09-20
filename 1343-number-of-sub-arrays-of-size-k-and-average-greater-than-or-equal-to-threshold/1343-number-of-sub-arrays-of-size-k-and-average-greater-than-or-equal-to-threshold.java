class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int targetSum = k * threshold;
        int currentSum = 0;
        int count = 0;
        
        // Sum of the first window of size k
        for (int i = 0; i < k; i++) {
            currentSum += arr[i];
        }
        
        if (currentSum >= targetSum) {
            count++;
        }
        
        // Slide the window across the array
        for (int i = k; i < arr.length; i++) {
            currentSum += arr[i] - arr[i - k];
            if (currentSum >= targetSum) {
                count++;
            }
        }
        
        return count;
    }
}