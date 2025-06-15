import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public  class arrays{
   public static boolean distinctelement(int arr[]){
     for(int i =0 ;i<arr.length ;i++){
       for(int j =i+1 ; j<arr.length;j++){
        if(arr[i]== arr[j]){
          return true ;
        }
       }
     }
     return false ;
}
   public static void printarray(int arr []){
    for(int i =0 ;i < arr.length ;i ++){
      System.out.print(arr[i]+" ");
    }System.out.println();
  }
   public static int searchinaRotaitedarray(int array[], int si , int ei  , int target){
    //base case
    if(si>= ei){
      System.out.println("not found :(");
      return -1;}

    //get the mid
    int mid=  (ei-si)+si /2 ;
    if(array[mid] == target){
      return mid ;
    }
    //case -01
     //mid on line 1
     if(array[si]<= array[mid])
     {if( array[si] <= target && target<= array[ mid ]){
     return searchinaRotaitedarray(array, si, mid -1 , target) ;
     } else{
      return searchinaRotaitedarray(array, mid +1 , ei, target);
     }
    }
    //case -02
    else{
      if(array[si]<= target && target <= array[ei]){
    return searchinaRotaitedarray(array, mid +1,ei, target) ;
    }
    else{
      return searchinaRotaitedarray(array,si,mid-1,target);
    }
  }
   
   }
   public static  int[] findMissingAndRepeatedValues(int[][] grid) {
    //create a res array
    int n = grid.length ;
    int res[] = new int[n] ;
    //loop on grid
    for(int i = 0 ; i<grid.length ; i++){
       for(int j = i+1 ; j<grid.length ; j++) {
        if(grid[i][i] == grid[j][j]) {
          res[i] = grid[i][i] ;
        }
       }
    }
    return res;
}
   public static int KadanesAlgo(int[] nums) {
      //Start the sum with first element
      int maxSum = nums[0];
      int currentSum = nums[0];

      for (int i = 1; i < nums.length; i++) {
          currentSum = Math.max(nums[i], currentSum + nums[i]); 
          // either start new or extend
          maxSum = Math.max(maxSum, currentSum); // update global max
      }

      return maxSum;
  }
   public static int maxProfit(int[] prices) {
        int buyPrice  = Integer.MAX_VALUE ;
        int maxProfit = 0 ; 

        for(int i = 0; i<prices.length ;i++){
            if(buyPrice < prices[i]) {
                //Profit happend  :)
                int profit = prices[i] - buyPrice ;
                maxProfit = Math.max(profit,maxProfit) ;
            }
            else {
                //We have to take the loss
                buyPrice = prices[i] ;
            }
         }
      return maxProfit ;
    }
   public static void Leaders(int arr[]) {
    ArrayList<Integer> res = new ArrayList<>();
    for (int i = arr.length - 1; i >= 0; i--) {
        if (arr[i] == rightMost(arr, i)) {
            res.add(arr[i]);
        }
    }

    // Reverse the result to maintain left-to-right order
    Collections.reverse(res);
    for (int i : res) {
        System.out.print(i + " ");
    }
}
   public static int rightMost(int arr[], int idx) {
    int max = Integer.MIN_VALUE; 
    for (int i = idx; i < arr.length; i++) {
        max = Math.max(max, arr[i]);
    }
    return max;
}
   public static void Leaders2(int arr[]) {
        ArrayList<Integer> res = new ArrayList<>();
        int maxFromRight = Integer.MIN_VALUE;

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] >= maxFromRight) {
                res.add(arr[i]);
                maxFromRight = arr[i];
            }
        }

        Collections.reverse(res);
        for (int val : res) {
            System.out.print(val + " ");
        }
     }
   public static int[][] merge(int[][] intervals) {
      ArrayList<int[]> res = new ArrayList<>();

      // Sort the array based on the start times
      Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

      // Initialize the start and end of the first interval
      int start = intervals[0][0];
      int end = intervals[0][1];

      // Loop through the rest of the intervals
      for (int i = 1; i < intervals.length; i++) {
          int currStart = intervals[i][0];
          int currEnd = intervals[i][1];

          if (end >= currStart) {
              // Merge the intervals
              end = Math.max(end, currEnd);
          } else {
              // Add the previous interval and update start and end
              res.add(new int[]{start, end});
              start = currStart;
              end = currEnd;
          }
      }

      // Add the last interval
      res.add(new int[]{start, end});
      return res.toArray(new int[res.size()][]);
  }
  public  static int findTarget(int arr[], int target) {
    int n = arr.length;
    int si = 0;
    int ei = n - 1;

    while (si <= ei) {
        int mid = si + (ei - si) / 2;

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            si = mid + 1;
        } else {
            ei = mid - 1;
        }
    }

    // If the target is not found, return -1
    return -1;
}
  
  
  public static int Prefixsum (int arr[]) {
    int n = arr.length ;
    int Max = Integer.MIN_VALUE ; //-infinity
    int currs = 0 ;
    //prefix 
    int prefix[] = new int[arr.length] ;
    

    //fill the prefix array
    for(int i  = 1 ;i<n ;i++) {
       prefix[i] = arr[i] + prefix[i-1] ;
    }


    //loop
    for(int i = 0; i<n ;i++) {
      int start = i ;
      for(int j = 1 ; j<n ;j++) {
        int end = j ;
        
        //index bound case
        if(start == 0) {
          currs = prefix[end] ;
        }
        //calculate the sum 
        currs = prefix[end] - prefix[start] ;
        

        Max = Math.max(currs, Max) ;

      }
    } 
    return Max ;
  }
  
 
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            int product = 1;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    product *= nums[j];  // Fix: use nums[j] instead of j
                }
            }
            res[i] = product;
        }

        return res;
    }

public static void main(String[] args) {   
        int nums[] = {1,2,3} ;
        int arr[] = productExceptSelf(nums) ;
        for(int i : arr) {
          System.out.print( i +" ");
        }
   }  
  
  }





