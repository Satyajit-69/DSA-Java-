import java.util.*;
public class hashset {
    public static int Union(int arr1[] , int arr2[]){
       HashSet<Integer> set = new HashSet<>() ;
       for( int i= 0 ;i<arr1.length ;i ++) {
         set.add(arr1[i]) ;
       }
       for(int i =0 ;i<arr2.length ; i++){
        set.add(arr2[i]);
       }

       System.out.println(set +" ");
       return set.size();
       
    }
    public static int countDistelements(int nums[]){
       HashSet<Integer> set = new HashSet<>() ;
       for(int i =0 ; i<nums.length ;i ++){
        set.add(nums[i]) ;
       }
       return set.size() ;
    }
    public static int Intersection(int arr1[] , int arr2[]) {
        HashSet<Integer> set = new HashSet<>() ;
        //add elements from arr1
        for(int i =0 ;i<arr1.length ;i++){
            set.add(arr1[i]);
        }
        //add elements from arr2
        int count = 0  ;
        for(int i =0 ;i<arr2.length ; i++){
            //check if exist in set  then count ++
             if(set.contains(arr2[i])) {
                System.out.print(arr2[i] +" ");
                count++;
             }
             set.remove(arr2[i]) ;
        }
        System.out.println();
        return count ;
    } 
    public static void FindItinerary() {
      //hash map
      HashMap<String ,String> tickets = new HashMap<>() ;
      tickets.put("Chennai","Banglore") ;
      tickets.put("Mumbai" ,"Delhi") ;
      tickets.put("Goa"    , "Chennai");
      tickets.put("Delhi"  ,"Goa") ;
      

      String start = GetStart(tickets) ;
      System.out.print(start);
      for(String key : tickets.keySet()){
      System.out.print( "->"+tickets.get(start));
      start= tickets.get(start);
    }

    System.out.println();
   }
    public static String GetStart(HashMap<String ,String> tickets) {
      HashMap<String ,String>revMap = new HashMap<>() ;
         for(String key : tickets.keySet()){
           revMap.put(tickets.get(key),key) ;
         }

         for(String key : tickets.keySet()){
            if(!revMap.containsKey(key)) {
                return key ; //the key that not exist in reverse Map 
            }
         }

         return  null ;
    }
    public static int Largestsubarray(int arr[]) {
      //hash map
      HashMap<Integer,Integer> map = new HashMap<>() ;
       //(sum,idx)
        int sum = 0 ;
        int length = 0  ;
      for(int i =0 ;i<arr.length ;i++){ //loop from 0 to n
         sum += arr[i]; //sum on every idx

         //check sum exist in map ?
         if(map.containsKey(sum)) {
            //if exist then compare with length
            //len -> idx - map.get(sum)
            length = Math.max(length, i-map.get(sum)) ;
         }
         else{
            //add sum and idx to the map
            map.put(sum,i);
         }
      }
      return length;
    }

    public static void main(String[] args) { 
      int nums[]= {10,2,-2,-20,10} ;
      int K = -10 ;
      int SubArrSum = kSumofsubarr(nums , K)  ;
      System.out.println("the subarray which sum equals to k ->" + SubArrSum);
    }

    public  static int kSumofsubarr(int arr[] , int k) {
        //hashmap
        HashMap<Integer ,Integer> hm = new HashMap<>() ;

        hm.put(0, 1) ;//default
        int ans =0 ;
        int sum = 0 ;
        
        //loop
        for(int i=0 ;i<arr.length ;i++) {
           sum += arr[i] ;
          //check if sum exist in tha map ?
          if(hm.containsKey(sum-k)) {
              //add its freq to ans
              ans+= hm.get(sum-k) ;
          }  
             //put sum and its freq -> freq = 0 (by default)
             hm.put(sum,hm.getOrDefault(sum, 0)+1) ;
        }
        return  ans ;
    }
   }
