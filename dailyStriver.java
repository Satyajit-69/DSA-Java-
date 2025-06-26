import java.util.*;



public class dailyStriver {
   
        public static boolean canMakeEqual(int[] nums, int k) {
            int n = nums.length ;
            int count = 0 ;
            for(int i = 0 ;i<n-1 ;i++) {
                if(nums[i] != nums[i+1] ) {
                    count ++ ;
                }
                
            }
            System.out.println("total count :" + count);
            return count <= k ;
        }
        public int minDays(int[] bloomDay, int m, int k) {
            int n = bloomDay.length;
            if (n < m * k) {
                return -1; // Not enough flowers
            }

            int low = 1;
            int high = findHigh(bloomDay);
            int ans = -1;

            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (possible(bloomDay, mid, m, k)) {
                    ans = mid;
                    high = mid - 1; // try for a better (smaller) day
                } else {
                    low = mid + 1;
                }
            }

            return ans;
        }
        public boolean possible(int[] arr, int days, int m, int k) {
            int count = 0;
            int noOfBouquets = 0;

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] <= days) {
                    count++;
                    if (count == k) {
                        noOfBouquets++;
                        count = 0;
                    }
                } else {
                    count = 0;
                }
            }

            return noOfBouquets >= m;
        }
        public int findHigh(int[] arr) {
        int high = Integer.MIN_VALUE;
        for (int i : arr) {
            if (i > high) {
                high = i;
            }
        }
        return high;
    }


        public static int shipWithinDays(int[] weights, int days) {
            int low = findMax(weights);
            int high = findSum(weights);
            int ans = high;

            while (low <= high) {
                int mid = low + (high - low) / 2;

                if (possible(weights, mid, days)) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return ans;
        }
        public static int findMax(int[] arr) {
            int max = Integer.MIN_VALUE;
            for (int num : arr) max = Math.max(max, num);
            return max;
        }
        public static int findSum(int[] arr) {
            int sum = 0;
            for (int num : arr) sum += num;
            return sum;
        }
        public static boolean possible(int[] weights, int capacity, int days) {
            int currLoad = 0;
            int dayCount = 1;

            for (int weight : weights) {
                if (currLoad + weight > capacity) {
                    dayCount++;
                    currLoad = 0;
                }
                currLoad += weight;
            }

            return dayCount <= days;
        }     
       public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if(m>n) {
            return findMedianSortedArrays(nums2, nums1);}

        int total = m+n;
        int pl = (m+n+1)/2;
        int low = 0;
        int high = m;

        while(low<=high) {
            int mid1 = (low+high)/2;
            int mid2 = pl-mid1;
            int l1 = Integer.MIN_VALUE;
            int l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE; 
            int r2 = Integer.MAX_VALUE;


            if(mid1<m) r1 = nums1[mid1];
            if(mid2<n) r2 = nums2[mid2];
            if(mid1-1>=0) l1 = nums1[mid1-1];
            if(mid2-1>=0) l2 = nums2[mid2-1];
            
            if(l1<=r2 && l2<=r1) {
                if(total%2 == 1) return Math.max(l1,l2);
                return (double)(Math.max(l1,l2) + Math.min(r1,r2))/2;
            } else if (l1>r2) {
                high = mid1-1;
            } else{
                low = mid1+1;
            }
        }
        return 0;
    }




    public static int minmaxDiff(int n) {
        String str = Integer.toString(n);
        char[] maxChars = str.toCharArray();
        char[] minChars = str.toCharArray();

        // Step 1: Find first non-9 digit for max replacement
        char replaceForMax = ' ';
        for (char c : maxChars) {
            if (c != '9') {
                replaceForMax = c; //get the ch to replace
                break;
            }
        }

        // Step 2: Replace that digit with 9 for max
        for (int i = 0; i < maxChars.length; i++) {
            if (maxChars[i] == replaceForMax) {
                maxChars[i] = '9';
            }
        }

        // Step 3: Replace first digit for min with 0
        char replaceForMin = minChars[0];
        for (int i = 0; i < minChars.length; i++) {
            if (minChars[i] == replaceForMin) {
                minChars[i] = '0';
            }
        }

        // Step 4: Convert and calculate result
        int maxVal = Integer.parseInt(new String(maxChars));
        int minVal = Integer.parseInt(new String(minChars));

        return maxVal - minVal;
    }
    

    // User function Template for Java

    public static int aggressiveCows(int[] stalls, int k) {
        // code here
        int n = stalls.length ;
        Arrays.sort(stalls);
        int low = 1  ;
        int high = stalls[n-1]- stalls[0];
        
        int ans = 0 ;
        while (low <= high) {
            int mid = low + (high - low)/2 ;
            if(canPossible(stalls,mid,k)) {
                ans = mid ;
                low = mid+1 ; //try to get better
            }
            else{
                high = mid-1 ;
            }
        }
        return ans ;
    }

    public static boolean canPossible(int arr[] , int minDist , int cows) {
        int n = arr.length ;
        int cowPlaced = arr[0] ;
        int count = 1 ;
        
        for(int i = 1; i<n ; i++){
            if(arr[i] - cowPlaced >= minDist){
                count ++ ;
                cowPlaced = arr[i] ;
            }
            
        }
        return count >= cows ;

    }



//Back-end complete function Template for Java


    
    public static int findPages(int[] arr, int k) {
        // code here
        int n = arr.length ; 
        if(n < k) {
            return -1 ;
        }
        int low = 0 ;
        int high = 0 ;
        for(int i : arr) {
            low = Math.max(low , i) ;
            high += i ;
        }
        int ans = -1 ;
        
        //apply bs
        while(low <= high){
            int mid = low + (high - low) /2 ;
            
            if(Possible(arr,mid,k)) {
                ans = mid ;
                high = mid-1 ; //try to get better
            }
            else{
                low = mid+1 ;
            }
        }
        
        return ans ;
    }
    
    public static boolean Possible(int arr[] , int maxPages , int maxAllocation) {
        int n = arr.length ;
        int currPages = 0;
        int allocation = 1 ;
        
        for(int i = 0 ;i < n ; i++){
            if(arr[i] + currPages <= maxPages){
                 currPages += arr[i] ;
            }else {
                 allocation ++ ;
                 currPages = arr[i] ;
            }
        }
        
        return allocation <= maxAllocation ;
    }

     public static int longestSubsequence(String s, int k) {
            int n = s.length() ;
            
            int count = 0 ;
            int power = 0 ;
            int valueSofar = 0 ;


            //loop
            for(int i = n-1 ; i>= 0 ;i--) {
                if(s.charAt(i) == '0') {
                    //always include
                    count ++ ;
                }else {
                   if (power < 31) { // prevent overflow
                   long add = 1L << power; // 2^power
                        if (valueSofar + add <= k) {
                            valueSofar += add;
                            count++;
                        }
                      }
                   }
                   power++;
                }
            

            return count ;


     }


    public static void main(String[] args) {
       String s = "1001010";
       int  k = 5 ;
    //    System.out.println(aggressiveCows(stalls, k));
    // System.out.println(findPages(stalls,  k));
       System.out.println(longestSubsequence(s, k));
    }

}




