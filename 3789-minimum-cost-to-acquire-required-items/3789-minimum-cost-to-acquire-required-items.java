class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // Cast inputs to long to prevent integer overflow during multiplication
        long c1 = cost1;
        long c2 = cost2;
        long cb = costBoth;
        long n1 = need1;
        long n2 = need2;
        
        // Step 1: Fulfill the overlapping needs for both type 1 and type 2
        long commonNeeds = Math.min(n1, n2);
        long totalCost = commonNeeds * Math.min(c1 + c2, cb);
        
        // Step 2: Fulfill any remaining single-type needs
        if (n1 > n2) {
            totalCost += (n1 - n2) * Math.min(c1, cb);
        } else {
            totalCost += (n2 - n1) * Math.min(c2, cb);
        }
        
        return totalCost;
    }
}
