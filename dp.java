import java.util.* ;

public class dp {
    public static int fib(int n , int f[]) {
        if(n == 0 || n==1) {
            return n ;
        }
        if(f[n] != 0) {
            //fib n already calculated
            return f[n] ;
        }
        f[n] = fib(n-1 ,f) + fib(n-2,f) ;
        return f[n] ;
    }
    public static int fibtabulation(int n) {
        int dp [] = new int[n+1] ;
        dp[0] = 0; 
        dp[1] = 1 ; //initialization

        for(int i = 2 ;i<=n ;i++) {
             dp[i] = dp[i-1] + dp[i-2] ;  //meaning
        }
        
        return dp[n] ;
    }
    
    public static int countwaysMemo(int n ,int ways[]) { //top down approach recursion + memoization
        if(n == 0) {
            return 1 ;
        }
        if(n<0) {
          return 0 ;
        }
        if(ways[n] != -1) {//already calculated
           return  ways[n] ;
        }
        ways[n] = countwaysMemo(n-1 ,ways) + countwaysMemo(n-2 ,ways) ;

        return ways[n] ;
    }
    static int countwaysTab(int n) {
        int dp []= new int[n+1] ;
        dp[0] = 1 ;


        for(int i =1 ;i<=n ;i++){
            //case for 1 (negative)
            if(i == 1) {
                dp[i] = dp[i-1]  + 0 ;
            }else{
                dp[i] = dp[i-1] +dp[i-2] ;
            }
          
        }

        return dp[n] ;
    }
   
    public static int knapsackRecursion(int val[],int wt[], int W , int n) {
        if(W == 0 || n == 0) {
            return 0 ;
        }

        if(wt[n-1] <= W) { //valid
           //include
          int ans1 =  val[n-1] + knapsackRecursion(val, wt, W-wt[n-1], n-1) ;
           //exclude
          int ans2 = knapsackRecursion(val, wt, W, n-1) ;
          return Math.max(ans1, ans2) ;
        }
        else{
            return knapsackRecursion(val, wt, W, n-1) ;
        }
    }     
    public static int knapsackMemoization(int val[],int wt[], int W , int n ,int dp[][]) {
        if(W == 0 || n == 0) {
            return 0 ;
        }
        if(dp[n][W] != -1) {
            return dp[n][W] ;
        }

        if(wt[n-1] <= W) { //valid
           //include
          int ans1 =  val[n-1] + knapsackRecursion(val, wt, W-wt[n-1], n-1) ;
           //exclude
          int ans2 = knapsackRecursion(val, wt, W, n-1) ;
          dp[n][W] = Math.max(ans1, ans2) ;
          return dp[n][W] ;
        }
        else{
            dp[n][W] = knapsackRecursion(val, wt, W, n-1) ;
            return  dp[n][W] ;
        }
    }     
    public static int knapsackTab(int val[],int wt[] ,int W) {
        int n = val.length ;
        int dp[][] = new int[n+1][W+1] ; //dp array
        for(int i = 0; i<dp.length ;i++){ // Oth col
            dp[i][0] = 0 ;
        }
        for(int j = 0; j<dp[0].length ;j++){ // Oth row
            dp[0][j] = 0 ;
        }

        for(int i =1 ; i<n+1 ; i++){
            for(int j =1 ;j<W+1 ;j++){

                int v = val[i-1] ;
                int w = wt[i-1] ;
                
                if(w <= j) { //valid
                        int incProfit = v + dp[i-1][j-w] ;   //include
                        int excProfit = dp[i-1][j] ;         //exclude
                        dp[i][j] = Math.max(incProfit, excProfit) ; //comapare
                }
                else { //Invalid
                        int excProfit = dp[i-1][j] ; //exclude
                        dp[i][j] = excProfit ;
                }
            }
        }
        //printdp(dp);
        return dp[n][W] ; //return the array element having n of ele and W no of weight
        
    }
   
    static void printdp(int dp[][]) {
        for(int i = 0; i<dp.length ; i++){
            for(int j = 0; j<dp[0].length ;j++){
                System.out.print(dp[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static boolean targetSum(int arr[] , int targetSum) {
        int sum = targetSum ;
        int n  = arr.length ;
        boolean dp[][] = new boolean[n+1][sum+1] ;
        //i -> items  j -> targetsum
        for(int i = 0; i < n+1 ;i++){
            dp[i][0] = true ;
        }

        for(int i = 1 ;i < n+1 ;i++){
            for(int j = 1 ;j < sum+1 ;j++){
                    int v = arr[i-1] ; //getting the value
                    //include
                    if(v <= j && dp[i-1][j-v] == true) {
                        dp[i][j] = true ;
                    }
                    //exclude
                    else if(dp[i-1][j] == true) {
                    dp[i][j] = true ;
                    }
            }
        }
        //Aprintdp(dp);
        return dp[n][sum] ;
    }
   

    public static int unboundedKnapsack(int val[] ,int wt[] , int W) {
        int n = val.length ;
        int dp[][] = new int[n+1][W+1] ;
       

        //Initialization
            for(int i = 0; i < n+1  ;i++ ){ // 0th col
            dp[i][0] = 0 ;
            }

            for(int j = 0 ; j< W+1 ;j++){ // 0th row
            dp[0][j] = 0 ; 
            }

            
        for(int i =1 ;i<n+1 ;i++){
            for(int j = 1 ;j<W+1 ;j++){
                if(wt[i-1] <= j) {
                    dp[i][j]  = Math.max(val[i-1] + dp[i][j- wt[i-1]], dp[i-1][j]) ;
                }
                else{
                    dp[i][j]  = dp[i-1][j] ;
                }
            }
        }
        printdp(dp);
        return dp[n][W] ;
}      
    //house robber problem
        //1 - recursion O(2^n)
            public int rob(int[] nums) {
                if(nums.length == 0){
                    return  0 ;
                }

                return helper(nums,nums.length -1) ;
            }
            public int helper(int nums[] , int idx) {
            if(idx == 0) { //only 1 house can be robbed
                return nums[idx] ;
            }
            
            if(idx == 1) {
                return Math.max(nums[1] ,nums[0]) ;
            }

            int robb_curr = nums[idx] + helper(nums,idx - 2) ; //include
            int robb_not = helper(nums,idx - 1) ; //exclude

            return Math.max(robb_curr,robb_not) ;

        }
        //2 - memoization O(n)
            public int rob_memo(int[] nums) {
                if(nums.length == 0){
                    return  0 ;
                }
                int dp[] = new int [nums.length ] ;
                Arrays.fill(dp,-1) ;
                return helper(nums,nums.length -1 ,dp) ;
            }
            public int helper(int nums[] , int idx,int dp[]) {
            if(idx == 0) { //only 1 house can be robbed
                return nums[idx] ;
            }
            
            if(idx == 1) {
                return Math.max(nums[1] ,nums[0]) ;
            }
            
            if(dp[idx] != -1) {
                return dp[idx] ;
            }
            int robb_curr = nums[idx] + helper(nums,idx - 2,dp) ; //include
            int robb_not = helper(nums,idx - 1,dp) ; //exclude

            dp[idx] = Math.max(robb_curr,robb_not) ;
            return dp[idx] ;
        }
        //3 - Tabulation O(n)
            public int rob_Tab(int nums[]) {
                if(nums.length ==1) {
                    return nums[0] ;
                }
                int dp[] = new int[nums.length] ; 
                dp[0] = nums[0] ;
                dp[1] = Integer.max(nums[1],nums[0]) ;

                for(int i = 2 ;i < nums.length; i++){
                    dp[i] = Math.max(nums[i] + dp[i-2] ,dp[i-1]) ;
                }

                return dp[nums.length -1] ;
            }
            

    
     public static int  Coinchange(int val[] ,int sum) {
        int W = sum  ;
        int n = val.length ;
        int dp[][] = new int[n+1][W+1] ;

        //initialization
        for(int i =1   ;i<n+1 ; i++){
             dp[i][0] = 1 ; //Oth col // sum is 0 i.e one way possible (empty)
        }

        for(int i =1 ;i<W+1 ;i++){
            dp[0][i] = 0 ; //Oth row -> 0 item
        }



        for(int i = 1; i<n+1 ;i++){
            for(int j =1 ; j<W+1 ;j++) {
                if(val[i-1] <= j) { //Valid
                       dp[i][j] = dp[i][j-val[i-1]] + dp[i-1][j] ;
                }
                else{ //invalid
                    dp[i][j]= dp[i-1][j] ; //exclude
                }
            }
        }
        printdp(dp);
        return dp[n][W] ;
     } 
     public static int rodcutting(int length[], int prices[],int totalRod) {
        int W = totalRod ;
        int n = prices.length ;
        int dp[][] = new int[n+1][W+1] ;

        //initialization
        for(int i = 1 ; i<n+1 ;i++) { //Oth col
           for(int j =1 ; j<W+1 ;j++) {
            if(i == 0 || j==0) {
                dp[i][j] = 0; 
            }
           }
        }
        

        //filling
        for(int i = 1 ;i<n+1 ;i++){
            for(int j = 1 ;j<W+1 ;j++) {
                //Valid
                if(length[i-1] <= j) {
                  dp[i][j] = Math.max(prices[i-1]+dp[i][j-length[i-1]] ,dp[i-1][j]) ;
                }
                else{
                    //Invalid
                dp[i][j] = dp[i-1][j] ; //exclude
                }
                
            }
        }
        
        printdp(dp);
        return dp[n][W] ;
    }
     //lcs -> longest common subsequence
     public static int LCS(String s1 ,String s2 ,int n,int m) {
      
        //base case
        if(m  == 0  || n == 0) {
            return 0 ;
        }

        if(s1.charAt(n-1) == s2.charAt(m-1))  {
            return LCS(s1, s2,n-1,m-1) +1 ;
        }
        else{
            int ans1 = LCS(s1, s2, n-1, m) ;
            int ans2 = LCS(s1, s2, n, m-1) ;

            return Math.max(ans1, ans2) ;
        }

     }
     public static int LcsMemo(String s1 ,String s2 ,int n,int m ,int dp[][]) {
        if(n == 0 || m == 0) {
            return 0 ;
        }
        if(dp[n][m] != -1) { //answer exist
            return dp[n][m]  ;
        }
        

        if(s1.charAt(n-1) == s2.charAt(m-1))  {
            return dp[n][m] = LcsMemo(s1, s2,n-1,m-1,dp) +1 ;
        }
        else{
            int ans1 = LcsMemo(s1, s2, n-1, m,dp) ;
            int ans2 = LcsMemo(s1, s2, n, m-1,dp) ;

            return dp[n][m] = Math.max(ans1, ans2) ;
        }
     }
   


     public static int lcsTab(String s1 ,String s2) {
                //creation
                int n = s1.length() ;
                int m = s2.length() ;

                int dp[][] = new int[n+1][m+1] ;
                //meaning
                  //dp(i,j) means lcs of string 1 of i length and string 2 of j length
                //initialazation
                for(int i = 1; i<n+1 ;i++){
                    for(int j=1 ;j<m+1 ;j++ ){
                        if(i == 0 || j == 0) {
                            dp[i][j] = 0 ;
                        }
                    }
                }
                //filling up

                for(int i =1 ;i<n+1 ;i++){
                    for(int j = 1 ;j<m+1 ;j++){
                        if(s1.charAt(i-1) == s2.charAt(j-1)) {
                            dp[i][j] = dp[i-1][j-1] +1 ;
                        }
                        else{
                            int ans1 = dp[i-1][j] ;
                            int ans2 = dp[i][j-1] ;
                            dp[i][j] = Math.max(ans1,ans2) ;
                        }
                    }
                }
                return dp[n][m] ;
             }
     public static int LongestCommonSubsequence(String s1,String s2) {
                int n = s1.length(); 
                int m = s2.length();

                int dp[][] = new int[n+1][m+1] ;
                int ans = 0 ;
                //initialization
                for(int i = 1 ; i<n+1 ;i++) {
                    for(int j = 1 ;j<m+1 ;j++) {
                        if(i == 0 || j == 0) {
                            dp[i][j] = 0 ;
                        }
                    }
                }
                //filling up
                for(int i = 1 ;i<n+1 ;i++){
                    for(int j = 1 ;j<m+1 ;j++) {
                         if(s1.charAt(i-1) == s2.charAt(j-1)) { //same
                          dp[i][j] = dp[i-1][j-1] + 1 ;
                          ans = Math.max(ans, dp[i][j]) ;
                        }
                        else{ //different
                            dp[i][j] = 0 ;

                        }
                    }
                }
                return ans ;
             }
     static int LIS(int arr1[]) {
        //stores unique elements
        HashSet<Integer> hm = new HashSet<>() ;
        for(int i = 0 ; i<arr1.length ;i++){
            hm.add(arr1[i]) ; ///add the elements
        }

        //arr2 -> contains only unique elements
        int arr2[] = new int[hm.size()] ; 
        int i = 0 ;
        for(int num : hm) { //for each loop
          arr2[i] = num ; //copy
          i++ ;
        }
        
        Arrays.sort(arr2); //sort 
        return LCSforArray(arr1,arr2) ;

    }
     static int LCSforArray(int arr1[],int arr2[]) {
        int n = arr1.length ;
        int m = arr2.length ;
         int dp[][] = new int[n+1][m+1] ;
         //initialization
         for(int i = 1 ; i<n+1 ;i++) {
            for(int j = 1 ;j<m+1 ;j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0 ;
                }
            }
        }

         //filling up -> bottom up manner
         for(int i =1 ;i<n+1 ;i++){
            for(int j = 1 ;j<m+1 ;j++){
                if(arr1[i-1] == arr2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] +1 ;
                }
                else{
                    int ans1 = dp[i-1][j] ;
                    int ans2 = dp[i][j-1] ;
                    dp[i][j] = Math.max(ans1,ans2) ;
                }
            }
        }
        return dp[n][m] ;
      }
    
     static int editDistance(String s1, String s2) {
            int n = s1.length() ;
            int m = s2.length() ;


            int dp[][] = new int[n+1][m+1] ;
            //Initialization
            for(int i = 0 ;i<n+1 ;i++){
                for(int j= 0 ;j<m+1 ;j++){
                    //case 1 
                    if(i == 0){
                        dp[i][j] = j  ;
                    }
                    //case 2
                    if(j==0) {
                        dp[i][j] = i ;
                    }
                }
            }

            //bottom up 
            for(int i = 1 ;i<n+1 ;i++){
                for(int j= 1 ;j<m+1 ;j++){
                    //case 1  smae
                    if( s1.charAt(i-1)== s2.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1] ; 
                    }
                    else{
                        int add = dp[i][j-1] +1 ;
                        int del = dp[i-1][j] +1;
                        int rep = dp[i-1][j-1] +1;

                        dp[i][j] = Math.min(add, Math.min(del, rep)) ;
                    }
                }
            }

            return dp[n][m] ;

        }
 
     public static boolean isMatch(String s,String p) {
            //Hard level problem
           int n = s.length() ;
           int m = p.length() ;
           boolean dp[][]= new boolean[n+1][m+1] ;


           //initailize
           dp[0][0] = true ;

           for(int i = 1 ;i < n+1 ; i++){ // p = " "
              dp[i][0] = false ;
           }
           //s = ""
           for(int j = 1 ; j < m+1 ; j++) {
                if(p.charAt(j-1) == '*') {
                    dp[0][j] = dp[0][j-1] ;
                }
           }

           //bottom up
           for(int i =1 ;i<n+1 ;i++){
              for(int j =1 ;j<m+1 ;j++){
                //case -> 
                    if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?' ){
                        dp[i][j] = dp[i-1][j-1] ;
                    }else if(p.charAt(j-1) == '*') {
                        dp[i][j] = dp[i-1][j] || dp[i][j-1] ;
                    }else{
                        dp[i][j] = false ;
                    }
             }
           }
             //string -> n and pattern -> m
             return dp[n][m] ;
        }
     public static int CatlanR(int n){
            if(n == 0 || n==1) {
                return 1 ;
            }
            int ans = 0 ; 
            for(int i = 0 ; i<= n-1 ;i++){
                ans+= CatlanR(i) * CatlanR(n-i-1) ;
            }

            return ans ;
        }
     public static int CatlanM(int n ,int dp[]){
            if(n == 0 || n==1) {
                return 1 ;
            }
            if(dp[n] != -1) {
                return dp[n] ;
            }
            int ans = 0 ;
            for(int i = 0 ; i<= n-1 ;i++){
                ans+= CatlanM(i, dp) * CatlanM(n-i-1, dp) ;
            }

            return dp[n]= ans ;
        }
     public static int catlanTab(int n) {
            int dp[] = new int[n+1] ;
            dp[0] = 1;
            dp[1] = 1;

            for(int i =2 ; i<=n ; i++) {
                for(int j = 0 ;j<i;j++) {
                    dp[i]  += dp[j] * dp[i-j-1] ;
                }
            }

            return dp[n] ;
        } 
     public static int countBst(int n) {
            int dp[] =  new int[n+1] ;
            dp[0]= 1;
            dp[1]= 1;
            for(int i = 2 ;i<n+1;i++){
                
                for(int j =0 ; j<i ;j++) {
                    int left  = dp[j] ;
                    int right = dp[i-j-1] ;
                    dp[i] += left * right ;
                }
            }

            return dp[n] ;
        }
      

     public static int longestPalindromicSubsequenceT(String s) {
            int n = s.length();
            int dp[][] = new int[1000][1000];
            
    
            // Every single character is a palindrome of length 1
            for (int i = 0; i < n; i++) {
                dp[i][i] = 1;
            }
    
            // Checking substrings of length 2 separately
            for (int i = 0; i < n - 1; i++) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    dp[i][i + 1] = 2;
                } else {
                    dp[i][i + 1] = 1;
                }
            }
    
            // Checking substrings of length >= 3
            for (int l = 2; l <= n; l++) {
                for (int i = 0; i <= n - l; i++) {
                    int j = i + l - 1;
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = 2 + dp[i + 1][j - 1];
                    } else {
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    }
                }
            }
    
            return dp[0][n - 1];

        }
     public static boolean partitionEqualstoSubset(int nums[]) {
        int n = nums.length;
        int sum = 0; 

        // Calculate the total sum of array
        for (int num : nums) {
            sum += num;
        }

        // If sum is odd, partition is not possible
        if (sum % 2 != 0) {
            return false;
        }

        int subset = sum / 2;
        boolean dp[] = new boolean[subset + 1];
        dp[0] = true; // Base case: sum 0 is always possible

        // Iterating through each number
        for (int num : nums) {
            // Traverse from back to avoid using the same element multiple times
            for (int j = subset; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }

        return dp[subset];
     }
     public static int mcm(int arr[],int i,int j) {
        if(i == j ) {
            return 0 ; //single matrix case
        }
        int ans  = Integer.MAX_VALUE ;
        for(int k = i ; k < j ;k++){
            int c1 = mcm(arr, i, k) ; //Ai .. Ak
            int c2 = mcm(arr, k+1, j) ; //Ak ... Aj
            int c3 = arr[i-1] * arr[k] * arr[j] ;
            int finalC = c1+c2+c3 ;

            ans = Math.min(ans, finalC) ;
        }

        return ans ;
      
    } 
     public static int mcmMemo(int arr[],int i,int j,int dp[][]) {
        if(i == j ) {
            return 0 ; //single matrix case
        }
        if(dp[i][j] != -1) {
            return dp[i][j] ;
        }
        int ans  = Integer.MAX_VALUE ;
        for(int k = i ; k < j ;k++){
            int c1 = mcm(arr, i, k) ; //Ai .. Ak
            int c2 = mcm(arr, k+1, j) ; //Ak ... Aj
            int c3 = arr[i-1] * arr[k] * arr[j] ;
            int finalC = c1+c2+c3 ;

            ans = Math.min(ans, finalC) ;
        }

        return dp[i][j] = ans ;
      
    } 
    
     public static int mcmTab(int arr[]) {
        int n= arr.length ;
        int dp[][] = new int[n][n] ;

        //initialization
        for(int i = 0; i<n ;i++) {
            dp[i][i] =  0; 
        }

        //filling up
        for(int len = 2 ; len <= n-1 ;len++) {
            for(int i= 1 ;i<= n-len ;i++) {
                int j = i+ len -1 ;
                dp[i][j] = Integer.MAX_VALUE ;
                for(int k = i ;k<= j-1 ;k++) {
                    int cost1 = dp[i][k] ;
                    int cost2 = dp[k+1][j] ;
                    int cost3 = arr[i-1] * arr[k] * arr[j] ;

                    dp[i][j] = Math.min(dp[i][j], cost1 + cost2 + cost3) ;
                }
            }
        }
        printdp(dp);
        return dp[1][n-1] ; //final answer (ABCD)
    }   
     public static int minPartition(int nums[]) {
        int n = nums.length ;
        int sum = 0; 
        for(int i : nums) {
            sum += i ;
        }

        int W = sum / 2 ;

        int dp[][] = new int[n+1][W+1] ;

        //bottom up
        for(int i= 1 ; i<n+1 ;i++) {
            for(int j =1 ;j<W+1 ;j++) {
                if(nums[i-1] <= j) {//Valid
                   dp[i][j] = Math.max(nums[i-1]+dp[i-1][j-nums[i-1]]  , dp[i-1][j]); //include and exclude
                }
                else { //exclude
                    dp[i][j] = dp[i-1][j] ; 
                }
            }
        }

        int sum1 = dp[n][W] ;
        int sum2 = sum - sum1 ;
        return Math.abs(sum2-sum1) ;
    }
   
 public static int minJumps(int nums[]) {
    int n = nums.length ;
    //table
    int dp[] = new int[n] ;
    Arrays.fill(dp, -1);
    //Initialization

    dp[n-1] = 0 ;
    for(int i = n-2 ; i >= 0; i--) {

        int steps = nums[i] ; //steps
        int ans = Integer.MAX_VALUE ;
        
        for(int j = i+1 ; j<= i+steps && j<n ;j++) {
            if(dp[j] != -1) { //valid answer 
                ans = Math.min(ans, dp[j]+1) ;
            }
        }

        if(ans != Integer.MAX_VALUE) {
            dp[i] = ans ;
        }
    }

    return dp[0] ;
 }
 

 //Assignment  questions
  
 //1 -> tribonacci series
       public static  void NthTribonacci(int n) {
            if( n < 0 ){
                return ;
            }

            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 0;
            dp[2] = 1;

            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            }
            
            for(int i  =0 ; i<n ;i++) {
                System.out.print( dp[i] + " ");
            }
        }
//2-> Generate all valid paranthesis
       static void GenerateValidParanthesis(int n) {

         char dp[] = new char[n*2] ;
        //  Generate(n,dp) ;
        if( n != 0) {
            paranthesis(n, 0, 0, 0, dp);
        }
         
         return ;
       }
       public static void paranthesis ( int n ,int pos ,int open ,int close , char dp[]) {
            //reached the end
            if(close == n) {
                System.out.println(new String(dp));
                return ;
            }
            //operations
    
            if(open < n) {
                dp[pos] = '(' ;
                paranthesis(n, pos + 1 , open + 1, close, dp);
            }
             if(open > close) {
                dp[pos] = ')' ;
                paranthesis(n, pos + 1, open, close + 1, dp);
            }

       }

       public static int maxProfit(int prices[] , int fee) {
        int n = prices.length ;
        int day = 0 ;
        int hold =  - prices[0] ; //holding means we are not selling
        int profit = 0 ; //max profit 

        for(int i = 0; i<n ;i++) {
            profit = Math.max(profit, prices[i] + hold - fee) ; //sell a stock
            hold = Math.max(hold ,  profit - prices[i]) ; //buy a stock if benificial            
                
        }
        
        return profit ; //return the max profit we have calculated            
        
    }
       public static int numofParanthesis(int n) {
        int dp[] = new int[n+1] ;
        dp[0] = 1;
        dp[1] = 1;

        for(int i =2 ; i<=n ; i++) {
            for(int j = 0 ;j<i;j++) {
                dp[i]  += dp[j] * dp[i-j-1] ;
            }
        }

        return dp[n] ;


       }
        


        public static void main(String[] args) {        
       
        int arr[][] = {{9,9,4},{6,6,8},{2,1,1}} ;
       // System.out.println(minPartition(arr));
    //    System.out.println(minJumps(arr));
        int n = 10 ;
        // GenerateValidParanthesis(3);
    //    System.out.println( maxProfit(arr, 2)  );
        // System.out.println(numofParanthesis(4));
        System.out.println(longestIncreasingPath(arr));
    }
    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        } 

        int row = matrix.length;
        int col = matrix[0].length;
        int maxLength = 0;
        int[][] dp = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //calculate the length of the path
                maxLength = Math.max(maxLength, dfs(matrix, i, j, dp));
            }
        }

        return maxLength;
    }   
    public static int dfs(int[][] matrix, int x, int y, int[][] dp) {
        //Answer exist
        if (dp[x][y] > 0) {
            return dp[x][y];
        }
        //directions
        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int maxPath = 1;
        int row = matrix.length;
        int col = matrix[0].length;
          
        //Go to the direc
        for (int[] dir : dirs) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];
            //boundary 
            if (nextX < 0 || nextY < 0 || nextX >= row || nextY >= col) {
                continue;
            }
            //check for valid cells
            if (matrix[x][y] >= matrix[nextX][nextY]) {
                continue;
            }
            //calculate the path
            maxPath = Math.max(maxPath, 1 + dfs(matrix, nextX, nextY, dp));
        }
        //return the maxPath we have calculated
        return dp[x][y] = maxPath;
    }
}