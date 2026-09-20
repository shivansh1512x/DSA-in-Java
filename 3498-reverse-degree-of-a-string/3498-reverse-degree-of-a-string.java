class Solution {
    public int reverseDegree(String s) {
        int totalSum = 0;
        for (int i = 0; i < s.length(); i++) {
            // Reversed value: 'a' -> 26, 'b' -> 25, ..., 'z' -> 1
            int reversedVal = 26 - (s.charAt(i) - 'a');
            
            // 1-indexed position is (i + 1)
            totalSum += reversedVal * (i + 1);
        }
        return totalSum;
    }
}