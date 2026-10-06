class Solution {
    public int minAddToMakeValid(String s) {
        int openNeed = 0;  // Tracks unmatched '('
        int closeNeed = 0; // Tracks unmatched ')'

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                openNeed++; // We have an open parenthesis waiting for a match
            } else {
                if (openNeed > 0) {
                    openNeed--; // The current ')' matches an existing '('
                } else {
                    closeNeed++; // No '(' available, so we need to add an opening one later
                }
            }
        }

        // Total insertions required is the sum of unmatched '(' and ')'
        return openNeed + closeNeed;
    }
}
