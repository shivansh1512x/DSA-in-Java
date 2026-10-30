import java.util.*;

public class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list: u -> list of [v, weight]
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        
        // dist[node][consecutive_count] stores the minimum path weight
        // Initialize all distances to Infinity
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        
        // Min-heap priority queue storing int[]: {cost, node, consecutive_count}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        // Starting state from node 0
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0];
            int u = curr[1];
            int count = curr[2];
            
            // If we reached the destination, return the cost immediately
            if (u == n - 1) {
                return d;
            }
            
            // If we have already found a shorter path to this state, skip it
            if (d > dist[u][count]) {
                continue;
            }
            
            // Traversal over neighboring edges
            for (int[] next : adj.get(u)) {
                int v = next[0];
                int w = next[1];
                
                // Determine consecutive identical characters count
                int nextCount = (labels.charAt(u) == labels.charAt(v)) ? count + 1 : 1;
                
                // Skip if it breaches the consecutive constraint
                if (nextCount > k) {
                    continue;
                }
                
                // Relax edge if a cheaper path to state (v, nextCount) is discovered
                if (d + w < dist[v][nextCount]) {
                    dist[v][nextCount] = d + w;
                    pq.offer(new int[]{dist[v][nextCount], v, nextCount});
                }
            }
        }
        
        return -1;
    }
}
