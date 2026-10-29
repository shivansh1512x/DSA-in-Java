import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private int n;
    private int[] head, to, next;
    private int edgeCount;
    
    private int[] start, end, depth;
    private int[][] up;
    private int timer;
    private int LOG;
    
    private int[] bit;
    private int[] currentMask;

    private void addEdge(int u, int v) {
        to[edgeCount] = v;
        next[edgeCount] = head[u];
        head[u] = edgeCount++;
    }

    private void updateBIT(int idx, int val) {
        for (; idx <= 2 * n; idx += idx & -idx) {
            bit[idx] ^= val;
        }
    }

    private int queryBIT(int idx) {
        int sum = 0;
        for (; idx > 0; idx -= idx & -idx) {
            sum ^= bit[idx];
        }
        return sum;
    }

    private void dfs(int u, int p, int d) {
        start[u] = ++timer;
        depth[u] = d;
        up[u][0] = p; // FIXED: Assign to the 0-th column row element
        for (int i = 1; i < LOG; ++i) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }
        
        for (int e = head[u]; e != -1; e = next[e]) {
            int v = to[e];
            if (v != p) {
                dfs(v, u, d + 1);
            }
        }
        end[u] = ++timer;
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u; u = v; v = temp;
        }
        for (int i = LOG - 1; i >= 0; --i) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }
        if (u == v) return u;
        for (int i = LOG - 1; i >= 0; --i) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }
        return up[u][0]; // FIXED: Return the 0-th column element (immediate parent)
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        
        head = new int[n];
        Arrays.fill(head, -1);
        to = new int[2 * n];
        next = new int[2 * n];
        edgeCount = 0;
        
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1]);
            addEdge(edge[1], edge[0]);
        }
        
        start = new int[n];
        end = new int[n];
        depth = new int[n];
        timer = 0;
        
        LOG = 0;
        while ((1 << LOG) <= n) LOG++;
        LOG++;
        up = new int[n][LOG];
        
        dfs(0, 0, 0);
        
        bit = new int[2 * n + 1];
        currentMask = new int[n];
        
        for (int i = 0; i < n; ++i) {
            int mask = 1 << (s.charAt(i) - 'a');
            currentMask[i] = mask;
            updateBIT(start[i], mask);
            updateBIT(end[i], mask);
        }
        
        List<Boolean> ans = new ArrayList<>(queries.length);
        
        for (String q : queries) {
            if (q.charAt(0) == 'u') { // "update ui c"
                int firstSpace = q.indexOf(' ');
                int secondSpace = q.indexOf(' ', firstSpace + 1);
                
                int u = 0;
                for (int i = firstSpace + 1; i < secondSpace; i++) {
                    u = u * 10 + (q.charAt(i) - '0');
                }
                char c = q.charAt(secondSpace + 1);
                
                int newMask = 1 << (c - 'a');
                int change = currentMask[u] ^ newMask;
                currentMask[u] = newMask;
                
                updateBIT(start[u], change);
                updateBIT(end[u], change);
            } else { // "query ui vi"
                int firstSpace = q.indexOf(' ');
                int secondSpace = q.indexOf(' ', firstSpace + 1);
                
                int u = 0;
                for (int i = firstSpace + 1; i < secondSpace; i++) {
                    u = u * 10 + (q.charAt(i) - '0');
                }
                int v = 0;
                for (int i = secondSpace + 1; i < q.length(); i++) {
                    v = v * 10 + (q.charAt(i) - '0');
                }
                
                int lca = getLCA(u, v);
                int maskU = queryBIT(start[u]);
                int maskV = queryBIT(start[v]);
                int maskLca = currentMask[lca];
                
                int pathMask = maskU ^ maskV ^ maskLca;
                
                boolean isPalindrome = (pathMask == 0) || ((pathMask & (pathMask - 1)) == 0);
                ans.add(isPalindrome);
            }
        }
        
        return ans;
    }
}
