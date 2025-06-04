import java.util.*;

public class hashmap {
  public static void main(String[] args) {
    String s  = "cat";
    String t  = "tad" ;

    boolean x = ValidAnagram(s ,t) ;
    System.out.println(x);
  }   
  public static boolean ValidAnagram(String s  , String t) {
    HashMap<Character ,Integer > map = new HashMap<>() ;
    for(int i = 0 ; i<s.length() ; i++) {
        map.put(s.charAt(i),map.getOrDefault(s.charAt(i), 0)+1) ;
    }
    //loop on t
    for(int i =0 ;i <t.length() ; i++){
      char ch = t.charAt(i); 
       if(map.get(ch) != null) {
         if(map.get(ch) ==1 ){
          map.remove(ch) ; //remove from map
         }else{
          map.put(ch, map.get(ch)-1) ; //decrease by 1
         }
       }else{
        return  false ;
       }
    }
    if(map.isEmpty()){
      return  true ;
    }
    return  false ;
  }


  public static void majorityelement(int arr[]) {
    //
    int nums;
    HashMap<Integer,Integer> hm = new HashMap<>() ;
    for(int i =  0 ; i<arr.length ; i++) {
        nums = arr[i] ;
        //conatains key
        if(hm.containsKey(nums)) {
            hm.put(nums,hm.get(nums) +1) ;
        }
        else{
            hm.put(nums,1) ;
        }
    }
    Set <Integer> keyset = hm.keySet() ;
    for(Integer key : keyset){
        if(hm.get(key) > arr.length/3) {
        System.out.println(key);
    }
  }
}
}
