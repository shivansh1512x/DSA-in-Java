class Solution {
    public String[] createGrid(int m, int n) {
        // Initialize an m x n grid filled entirely with obstacles ('#')
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = '#';
            }
        }
        
        // Start at the top-left corner
        int r = 0;
        int c = 0;
        grid[r][c] = '.';
        
        // Carve out a single deterministic path
        while (r < m - 1 || c < n - 1) {
            if (c < n - 1) {
                // Move Right if we haven't reached the last column
                c++;
            } else {
                // Move Down if we are at the last column
                r++;
            }
            grid[r][c] = '.';
        }
        
        // Convert the 2D char array into a String array
        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = new String(grid[i]);
        }
        
        return result;
    }
}
