class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int totalSum = 0;
        
        for (int point : cardPoints) {
            totalSum += point;
        }
        
        // If we pick all cards, return total sum
        if (k == n) {
            return totalSum;
        }
        
        int windowSize = n - k;
        int currentWindowSum = 0;
        
        // Compute sum of initial window of size (n - k)
        for (int i = 0; i < windowSize; i++) {
            currentWindowSum += cardPoints[i];
        }
        
        int minWindowSum = currentWindowSum;
        
        // Slide the window across the array
        for (int i = windowSize; i < n; i++) {
            currentWindowSum += cardPoints[i] - cardPoints[i - windowSize];
            minWindowSum = Math.min(minWindowSum, currentWindowSum);
        }
        
        return totalSum - minWindowSum;
    }
}