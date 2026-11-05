import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        // Sort arr2 to enable binary search
        Arrays.sort(arr2);
        int distanceValue = 0;

        for (int x : arr1) {
            // Find the insertion point or exact index for (x - d)
            int idx = Arrays.binarySearch(arr2, x - d);
            
            // If the element isn't found, convert the negative return value 
            // to the actual insertion point index
            if (idx < 0) {
                idx = -idx - 1;
            }
            
            // If the index is within bounds and the value at that index 
            // is less than or equal to x + d, then an element exists within 
            // the prohibited range.
            if (idx < arr2.length && arr2[idx] <= x + d) {
                continue;
            }
            
            // If no element violates the condition, increment the count
            distanceValue++;
        }

        return distanceValue;
    }
}
