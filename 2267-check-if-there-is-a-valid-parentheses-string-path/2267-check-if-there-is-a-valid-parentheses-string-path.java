class Solution {
    private Boolean[][][] memo;
    private int m, n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length; // Fixed: Correctly get column length
        
        // Quick early exits:
        // 1. Total path length (m + n - 1) must be even for a valid parentheses string.
        // 2. The start cell cannot be ')' and the end cell cannot be '('.
        if ((m + n) % 2 == 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        // The running balance 'k' can theoretically grow up to the maximum path length (m + n - 1).
        // Allocating m + n size covers all valid index bounds.
        int maxBalance = m + n;
        memo = new Boolean[m][n][maxBalance];

        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int r, int c, int k) {
        // Adjust the open parentheses balance based on the current cell
        if (grid[r][c] == '(') {
            k++;
        } else {
            k--;
        }

        // Invalid cases: 
        // 1. More closing brackets than opening brackets (k < 0)
        // 2. More opening brackets than remaining steps available to close them
        if (k < 0 || k > (m - 1 - r + n - 1 - c)) {
            return false;
        }

        // Base case: Reached the bottom-right cell
        if (r == m - 1 && c == n - 1) {
            return k == 0;
        }

        // Return memoized result if already computed
        if (memo[r][c][k] != null) {
            return memo[r][c][k];
        }

        boolean res = false;
        
        // Move Down
        if (r + 1 < m) {
            res = res || dfs(grid, r + 1, c, k);
        }
        
        // Move Right
        if (c + 1 < n) {
            res = res || dfs(grid, r, c + 1, k);
        }

        // Cache and return the result
        return memo[r][c][k] = res;
    }
}