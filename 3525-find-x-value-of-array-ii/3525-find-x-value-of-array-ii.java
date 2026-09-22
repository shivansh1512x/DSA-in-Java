import java.util.Arrays;

class Solution {

    // Segment Tree Node definition
    class SegmentTreeNode {
        int prod;             // Range ka product modulo k
        int[] counts;         // Prefix product counts modulo k

        SegmentTreeNode(int k) {
            this.prod = 1;
            this.counts = new int[k];
        }
    }

    private SegmentTreeNode[] tree;
    private int n;
    private int kVal;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.kVal = k;
        this.tree = new SegmentTreeNode[4 * n];

        // Segment Tree build karo
        build(1, 0, n - 1, nums);

        // Driver expects int[] return type
        int[] result = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int targetX = queries[q][3];

            // Step 1: Array index par new value update karo (persistent)
            update(1, 0, n - 1, index, value);

            // Step 2: Range [start, n - 1] query karo
            SegmentTreeNode queryRes = query(1, 0, n - 1, start, n - 1);

            // targetX ki remainder count result mein daalo
            result[q] = queryRes.counts[targetX];
        }

        return result;
    }

    // Do segment nodes ko merge karna
    private SegmentTreeNode merge(SegmentTreeNode left, SegmentTreeNode right) {
        if (left == null) return right;
        if (right == null) return left;

        SegmentTreeNode res = new SegmentTreeNode(kVal);
        res.prod = (left.prod * right.prod) % kVal;

        // Combine prefix counts
        for (int r = 0; r < kVal; r++) {
            res.counts[r] = left.counts[r];
        }
        for (int r = 0; r < kVal; r++) {
            if (right.counts[r] > 0) {
                int newRem = (left.prod * r) % kVal;
                res.counts[newRem] += right.counts[r];
            }
        }

        return res;
    }

    // Segment tree build method
    private void build(int node, int start, int end, int[] nums) {
        tree[node] = new SegmentTreeNode(kVal);
        if (start == end) {
            int val = nums[start] % kVal;
            tree[node].prod = val;
            tree[node].counts[val] = 1;
            return;
        }

        int mid = start + (end - start) / 2;
        build(2 * node, start, mid, nums);
        build(2 * node + 1, mid + 1, end, nums);

        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    // Point update method
    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            int modVal = val % kVal;
            tree[node].prod = modVal;
            Arrays.fill(tree[node].counts, 0);
            tree[node].counts[modVal] = 1;
            return;
        }

        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }

        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    // Range query method
    private SegmentTreeNode query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return null;
        }
        if (l <= start && end <= r) {
            return tree[node];
        }

        int mid = start + (end - start) / 2;
        SegmentTreeNode leftRes = query(2 * node, start, mid, l, r);
        SegmentTreeNode rightRes = query(2 * node + 1, mid + 1, end, l, r);

        return merge(leftRes, rightRes);
    }
}