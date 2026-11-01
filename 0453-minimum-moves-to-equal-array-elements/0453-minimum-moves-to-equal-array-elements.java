class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0];
        long sum = 0;
        
        // Find the minimum element and calculate the total sum
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
            sum += num;
        }
        
        // Apply the formula: sum - (n * min)
        return (int) (sum - (long) nums.length * min);
    }
}
