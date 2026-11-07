import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Available heap: stores servers as [weight, index]
        // Sorted by smaller weight, then smaller index
        PriorityQueue<int[]> available = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // Busy heap: stores servers as [weight, index, freeTime]
        // Sorted by earliest freeTime, then smaller weight, then smaller index
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[2] != b[2]) return Integer.compare(a[2], b[2]);
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // Initially, all servers are free
        for (int i = 0; i < n; i++) {
            available.add(new int[]{servers[i], i});
        }

        int currTime = 0;

        for (int j = 0; j < m; j++) {
            // The j-th task arrives at least at second j
            currTime = Math.max(currTime, j);

            // 1. Free up all servers that finished their tasks by current time
            while (!busy.isEmpty() && busy.peek()[2] <= currTime) {
                int[] server = busy.poll();
                available.add(new int[]{server[0], server[1]});
            }

            // 2. If no servers are free, fast-forward time to when the next server finishes
            if (available.isEmpty()) {
                currTime = busy.peek()[2];
                while (!busy.isEmpty() && busy.peek()[2] <= currTime) {
                    int[] server = busy.poll();
                    available.add(new int[]{server[0], server[1]});
                }
            }

            // 3. Take the best available server and assign the task
            int[] bestServer = available.poll();
            ans[j] = bestServer[1];

            // 4. Put the server into the busy queue with its updated free time
            busy.add(new int[]{bestServer[0], bestServer[1], currTime + tasks[j]});
        }

        return ans;
            }
}
