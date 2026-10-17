import java.util.Arrays;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        long[] packed = new long[n];
        for (int i = 0; i < n; i++) {
            long sum = getDigitSum(nums[i]);
            packed[i] = (sum << 32) | i;
        }

        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> {
            long valA = packed[a];
            long valB = packed[b];
            int sumA = (int) (valA >>> 32);
            int sumB = (int) (valB >>> 32);
            if (sumA != sumB) {
                return Integer.compare(sumA, sumB);
            }
            return Integer.compare(nums[(int) valA], nums[(int) valB]);
        });

        boolean[] visited = new boolean[n];
        int cycles = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                cycles++;
                int j = i;
                while (!visited[j]) {
                    visited[j] = true;
                    j = indices[j];
                }
            }
        }

        return n - cycles;
    }

    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
