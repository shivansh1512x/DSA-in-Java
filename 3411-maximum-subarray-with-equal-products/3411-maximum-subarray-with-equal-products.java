class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxVal = 0;
        long totalLcm = 1;
        
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
            totalLcm = lcm(totalLcm, num);
        }
        
        long maxProd = totalLcm * maxVal;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            long prod = 1;
            long curGcd = 0;
            long curLcm = 1;

            for (int j = i; j < n; j++) {
                int x = nums[j];
                prod *= x;
                
                // Stop early since prod only increases and can never match lcm * gcd again
                if (prod > maxProd) {
                    break;
                }

                curGcd = gcd(curGcd, x);
                curLcm = lcm(curLcm, x);

                if (prod == curLcm * curGcd) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}
