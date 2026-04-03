import java.util.*;

class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int res[] = new int[rains.length];
        Arrays.fill(res, 1);
        // key - > lakes valu -> dry days
        HashMap<Integer, Integer> fullLakes = new HashMap<>();
        TreeSet<Integer> dryDays = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            if (rains[i] > 0) {
                int lake = rains[i];

                if (fullLakes.containsKey(lake)) {
                    // It rained before → check if we can dry it before today
                    Integer dryDay = dryDays.higher(fullLakes.get(lake));
                    if (dryDay == null)
                        return new int[0]; // flood!

                    res[dryDay] = lake; // dry that lake on dryDay
                    dryDays.remove(dryDay);
                }

                fullLakes.put(lake, i); // mark lake as filled today
                res[i] = -1;
            } else {
                dryDays.add(i); // available dry day
                res[i] = 1; // placeholder
            }
        }

        return res;

    }
    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int[] pairs = new int[n];

        // Sort potions only once
        Arrays.sort(potions);

        for (int i = 0; i < n; i++) {
            int currSpell = spells[i];
            pairs[i] = helper(currSpell, success, potions);
        }

        return pairs;
    }
    // Helper to find number of successful potions for a single spell
    public static int helper(int spell, long success, int[] potions) {
        int m = potions.length;
        int low = 0, high = m - 1;
        int firstValid = m; // store first valid potion index

        while (low <= high) {
            int mid = low + (high - low) / 2;
            long product = (long) spell * potions[mid];

            if (product >= success) {
                firstValid = mid;
                high = mid - 1; // look for smaller index
            } else {
                low = mid + 1;
            }
        }

        return m - firstValid; // total successful potions
    }
    public static int findMin(int[] arr) {
        // complete the function here
        int res = Integer.MAX_VALUE;
        for (int i : arr) {
            res = Math.min(res, i);
        }

        return res;
    }
    public static int search(int[] arr, int key) {
        return helper(arr, 0, arr.length - 1, key);
    }
    public static int helper(int arr[], int si, int ei, int key) {
        int n = arr.length;
        if (si > ei) {
            return -1;
        }

        // get the mid
        int mid = si + (ei - si) / 2;

        if (arr[mid] == key) {
            return mid;
        }
        // searching on line1
        if (arr[si] <= arr[mid]) {
            // left search

            if (arr[si] <= key && key <= arr[mid]) {
                return helper(arr, si, mid - 1, key);
            } else {
                return helper(arr, mid + 1, ei, key);
            }

        } else {
            if (arr[mid] <= key && key <= arr[ei]) {
                return helper(arr, mid + 1, ei, key);
            } else {
                // case b ~> left search
                return helper(arr, si, mid - 1, key);
            }
        }

    }
    public static int maximumEnergy(int[] energy, int k) {
        int n = energy.length;

        if (n == 1) {
            return energy[0];
        }

        // intialization
        int dp[] = new int[n];
        Arrays.fill(dp, 0);
        // here dp [i] means maximum energy form idx i

        for (int i = n - 1; i >= 0; i--) {
            int next = (i + k < n) ? dp[i + k] : 0;
            dp[i] = energy[i] + next;
        }

        return maximum(dp);

    }
    public static int maximum(int arr[]) {
        int maxEle = Integer.MIN_VALUE;
        for (int i : arr) {
            maxEle = Math.max(maxEle, i);
        }
        return maxEle;
    }
    public static int peakElement(int[] arr) {
        // code here
        int n = arr.length;
        int peakEle = Integer.MIN_VALUE;
        for (int i = 1; i < n - 1; i++) {
            int prevVal = arr[i - 1];
            int nextVal = arr[i + 1];

            if (arr[i] > prevVal && arr[i] > nextVal) {
                peakEle = Math.max(peakEle, arr[i]);
            }
        }

        return peakEle;
    }
    public static List<String> removeAnagrams(String[] words) {
        List<String> res = new ArrayList<>();
        String prev = "";

        for (String word : words) {
            char[] ch = word.toCharArray();
            Arrays.sort(ch);
            String sorted = new String(ch);

            if (!sorted.equals(prev)) {
                res.add(word);
                prev = sorted;
            }
        }

        return res;
    }
    public static int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        // 1. Edge Case: If needle is empty, return 0 (as per problem spec)
        if (m == 0) {
            return 0;
        }

        // 2. Iterate through all possible starting positions in haystack
        // The last possible starting index is n - m
        for (int i = 0; i <= n - m; i++) {

            // At index 'i' in haystack, check if the substring matches the needle
            int j;
            for (j = 0; j < m; j++) {
                // Check character by character
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    // Mismatch found, break the inner loop and check the next starting position
                    // (i+1)
                    break;
                }
            }

            // 3. Check for Full Match:
            // If the inner loop completed without a break (i.e., j reached m),
            // a full match was found.
            if (j == m) {
                return i; // Return the starting index
            }
        }

        // 4. If the loop completes without finding a match, return -1
        return -1;
    }
    public static boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        if (n < 2 * k)
            return false;
        for (int i = 0; i + 2 * k - 1 < n; i++) {
            if (isIncreasing(nums, i, i + k) && isIncreasing(nums, i + k, i + 2 * k)) {
                return true;
            }
        }

        return false;
    }
    private static boolean isIncreasing(List<Integer> nums, int start, int end) {
        for (int i = start; i < end - 1; i++) {
            if (nums.get(i) >= nums.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
    public static long maximumHappinessSum(int[] happiness, int k) {
        int n = happiness.length;

        // sort the happiness
        Arrays.sort(happiness);
        int i = n - 1;
        long gatheredH = 0;
        int decrease = 0;
        while (k > 0 && i >= 0) {
            int curr = happiness[i] - decrease; // check after reducing
            // take only postive
            if (curr > 0) {
                gatheredH += curr; // selected
            }
            // update
            decrease++;
            i--;
            k--;
        }
        return gatheredH;
    }
    public static int bestClosingTime(String customers) {
        int n = customers.length();
        int penality = 0;

        // Assuming closed at 0th hour
        for (char ch : customers.toCharArray()) {
            if (ch == 'Y') {
                penality++;
            }
        }
        int minimumPenality = penality;
        int res = 0;

        for (int i = 0; i < n; i++) {
            if (customers.charAt(i) == 'Y') {
                penality--;
            } else {
                penality++;
            }

            if (penality < minimumPenality) {
                minimumPenality = penality;
                res = i + 1; // due to 0 base indexing
            }

        }

        return res;

    }
    public static boolean trionic(int[] nums) {
        int n = nums.length;
        if (n < 3)
            return false;

        int p = -1, q = -1;

        // find end of first increasing
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                p = i + 1;
            } else {
                break;
            }
        }

        // must have increasing part
        if (p <= 0)
            return false;

        // find end of decreasing starting from p
        for (int i = p; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                q = i + 1;
            } else {
                break;
            }
        }

        // must have decreasing part
        if (q <= p)
            return false;

        // remaining must be increasing
        return increase(nums, 0, p)
                && decrease(nums, p, q)
                && increase(nums, q, n - 1);
    }
    public static boolean increase(int nums[], int start, int end) {

        for (int i = start; i < end; i++) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }

        return true;
    }
    public static boolean decrease(int nums[], int start, int end) {

        for (int i = start; i < end; i++) {
            if (nums[i] < nums[i + 1]) {
                return false;
            }
        }
        return true;
    }
    public static long maxsumTrionic(int nums[]) {
        // assurity -> one subarray of trionic array exists
        int n = nums.length;

        if (n < 3) {
            return 0;
        }

        // initialization
        int dp[] = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }

        int i = 0;
        long maxSum = Long.MIN_VALUE;

        while (i < n - 2) {
            int start = i;

            // findout p
            while (i + 1 < n && nums[i] < nums[i + 1]) {
                i++;
            }

            int p = i;

            if (p == start) {
                i++;
                continue;
            }

            // find out q
            while (i + 1 < n && nums[i] > nums[i + 1]) {
                i++;
            }

            int q = i;

            if (q == p) {
                continue;
            }

            while (i + 1 < n && nums[i] < nums[i + 1]) {
                i++;
            }

            int end = i;

            while (i + 1 < n && nums[i] < nums[i + 1]) {
                i++;
                long currSum = dp[i] - (start > 0 ? dp[start - 1] : 0);
                if (currSum > maxSum) {
                    maxSum = currSum;
                    end = i;
                }
            }

        }

        return maxSum;

    }
    public static List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>() ;
        int n = nums.length ;
        if(n==0){
            return res ;
        }
        int first = nums[0] ;
        //track 
        int j = nums[0] ;
        for(int i = 1 ; i < n ; i++) {
            if(nums[i] == j + 1) { //next number is present
               j = nums[i];
            }
            else{
                if(first == j) {
                    res.add(String.valueOf(first));
                }
                 else{
                    //next number is not present
                    res.add(Integer.toString(first) + "->"+Integer.toString(j));
                }
               
                first = nums[i];
                j  = nums[i]  ;
           }
            
        }

        //add remaining element
        if(first==j) {
            res.add(String.valueOf(first));
        }
        else{
            res.add(first+"->"+j);
        }
       
        return res ;


     }
    public static boolean check_elements(int arr[], int n, int A, int B) {
    //   Arrays.sort(arr) ;
      
      Set<Integer> seen = new HashSet<>() ;
      for(int i : arr) {
        seen.add(i) ;
      }


      for(int i = A ; i <= B ; i ++) {
        if(!seen.contains(i)){
          return false ;
        }
      }
      return true;
}


    public static void main(String[] args) {

            int nums[] = {0,2,3,4,6,8,9};
            int n = 7, A = 2, B = 9, arr[] =  {1, 4, 5, 2, 7, 8, 3} ;
            System.out.println(check_elements(arr, n, A, B));

            // boolean res = trionic(nums);
            // System.out.println(res);
            // System.out.println(maxsumTrionic(nums));
            // System.out.println(summaryRanges(nums));
        }

}
