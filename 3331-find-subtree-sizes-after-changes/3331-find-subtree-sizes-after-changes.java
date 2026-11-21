import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private List<Integer>[] adj;
    private int[] lastSeen;
    private int[] ans;
    private String s;

    @SuppressWarnings("unchecked")
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        this.ans = new int[n];
        this.lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        dfs(0, -1);
        return ans;
    }

    private void dfs(int u, int origParent) {
        ans[u] = 1;
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];

        // Determine the new parent for node u
        int targetParent = (prevAncestor != -1) ? prevAncestor : origParent;

        // Mark u as the closest ancestor for this character
        lastSeen[charIdx] = u;

        for (int v : adj[u]) {
            dfs(v, u);
        }

        // Restore state on backtrack
        lastSeen[charIdx] = prevAncestor;

        // Add u's final subtree size to its target parent
        if (targetParent != -1) {
            ans[targetParent] += ans[u];
        }
    }
}
