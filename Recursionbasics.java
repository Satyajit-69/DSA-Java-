import java.lang.reflect.Array;
import java.util.*;
public class Recursionbasics {
    public static void towerofhanoi(char source, char auxilary, char destiny, int n) { /// here no of disk = 3

        // base case
        if (n == 0) {
            return;
        }
        // faith 1st and 2nd disk will move to aux
        towerofhanoi(source, destiny, auxilary, n - 1);
        int total = 3;

        // work

        // moving 3 rd disk from source to destiny
        System.out.println(total - n + 1 + "th  disk " + " will  move from " + source + " tower " + " to " + destiny);

        // faith ~> 1st and 2nd will move to destiny from aux tower
        towerofhanoi(auxilary, source, destiny, n - 1);

    }

    public static long noOfDigits(int N) {
        // code here
        long count = 0;
        int nthFib = nthFibonacci(N);
        while (nthFib > 0) {
            count++;
            nthFib /= 10;
        }
        return count;
    }

    public static int nthFibonacci(int n) {
        // code here
        if (n == 1 || n == 0) {
            return n;
        }

        int fibNM1 = nthFibonacci(n - 1);
        int fibNM2 = nthFibonacci(n - 2);

        return fibNM1 + fibNM2;
    }

    public static void printNtimes(int n) {
        if (n == 0) {
            System.out.println("function call ends here");
            return;
        }

        System.out.println("Satyajit Sahoo Calling");
        printNtimes(n - 1);
    }

    static int i = 1;

    public static void print1toN(int n) {
        if (n == 0) {
            return;
        }

        System.out.println(i);
        i++;
        print1toN(n - 1);

    }

    public static void printNtoI(int i, int n) {
        if (i < n) {
            return;
        }

        System.out.println(n);
        printNtoI(i, n + 1);

    }

    public static void printNtoIBackTrack(int i, int n) {
        if (i < n) {
            return;
        }

        printNtoI(i, n + 1);
        System.out.println(n); // let the function complete first i will wait

    }

    public static void reverseArr(int arr[], int i, int j) {

        if (i >= j) {
            return;
        }

        swap(arr, i, j);
        reverseArr(arr, i + 1, j - 1);

    }

    public static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static boolean isPalindrome(String s, int left, int right) {
        if (left >= right) {
            return true;
        }

        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }

        return isPalindrome(s, left + 1, right - 1);
    }

    public static void printSubSeq(int arr[], int idx, ArrayList<Integer> list) {

        if (idx == arr.length) {
            System.out.println(list);
            return;
        }

        // include option
        list.add(arr[idx]);
        printSubSeq(arr, idx + 1, list);

        // exculde option
        list.remove(list.size() - 1);
        printSubSeq(arr, idx + 1, list);

    }

    public static void printSubSeqWithSumK(int arr[], int k, int idx, ArrayList<Integer> list, int sum) {

        if (idx == arr.length) {
            if (sum == k) {
                System.out.println(list);
            }
            return;
        }

        // ✅ include current element
        list.add(arr[idx]);
        printSubSeqWithSumK(arr, k, idx + 1, list, sum + arr[idx]);

        // ✅ exclude current element (backtrack)
        list.remove(list.size() - 1);
        printSubSeqWithSumK(arr, k, idx + 1, list, sum);
    }

    static long MOD = 1000000007L;

    public int countGoodNumbers(long n) {
        long even = (n + 1) / 2; // even indices
        long odd = n / 2; // odd indices

        long totalCalculated = (power(5, even) * power(4, odd)) % MOD;
        return (int) totalCalculated;
    }

    public static long power(long base, long exp) {
        if (exp == 0)
            return 1;

        long half = power(base, exp / 2);
        long result = (half * half) % MOD;

        if (exp % 2 == 1)
            result = (result * base) % MOD;

        return result;
    }

    public static void sortStack(Stack<Integer> s) {

        if (!s.isEmpty()) {
            int curr = s.pop();
            sortStack(s);// sort the stack
            insert(s, curr); // push the element in perfect position
        }

    }

    public static void insert(Stack<Integer> s, int ele) {
        if (s.isEmpty() || s.peek() <= ele) { // push on the top
            s.push(ele);
            return;

        }
        // search the perfect position and push
        int val = s.pop();
        insert(s, ele);
        s.push(val);

    }

    public static void reverseStack(Stack<Integer> s) {
        if (!s.isEmpty()) {
            int top = s.pop();
            reverseStack(s);
            insertAtBottom(s, top);
        }
    }

    public static void insertAtBottom(Stack<Integer> s, int curr) {
        if (!s.isEmpty()) {
            int top = s.pop();
            insertAtBottom(s, curr);
            s.push(top);
        } else {
            // no element left push at the top
            s.push(curr);
            return;
        }
    }

    public static void printBinaryString(int n, String str) {
        if (str.length() == n) {
            System.out.println(str);
            return;
        }

        // work
        printBinaryString(n, str + "0");
        printBinaryString(n, str + "1");

    }

   

    public static List<String> letterCombinations(String digits) {
        int n = digits.length();
        List<String> res = new ArrayList<>();

        // check the base case
        if (digits == null || digits.length() == 0) {
            return res;
        }

        // Map
        Map<Character, List<Character>> hm = new HashMap<>();

        // add elements
        hm.put('2', Arrays.asList('a', 'b', 'c'));
        hm.put('3', Arrays.asList('d', 'e', 'f'));
        hm.put('4', Arrays.asList('g', 'h', 'i'));
        hm.put('5', Arrays.asList('j', 'k', 'l'));
        hm.put('6', Arrays.asList('m', 'n', 'o'));
        hm.put('7', Arrays.asList('p', 'q', 'r', 's'));
        hm.put('8', Arrays.asList('t', 'u', 'v'));
        hm.put('9', Arrays.asList('w', 'x', 'y', 'z'));

        helper(digits, hm, 0, "", res);
        return res;

    }

    public static void helper(String digits, Map<Character, List<Character>> hm, int idx, String curr,
            List<String> res) {

        // base case
        if (curr.length() == digits.length()) {
            res.add(curr);
            return;
        }

        char digit = digits.charAt(idx);
        // extract the letters
        List<Character> letters = hm.get(digit);
        // add recursively
        for (char ch : letters) {
            helper(digits, hm, idx + 1, curr + ch, res);
        }

    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = candidates.length;
        pickingHelper(n, candidates, 0, target, new ArrayList<>(), res);
        return res;

    }

    public static void pickingHelper(int n, int arr[], int idx, int target, List<Integer> curr,
            List<List<Integer>> ans) {
        // reached the end
        if (idx == n) {
            // found the sum
            if (target == 0) {
                ans.add(new ArrayList<>(curr));
            }
            return;
        }

        // picking
        if (arr[idx] <= target) {
            // add
            curr.add(arr[idx]);
            pickingHelper(n, arr, idx, target - arr[idx], curr, ans);
            curr.remove(curr.size() - 1); // backtrack
        }

        // Not picking
        pickingHelper(n, arr, idx + 1, target, curr, ans);
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);

        helper(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    public static void helper(int[] arr, int target, int idx,
            List<Integer> curr, List<List<Integer>> res) {

        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = idx; i < arr.length; i++) {

            // skip duplicates
            if (i > idx && arr[i] == arr[i - 1])
                continue;

            if (arr[i] > target)
                break;

            curr.add(arr[i]);

            helper(arr, target - arr[i], i + 1, curr, res);

            curr.remove(curr.size() - 1);
        }
    }
    public  static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>() ;

        if(n == 0) {
            return res ;
        }

        combinatiohelper(k,n,1,new ArrayList<>(),res) ;
        return res ;
        
    }
    public static void combinatiohelper(int k , int target , int idx ,List<Integer> curr,List<List<Integer>> res){
         
         if(target == 0 && curr.size() == k){
            res.add(new ArrayList<>(curr));
            return ;
         }
         
         if(target < 0 || curr.size()>k){
            return ;
         }
        
         for(int i=idx;i<=9;i++) {
             curr.add(i);
             combinatiohelper(k, target-i , i + 1, curr, res);
             curr.remove(curr.size()-1) ;//backtrack
         }
    }


    public static ArrayList<Integer> sumofSubset(int arr[]) {
         int n = arr.length ;

         ArrayList <Integer> res = new ArrayList<>() ;
         subsetsumCalculator(arr,0,0,res) ;
         return res ;



         
    }
    public static void main(String[] args) {
        int arr[] =  {2, 3 ,3, 2} ;
       ArrayList< ArrayList<Integer>> res =findSubsets(arr);
        System.out.println(res);
      
    }


    public static void subsetsumCalculator(int arr[] , int sum , int idx , ArrayList<Integer>list) {
           
          if(idx == arr.length) {
             list.add(sum);
             return ;
          }

          subsetsumCalculator(arr, sum + arr[idx], idx + 1, list);//picking
          subsetsumCalculator(arr, sum , idx + 1, list);//not picking 


    }
    public static ArrayList<ArrayList<Integer>> findSubsets(int[] arr) {
        int n = arr.length ;
        Arrays.sort(arr);
        ArrayList<ArrayList<Integer>> res= new ArrayList<>();
        helperForSubset(arr,0,new ArrayList<Integer>(),res);
        return res;
        
    }


    public static void helperForSubset(int arr[],int idx ,ArrayList<Integer> curr, ArrayList<ArrayList<Integer>> res) {
        
        res.add(new ArrayList<>(curr));
        for (int i = idx; i < arr.length; i++) {
            
            // skip duplicates
            if (i > idx && arr[i] == arr[i - 1]) continue;

            curr.add(arr[i]);
            helperForSubset(arr, i + 1, curr, res);
            curr.remove(curr.size() - 1);
        }
    }
  
    
}
