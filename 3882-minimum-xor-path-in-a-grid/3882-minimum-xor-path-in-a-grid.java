class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length; // Fixed column bounds extraction
        
        // Since max grid value is 1023, the maximum XOR outcome cannot exceed 1023.
        // This maps 1024 distinct possible states (0 to 1023).
        final int MAX_XOR = 1024; 
        
        // Rolling DP arrays: only tracking the previous row to save space.
        // dp[j][k] is true if XOR value 'k' is reachable at column 'j'.
        boolean[][] prevDp = new boolean[n][MAX_XOR];
        
        // Initialize the top-left start cell
        prevDp[0][grid[0][0]] = true;
        
        // Populate the first row (can only move rightward)
        for (int j = 1; j < n; j++) {
            int currentVal = grid[0][j];
            for (int k = 0; k < MAX_XOR; k++) {
                if (prevDp[j - 1][k]) {
                    prevDp[j][k ^ currentVal] = true;
                }
            }
        }
        
        // Process the rest of the rows
        for (int i = 1; i < m; i++) {
            boolean[][] currDp = new boolean[n][MAX_XOR];
            
            // First cell of the current row (can only move downwards from above)
            int firstCellVal = grid[i][0];
            for (int k = 0; k < MAX_XOR; k++) {
                if (prevDp[0][k]) {
                    currDp[0][k ^ firstCellVal] = true;
                }
            }
            
            // Middle cells (can arrive either from the left or from above)
            for (int j = 1; j < n; j++) {
                int currentVal = grid[i][j];
                for (int k = 0; k < MAX_XOR; k++) {
                    // Check if state k came from the cell above (prevDp) or left cell (currDp)
                    if (prevDp[j][k] || currDp[j - 1][k]) {
                        currDp[j][k ^ currentVal] = true;
                    }
                }
            }
            
            // Switch current row states to the next row's history reference
            prevDp = currDp;
        }
        
        // The bottom-right cell is now managed within the final state of prevDp[n - 1]
        // Scan linearly starting at index 0 to fetch the minimum reachable path XOR value.
        for (int k = 0; k < MAX_XOR; k++) {
            if (prevDp[n - 1][k]) {
                return k;
            }
        }
        
        return -1; 
    }
}
