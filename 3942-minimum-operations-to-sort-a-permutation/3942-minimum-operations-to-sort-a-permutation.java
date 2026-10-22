import java.util.Arrays;

public class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int idx = -1;
        
       
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                idx = i;
                break;
            }
        }
        
        
        boolean isAsc = true;
        for (int i = 0; i < n; i++) {
            if (nums[(idx + i) % n] != i) {
                isAsc = false;
                break;
            }
        }
        if (isAsc) {
            return Math.min(idx, 2 + n - idx);
        }
        
        
        boolean isDsc = true;
        for (int i = 0; i < n; i++) {
            if (nums[(idx - i + n) % n] != i) {
                isDsc = false;
                break;
            }
        }
        if (isDsc) {
            return Math.min(n - idx, idx + 2);
        }
        
       
        return -1;
    }
}
