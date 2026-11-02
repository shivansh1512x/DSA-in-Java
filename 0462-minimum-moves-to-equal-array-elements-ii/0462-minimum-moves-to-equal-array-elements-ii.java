import java.util.Arrays;

public class Solution {
    public int minMoves2(int[] nums) {
        // 1. Sort the array to easily find the median
        Arrays.sort(nums);
        
        // 2. The median element minimizes the absolute difference
        int median = nums[nums.length / 2];
        
        // 3. Accumulate the distance from each number to the median
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        
        return moves;
    }
}
