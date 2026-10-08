class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int opened = 0; // Tracks the current nesting depth

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // If it's not the outermost '(' of a primitive string, add it
                if (opened > 0) {
                    result.append(c);
                }
                opened++;
            } else { // c == ')'
                opened--;
                // If it's not the outermost ')' of a primitive string, add it
                if (opened > 0) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}
