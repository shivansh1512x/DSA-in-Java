class Solution {
    public int sortPermutation(int[] nums) {
        int k = -1; // Initialize k with a sentinel value indicating no misplaced element found yet
        
        for (int i = 0; i < nums.length; i++) {
            // Check if the current element is misplaced
            if (nums[i] != i) {
                if (k == -1) {
                    k = nums[i]; // Initialize k with the first misplaced element
                } else {
                    k &= nums[i]; // Bitwise AND with subsequent misplaced elements
                }
            }
        }
        
        // If the array is already sorted, k remains -1, so we return 0
        return k == -1 ? 0 : k;
    }
}
