class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            // Agar element pehle se visited / mark ho chuka hai (0 set kar diya), skip karo
            if (nums[i] == 0) continue;
            
            int slow = i;
            int fast = i;
            boolean isForward = nums[i] > 0;
            
            while (true) {
                // Slow pointer ek step aage jayega
                slow = getNextIndex(nums, slow, isForward);
                if (slow == -1) break;
                
                // Fast pointer do steps aage jayega
                fast = getNextIndex(nums, fast, isForward);
                if (fast == -1) break;
                
                fast = getNextIndex(nums, fast, isForward);
                if (fast == -1) break;
                
                // Agar slow aur fast mil gaye, matlab valid cycle mil gayi
                if (slow == fast) {
                    return true;
                }
            }
            
            // Optimization: Jo path follow karke koi cycle nahi mili,
            // us saare path ke elements ko 0 mark kar do taaki dobara check na karna pade
            int curr = i;
            while (nums[curr] != 0 && (nums[curr] > 0) == isForward) {
                int next = getNextIndex(nums, curr, isForward);
                nums[curr] = 0;
                if (next == -1) break;
                curr = next;
            }
        }
        
        return false;
    }
    
    // Helper function agla valid index calculate karne ke liye
    private int getNextIndex(int[] nums, int curr, boolean isForward) {
        int n = nums.length;
        // Direction change ho gayi toh invalid (-1 return karo)
        if ((nums[curr] > 0) != isForward) {
            return -1;
        }
        
        // Circular next index formula
        int next = ((curr + nums[curr]) % n + n) % n;
        
        // Single element self-loop invalid hai
        if (next == curr) {
            return -1;
        }
        
        return next;
    }
}