import java.util.*;
public class GreedyAlgo {
    public static int maximumBalancedsubstring(String str, int n  ){
     
        int count = 0 ;
        if(n== 0){return 0 ;} 

        int l = 0 ; int r = 0 ;
        for(int i = 0 ;i<n ;i++){
            if(str.charAt(i) == 'L'){
                l++ ;
            }
            if(str.charAt(i)=='R'){
                r++ ;
            }
            if(l == r){
                count++ ;
                l =0 ;
                r =0 ;
            }
        }
     return  count ;
    }
    public static  int KthLargestOddNumbers( int L,int R,int K) {
      int totalnumber = R-L +1 ;
      int numberofoddnumbers ;
      if(L%2 != 0 && R%2 != 0 ) {
        numberofoddnumbers = (totalnumber/2) +1 ;
      }else{
        numberofoddnumbers = totalnumber/2 ;
       }
     
    if(K>numberofoddnumbers){
        return  0 ;
    }
    int largestval ;
    if(R %2 != 0 ){
        largestval = R ;
    }else{
        largestval = R -1 ;
    }

    return  largestval-(2*(K-1)) ;
} 
    public static char[]  Lexosmall(int k , int n){
  //make a ch array

  char ch [] =  new char[n];
  //fill the ch  with 'a'  -> default
  Arrays.fill( ch,'a'); // now the ch[] look like -> aaaa
    k-=n*1 ;
   //loop
   for(int i = n-1 ; i>=0 ;i--){
    //check wheather k>26
    
    if(k>0)
    {    k+= 1 ;
        if(k>=26){// assign z in the n-1 index !
        ch[i]='z' ;
        k-= 26 ;
    }
    else if(k<=26) {
        ch[i] = (char)(k+97-1) ; // converting the remaining k val into char and assigning the char into last index
        k-= ch[i]-'a'+1 ; // subtracting the value  from k
    }
  }
    else {break ;}
    
}
   return ch ;
}
    public static int  Maxprofit ( int prices[]){
    int max =0 ;
    for (int i =1 ; i<prices.length ;i++){
         int buy  =prices[0] ;
        if( buy < prices[i]){
            buy = prices[i] ;
            max =Math .max(max, buy) ; 
        }
      }
       return max ;  
    }
    public static void main(String[] args) {
    int val[] = {15,14,10,45,30} ;
    int weight[] = {2,5,1,3,4} ;
    int W = 7 ;
    int dp[][] = new int[val.length +1][W+1] ;
    //initialization
    
    for (int row[] : dp) Arrays.fill(row, -1); // Fill with -1 (not computed yet){
    
        
        
  // System.out.println(fractionalKnapSack(val, weight, 50));
 //System.out.println(knapsackRecursion(val, weight,7, val.length));
   //System.out.println(knapsackMemoization(val, weight, W, W, dp));
  // System.out.println( "the knapsack value will be : " + knapsackTab(val, weight, W));
}
   
    public static int fractionalKnapSack(int val[] , int weight[] , int w) {
         double ratio[][]  = new double[val.length][2] ;
         // we use double data type bcz  floating val can be expect
         for(int i = 0 ; i<val.length ;i++){
            ratio[i][0] = i ;  // 1st column = val
            ratio[i][1] = val[i] / (double) weight[i] ; // ratio of the item 
         }

         //sorting on the basis of ratio
         Arrays.sort(ratio, Comparator.comparingDouble(o -> o[1]));

         int capacity  = w ;
         int finalval = 0 ;
         
          //loop on ratio
          for(int i = ratio.length -1 ;i>=0;i--){
             //more ratio more val i.e. backward loop
             int idx = (int)ratio[i][0] ;
             
              //condition
              if(capacity >= weight[idx]) { //can invoke 
                 finalval += val[idx] ;
                 capacity -= weight[idx] ;
              }
              else{
                 finalval += (ratio[i][1] * capacity) ;
                 break ;
              }
          }
          return finalval ;
     }
 
}
        

