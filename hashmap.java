
// import java.lang.reflect.Array;
import java.util.*;

public class hashmap {
  public static void main(String[] args) {

    int arr[] = {5,3,6,1,12};
    // System.out.println(longestConsecutive(arr));
    System.out.println(findFinalValue(arr, 3));

  }

  public static boolean ValidAnagram(String s, String t) {
    HashMap<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
    }
    // loop on t
    for (int i = 0; i < t.length(); i++) {
      char ch = t.charAt(i);
      if (map.get(ch) != null) {
        if (map.get(ch) == 1) {
          map.remove(ch); // remove from map
        } else {
          map.put(ch, map.get(ch) - 1); // decrease by 1
        }
      } else {
        return false;
      }
    }
    if (map.isEmpty()) {
      return true;
    }
    return false;
  }

  public static void majorityelement(int arr[]) {
    //
    int nums;
    HashMap<Integer, Integer> hm = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
      nums = arr[i];
      // conatains key
      if (hm.containsKey(nums)) {
        hm.put(nums, hm.get(nums) + 1);
      } else {
        hm.put(nums, 1);
      }
    }
    Set<Integer> keyset = hm.keySet();
    for (Integer key : keyset) {
      if (hm.get(key) > arr.length / 3) {
        System.out.println(key);
      }
    }

  }

  public static List<List<Integer>> findTriplets(int[] arr) {
    List<List<Integer>> res = new ArrayList<>();
    int n = arr.length;

    for (int i = 0; i < n - 2; i++) {

      int target = -arr[i]; // first element
      HashMap<Integer, Integer> map = new HashMap<>();

      for (int j = i + 1; j < n; j++) {

        int needed = target - arr[j]; // second element

        if (map.containsKey(needed)) {

          int k = map.get(needed); // index of 3rd element

          List<Integer> triplet = Arrays.asList(i, j, k);
          Collections.sort(triplet);
          res.add(triplet);
        }

        map.put(arr[j], j); // store value → index
      }
    }

    return res;
  }

  // Function to return length of longest subsequence of consecutive integers.
  public static int longestConsecutive(int[] arr) {
    Set<Integer> set = new HashSet<>();
    for (int num : arr)
      set.add(num);

    int longest = 0;

    for (int num : set) {
      // check if it is the start of a sequence
      if (!set.contains(num - 1)) {
        int current = num;
        int count = 1;

        while (set.contains(current + 1)) {
          current++;
          count++;
        }
        longest = Math.max(longest, count);
      }
    }
    return longest;
  }

  public static int findFinalValue(int[] nums, int original) {

    Set<Integer> set = new HashSet<>();

    for (int i : nums) {
      set.add(i);
    }

    while (set.contains(original)) {
        original *= 2 ;
    }

    return original;
  }


  
}
