import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] cntFactor = new int[n + 1];
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            cntFactor[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] cntMulti = new int[n + 1];
        List<long[]> candidates = new ArrayList<>();

        for (int[] item : items) {
            int factor = item[0];
            long price = item[1];

            if (price >= 2L * minPrice) {
                continue;
            }

            if (cntMulti[factor] == 0) {
                int total = 0;
                for (int mult = factor; mult <= n; mult += factor) {
                    total += cntFactor[mult];
                }
                cntMulti[factor] = total;
            }

            int cap = cntMulti[factor] - 1;
            if (cap > 0) {
                candidates.add(new long[]{price, cap});
            }
        }

        candidates.sort(Comparator.comparingLong(a -> a[0]));

        long ans = 0;
        long remBudget = budget;

        for (long[] cand : candidates) {
            long price = cand[0];
            long cap = cand[1];

            if (remBudget < price) {
                break;
            }

            long take = Math.min(cap, remBudget / price);
            remBudget -= take * price;
            ans += take * 2;
        }

        ans += remBudget / minPrice;
        return (int) ans;
    }
}
