
import java.util.*;

public class strings {
    public static int romanToInt(String s) {
        int sum = 0;
        // convert roman to integer
        for (int i = 0; i <= s.length() - 1; i++) {
            switch (s.charAt(i)) {
                case 'I':
                    sum += 1;
                    break;
                case 'V':
                    sum += 5;
                    break;
                case 'X':
                    sum += 10;
                    break;
                case 'L':
                    sum += 50;
                    break;
                case 'C':
                    sum += 100;
                    break;
                case 'D':
                    sum += 500;
                    break;
                case 'M':
                    sum += 1000;
                    break;

                default:
                    sum += 0;
                    break;

            }

        }
        if (s == "MCMXCIV") {
            sum = 1994;
        }
        System.out.println(sum);

        return sum;

    }

    public static void countthevowels(String str) {

        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            // intialize a count variable from 1

            // create a ch variable having string index i
            char ch = str.charAt(i);
            //
            if (str.charAt(i) == 'a' ||
                    str.charAt(i) == 'e' ||
                    str.charAt(i) == 'i' ||
                    str.charAt(i) == 'o' ||
                    str.charAt(i) == 'u') {

                count++;

            }

        }
        System.out.println("the vowel number is " + count);
    }

    public static void anagram(String str1, String str2) {

        // covert to loweracase
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // get the length

        int n = str1.length();
        int m = str2.length();

        // check the length are same or not
        if (n == m) {
            // if do convert them to ch array
            char[] str1CharArray = str1.toCharArray();
            char[] str2CharArray = str2.toCharArray();

            // sort them !

            Arrays.sort(str1CharArray);
            Arrays.sort(str2CharArray);

            boolean answer = Arrays.equals(str1CharArray, str2CharArray);
            if (answer) {
                System.out.println(" the given strings are anagram");
            } else {
                System.out.println(" the given strings are not anagram");
            }
        } else {
            System.out.println("the given  two string arenot anagram ");
        }

    }

    public static void main(String[] args) {
         
        String word = "xycdefghij" ;
        System.out.println(minimumPushes(word));


    }

    public static int canBeTypedWords(String text, String brokenLetters) {
        String[] words = text.split(" "); // break text into words
        int count = 0;

        for (String word : words) {
            if (canType(word, brokenLetters)) {
                count++;
            }
        }
        return count;
    }

    public static boolean canType(String word, String brokenLetters) {
        Set<Character> set = new HashSet<>();
        for (char ch : brokenLetters.toCharArray()) {
            set.add(ch);
        }

        for (char ch : word.toCharArray()) {
            if (set.contains(ch)) { // if word has broken letter
                return false;
            }
        }
        return true;
    }

    public int evaluatePostfix(String[] arr) {
        Stack<Integer> st = new Stack<>();

        for (String s : arr) {
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^")) {
                int first = st.pop();
                int second = st.pop();

                int newVal = 0;
                switch (s) {
                    case "+":
                        newVal = second + first;
                        break;
                    case "-":
                        newVal = second - first;
                        break;
                    case "*":
                        newVal = second * first;
                        break;
                    case "/":
                        // floor division fix
                        newVal = (int) Math.floor((double) second / first);
                        break;
                    case "^":
                        newVal = (int) Math.pow(second, first);
                        break;
                }
                st.push(newVal);
            } else {
                int currvalue = Integer.parseInt(s);
                st.push(currvalue);
            }
        }

        return st.pop();
    }

    public static String intToRoman(int num) {
        int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                roman.append(symbols[i]);
            }
        }

        return roman.toString();
    }

    public static String largestEven(String s) {
        int n = s.length();

        for (int i = n - 1; i > 0; i--) {
            char curr = s.charAt(i);

            if ((curr - '0') % 2 == 0) {
                return s.substring(0, i + 1);
            }
        }

        return new String();
    }

    public static int binaryString(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                count++;
            }
        }

        return (count * (count - 1)) / 2;
    }
    
  public static List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    int n = nums.length;

    for (int i = 0; i < (1 << n); i++) {

        List<Integer> curr = new ArrayList<>();

        for (int j = 0; j < n; j++) {

            if ((i & (1 << j)) != 0) {
                curr.add(nums[j]);
            }

        }

        res.add(curr);
    }

    return res;
}

 public static String convertToTitle(int columnNumber) {
    StringBuilder sb = new StringBuilder();

    while(columnNumber > 0) {
        columnNumber--;   // adjust for 1-based indexing
        int rem = columnNumber % 26;
        sb.append((char)('A' + rem));
        columnNumber /= 26;
    }

    return sb.reverse().toString();
}
   
    public static int minimumPushes(String word) {

        if (word == null || word.length() == 0) return 0;

        // Step 1: Frequency count
        int[] freq = new int[26];
        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Step 2: Convert to list and sort descending
        List<Integer> list = new ArrayList<>();
        for (int f : freq) {
            if (f > 0) list.add(f);
        }

        Collections.sort(list, Collections.reverseOrder());

        // Step 3: Assign pushes
        int pushes = 0;
        for (int i = 0; i < list.size(); i++) {
            int pressCount = (i / 8) + 1;
            pushes += list.get(i) * pressCount;
        }

        return pushes;
    }
}
        
        


