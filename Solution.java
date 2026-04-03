import java.util.*;

public class Solution {
    public static int possibleStringCount(String word) {
        int n = word.length();
        int count = 0;

        Set<Character> unique = new HashSet<>();
        for (char ch : word.toCharArray()) {
            unique.add(ch);
        }

        for (int i = 0; i < n; i++) {
            Set<Character> seen = new HashSet<>();
            for (int j = i; j < n; j++) {
                seen.add(word.charAt(j));
                String sub = word.substring(i, j + 1);

                if (seen.containsAll(unique)) {
                    System.out.println("Valid substring: " + sub);
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String word = "abbcccc";
        int result = possibleStringCount(word);
        System.out.println("Answer: " + result);  // Expecting: 5
    }
}
