import java.util.*;

public class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);
        
        // Step 1: Record the first and last occurrence of each character
        for (int i = 0; i < n; i++) {
            int charIdx = s.charAt(i) - 'a';
            if (first[charIdx] == -1) {
                first[charIdx] = i;
            }
            last[charIdx] = i;
        }
        
        // Track valid extended intervals: intervals[0] = start, intervals[1] = end
        List<int[]> intervals = new ArrayList<>();
        
        // Step 2: Build valid expanded intervals for every unique character
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;
            
            int start = first[i];
            int end = last[i];
            boolean isValid = true;
            
            // Expand the interval dynamically
            for (int j = start; j <= end; j++) {
                int charIdx = s.charAt(j) - 'a';
                
                // If a character inside our window started BEFORE our current window,
                // then this 'start' can never form a valid independent substring.
                if (first[charIdx] < start) {
                    isValid = false;
                    break;
                }
                end = Math.max(end, last[charIdx]);
            }
            
            if (isValid) {
                intervals.add(new int[]{start, end});
            }
        }
        
        // Step 3: CRUCIAL FIX - Sort intervals by their END index
        Collections.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        // Step 4: Greedily pick non-overlapping intervals
        List<String> result = new ArrayList<>();
        int lastEnd = -1;
        
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            
            // If the current interval does not overlap with the last picked one
            if (start > lastEnd) {
                result.add(s.substring(start, end + 1));
                lastEnd = end;
            }
        }
        
        return result;
    }
}
