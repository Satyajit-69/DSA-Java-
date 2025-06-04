
import java.util.*;
public class Arraylist {
   
    public static boolean  monotonic( ArrayList<Integer> Height ){
        boolean inc = true ;
        for(int i = 0 ;  i< Height.size()-1 ; i++){
             
                if(Height.get(i)<= Height.get(i+1)){
                    inc =  true ;
                }else{ 
                    inc =  false ;
                }
            
        }
        return  inc ;
    }
    public static  int mostFrequent(ArrayList<Integer> nums, int key) {
        //make a count array
        int count [] = new int[1001] ;
        for(int i  = 0 ; i< nums.size(); i++){
         if(nums.get(i) == key){
            count[nums.get(i+1)]++ ;
         }
        }  
        // finding the index with greatest entry
        int maxvalue = 0 ; 
        int target = 0 ;
        for(int i = 0 ; i< count.length ; i++){
         if(count[i]>maxvalue){
         
           maxvalue = count[i] ;
           target = i;
         }
        }
        System.out.println("the target is occuring" +  maxvalue + " times") ;
        return  target ;
       }      
        //LONELY NUMBER :(
    public static  ArrayList<Integer> LONELYnumber(ArrayList<Integer>nums){
       int n  = nums.size() ;
       ArrayList<Integer> result = new ArrayList<>() ;
       Collections.sort(nums);

       for( int i  = 0 ; i<n ; i++){
        if( i !=0  && (nums.get(i - 1) == nums.get(i)-1 || nums.get(i) == nums.get(i)-1)) {
             continue;
             //continue means it will start from the loop
        }if( i != 0 &&(Objects.equals(nums.get(i+1), nums.get(i))||nums.get(i + 1 ) == nums.get(i) + 1)){
             continue;
        }
            result.add(nums.get(i)) ;
        }
        return  result ;
       } 
  
    public static ArrayList<Integer> beautifularray(int n ){
        //ans arraylist
        ArrayList<Integer> ans  = new  ArrayList<>() ;
        ans.add(1);
        
        while(ans.size()<n){
            //temp array
            ArrayList<Integer> temp = new  ArrayList<>() ;
            int ans_size = ans.size() ;
            for(int i =  0 ; i<ans_size ; i++){
                if((ans.get(i)*2-1) <= n){//odd part
                    temp.add(ans.get(i)*2 - 1) ;
                }
            }
            for(int i  = 0 ; i<ans_size ;i++)
               { if(ans.get(i)*2 <= n){
                    temp.add(ans.get(i)*2) ;
                }
            }
              ans = temp ;
         }
          return  ans ;
       }


       
       
      
    public static void main(String[] args) {
         Scanner input = new  Scanner (System.in) ;
         System.out.print("input n = " +" ");
         int n  =  input.nextInt() ;
         ArrayList<Integer> ans = beautifularray(n);
        System.out.println(ans
        );
    
    }

       
}
