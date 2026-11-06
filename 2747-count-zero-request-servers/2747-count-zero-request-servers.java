import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[][] sortedQueries = new int[m][2];
        
        // Store queries alongside their original indices
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i]; // query time
            sortedQueries[i][1] = i;          // original index
        }
        
        // Sort logs by time
        Arrays.sort(logs, Comparator.comparingInt(a -> a[1]));
        
        // Sort queries by time
        Arrays.sort(sortedQueries, Comparator.comparingInt(a -> a[0]));
        
        int[] ans = new int[m];
        int[] serverCounts = new int[n + 1]; // 1-indexed server frequencies
        int activeServers = 0;
        
        int left = 0;
        int right = 0;
        int numLogs = logs.length;
        
        for (int i = 0; i < m; i++) {
            int qTime = sortedQueries[i][0];
            int origIdx = sortedQueries[i][1];
            
            int windowStart = qTime - x;
            int windowEnd = qTime;
            
            // Expand the window: Add logs that fall within the current query's end time
            while (right < numLogs && logs[right][1] <= windowEnd) {
                int serverId = logs[right][0];
                if (serverCounts[serverId] == 0) {
                    activeServers++;
                }
                serverCounts[serverId]++;
                right++;
            }
            
            // Shrink the window: Remove logs that fall out of the current query's start time
            while (left < right && logs[left][1] < windowStart) {
                int serverId = logs[left][0];
                serverCounts[serverId]--;
                if (serverCounts[serverId] == 0) {
                    activeServers--;
                }
                left++;
            }
            
            // The remaining servers had 0 requests in the window
            ans[origIdx] = n - activeServers;
        }
        
        return ans;
    }
}
