class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int rightNeeded = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // If we need an odd number of ')', the previous '(' has only one ')' so far.
                // We must insert 1 ')' to complete that pair before moving on.
                if (rightNeeded % 2 == 1) {
                    insertions++;
                    rightNeeded--;
                }
                rightNeeded += 2;
            } else {
                // We found a ')'. Check if the next character is also a ')' to form '))'
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i++; // Consume the second ')' by skipping the next index
                    rightNeeded -= 2;
                } else {
                    // It's a single isolated ')'
                    rightNeeded -= 1;
                }

                // If rightNeeded goes below 0, it means we have a closing tag without an opening '('
                if (rightNeeded < 0) {
                    insertions++;     // Insert 1 opening '('
                    rightNeeded += 2; // The new '(' expects 2 closing brackets
                }
            }
        }

        // At the end, add any remaining right brackets that are still needed
        return insertions + rightNeeded;
    }
}
