class Solution {
    public int maxVowels(String s, int k) {
        int maxVowelCount = 0;
        int currentVowelCount = 0;
        
        // Count vowels in the first window of size k
        for (int i = 0; i < k; i++) {
            if (isVowel(s.charAt(i))) {
                currentVowelCount++;
            }
        }
        
        maxVowelCount = currentVowelCount;
        
        // Slide the window across the string
        for (int i = k; i < s.length(); i++) {
            if (isVowel(s.charAt(i))) {
                currentVowelCount++;
            }
            if (isVowel(s.charAt(i - k))) {
                currentVowelCount--;
            }
            maxVowelCount = Math.max(maxVowelCount, currentVowelCount);
        }
        
        return maxVowelCount;
    }
    
    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}