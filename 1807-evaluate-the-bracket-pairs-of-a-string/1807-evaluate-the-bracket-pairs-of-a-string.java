import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // 1. Knowledge list ko HashMap mein daalo
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        
        StringBuilder result = new StringBuilder();
        StringBuilder keyBuilder = new StringBuilder();
        boolean insideBracket = false;
        
        // 2. String s par traverse karo
        for (char c : s.toCharArray()) {
            if (c == '(') {
                insideBracket = true;
            } else if (c == ')') {
                insideBracket = false;
                String key = keyBuilder.toString();
                // Map se key search karo, nahi mili toh "?" use karo
                result.append(map.getOrDefault(key, "?"));
                keyBuilder.setLength(0); // clear key builder for next bracket
            } else {
                if (insideBracket) {
                    keyBuilder.append(c); // bracket ke andar ka char key ka part hai
                } else {
                    result.append(c); // normal string char
                }
            }
        }
        
        return result.toString();
    }
}