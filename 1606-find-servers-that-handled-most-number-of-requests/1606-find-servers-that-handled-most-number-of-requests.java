import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // Track the number of requests handled by each server
        int[] count = new int[k];
        
        // Pool of available servers (ordered by their IDs)
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        
        // Min-heap to track busy servers: [free_time, server_id]
        // Sorted by free_time ascending
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        for (int i = 0; i < arrival.length; i++) {
            int currTime = arrival[i];
            
            // 1. Free up servers that finished their tasks before or at the current arrival time
            while (!busy.isEmpty() && busy.peek()[0] <= currTime) {
                int[] freed = busy.poll();
                available.add(freed[1]);
            }
            
            // 2. If no servers are available, the request is dropped
            if (available.isEmpty()) {
                continue;
            }
            
            // 3. Find the first available server starting from i % k
            int target = i % k;
            Integer server = available.ceiling(target);
            
            // 4. Wrap around to the beginning if no server >= target is found
            if (server == null) {
                server = available.first();
            }
            
            // 5. Assign the request to the found server
            available.remove(server);
            busy.offer(new int[]{currTime + load[i], server});
            count[server]++;
        }
        
        // Find the maximum number of requests handled by any server
        int maxRequests = 0;
        for (int c : count) {
            maxRequests = Math.max(maxRequests, c);
        }
        
        // Collect all servers that achieved the maximum count
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == maxRequests) {
                result.add(i);
            }
        }
        
        return result;
    }
}
