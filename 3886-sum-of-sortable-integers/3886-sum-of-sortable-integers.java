import java.util.*;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
                if (i * i != n) {
                    divisors.add(n / i);
                }
            }
        }
        
        int ans = 0;
        
        for (int k : divisors) {
            boolean possible = true;
            for (int j = 0; j < n / k; j++) {
                int start = j * k;
                
                int dropCount = 0;
                int dropIdx = -1;
                for (int i = 1; i < k; i++) {
                    if (nums[start + i] < nums[start + i - 1]) {
                        dropCount++;
                        dropIdx = i;
                        if (dropCount > 1) break;
                    }
                }
                
                if (dropCount > 1) {
                    possible = false;
                    break;
                }
                
                int shift = (dropCount == 1) ? dropIdx : 0;
                for (int i = 0; i < k; i++) {
                    if (nums[start + (shift + i) % k] != sortedNums[start + i]) {
                        possible = false;
                        break;
                    }
                }
                if (!possible) break;
            }
            if (possible) {
                ans += k;
            }
        }
        
        return ans;
    }
}
