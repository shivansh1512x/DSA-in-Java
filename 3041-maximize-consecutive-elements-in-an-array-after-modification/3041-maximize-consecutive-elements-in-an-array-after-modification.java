import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int x : nums) {
            int lenPlusOne = dp.getOrDefault(x, 0) + 1;
            int lenSame = dp.getOrDefault(x - 1, 0) + 1;

            dp.put(x + 1, lenPlusOne);
            dp.put(x, lenSame);

            ans = Math.max(ans, Math.max(lenSame, lenPlusOne));
        }

        return ans;
    }
}
