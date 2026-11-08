class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Step 1: Count total servers in each row and each column
        int[] rowCounts = new int[m];
        int[] colCounts = new int[n];
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    rowCounts[r]++;
                    colCounts[c]++;
                }
            }
        }
        
        // Step 2: Count servers that can communicate
        int communicatingServers = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    // If there's more than 1 server in its row OR column, it can communicate
                    if (rowCounts[r] > 1 || colCounts[c] > 1) {
                        communicatingServers++;
                    }
                }
            }
        }
        
        return communicatingServers;
    }
}
