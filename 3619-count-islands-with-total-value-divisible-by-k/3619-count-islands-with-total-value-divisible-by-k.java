import java.util.Stack;

class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int divisibleIslandsCount = 0;
        
        // 4-directional offsets: Up, Down, Left, Right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Found a piece of unvisited land
                if (grid[i][j] > 0) {
                    long islandSum = 0; // Using long to prevent integer overflow
                    Stack<int[]> stack = new Stack<>();
                    
                    // Push the starting cell, add its value, and mark as visited
                    stack.push(new int[]{i, j});
                    islandSum += grid[i][j];
                    grid[i][j] = 0; 
                    
                    // Iterative DFS
                    while (!stack.isEmpty()) {
                        int[] cell = stack.pop();
                        int r = cell[0];
                        int c = cell[1];
                        
                        for (int[] dir : directions) {
                            int nr = r + dir[0];
                            int nc = c + dir[1];
                            
                            // Validate grid boundaries and if the adjacent cell is land
                            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] > 0) {
                                islandSum += grid[nr][nc];
                                grid[nr][nc] = 0; // Sink the cell to mark it visited
                                stack.push(new int[]{nr, nc});
                            }
                        }
                    }
                    
                    // Check if the total island sum is divisible by k
                    if (islandSum % k == 0) {
                        divisibleIslandsCount++;
                    }
                }
            }
        }
        
        return divisibleIslandsCount;
    }
}
