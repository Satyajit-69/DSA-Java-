
import java.util.*;

public class HMimplement{
  //hashmap creation
  static  class Hashmap <K,V> { // K and V are generics which are general to all
   //Node class
    private  class Node {
    K key ; 
    V value ;

    //constructor
    public Node(K key , V value) {
      this.key = key ;
      this.value = value ;
    }
   }
    private int n; ///size (Hashmap)
    private int N ; // size(bucket)
    private LinkedList<Node> buckets[] ;//array of linkedlist
    @SuppressWarnings("Unchecked")  // to remove error
    
    //consturctor of hashmap :>
    public Hashmap(){
      this.N= 4 ;
      this.buckets = new LinkedList[4] ;
      for(int i=0 ; i<4 ; i++) { //creating (empty) LL
      this.buckets[i] = new LinkedList<>() ;
      }
    }

       // helper functions
        private int searchinLL(K key , int bi ){
      LinkedList<Node> ll = buckets[bi] ; //ll on bi
      int di =0 ; //data index

     //checking the key is present or not !
      for(int i =0 ;i< ll.size() ;i++){
        Node node = ll.get(i);
        if(node.key == key){
          return di ; // return the data index
        }
        di ++ ;
      }
      return -1  ;//found nothing
    }
        private int hashfunction(K key){
       int hc= key.hashCode() ; //returns a hashcode
       //123456 ; -1234567
        return  Math.abs(hc)%N; 
      //the absoulte val of hc % size gives val betn 0 to 3
    }
       @SuppressWarnings("Unchecked")
        private  void rehash() {
          LinkedList<Node> oldBuck[] = buckets ;
          buckets = new LinkedList[N*2] ; // size  * 2
          N = 2*N ;
          for(int i =0 ;i<buckets.length ;i ++) {          
                  buckets[i] = new LinkedList<>() ; // refresh
          }

          //nodes -> add in bucket
          for(int i = 0 ; i<oldBuck.length ; i++){
          LinkedList<Node>  ll = new LinkedList<>() ;
          for(int j = 0 ;j<ll.size() ; j++){ //import
               Node node = ll.remove() ;
               put(node.key, node.value);       
              }
            }

        }
  
    // functions or operations
    public void put(K key , V value) {
      int bi = hashfunction(key) ; //  0 to 3 (bucket index)
      int di = searchinLL(key , bi) ;  // di - >  valid ; -1

      if(di != -1) { // valid -> already exist
        Node node = buckets[bi] .get(di) ; //node
        node.value = value ; //update
      }else{
        buckets[bi].add(new Node(key, value)) ;
        n++ ;
      }
        double lamda= (double) n /N ;
        if(lamda > 2.0) {
        rehash();
    }
  
   }
    public boolean containsKey(K key) {
      int bi = hashfunction(key) ; //  0 to 3 (bucket index)
      int di = searchinLL(key , bi) ;  // di - >  valid ; -1

      if(di != -1) { // valid -> already exist
       return true ;
      }else{
       return false ;
      }
    }
    public V get(K key){
      int bi = hashfunction(key) ; //  0 to 3 (bucket index)
      int di = searchinLL(key , bi) ;  // di - >  valid ; -1

      if(di != -1) { // valid -> already exist
        Node node = buckets[bi] .get(di) ; //node
        return node.value  ; //update
      }else{
        return null ;
      }
    }
    public V remove(K key) { // 0(1)
      int bi = hashfunction(key) ; //  0 to 3 (bucket index)
      int di = searchinLL(key , bi) ;  // di - >  valid ; -1

      if(di != -1) { // valid -> already exist
        Node node = buckets[bi] .remove(di) ; //node
        n-- ;
        return node.value  ; //update
      }else{
        return  null ;
      }
    }
    public ArrayList<K> keyset(){
      ArrayList<K> keys = new ArrayList<>() ;
      for(int i =0 ;i< buckets.length ; i++){
        LinkedList<Node >ll = buckets[i] ;
        for (Node node  : ll){
          keys.add(node.key) ;
        }
      }
      return keys ;
    }
    public boolean isEmpty(){
      return n  ==  0 ;
    }
    
  
  }
  // public static void main(String[] args) {
  //   Hashmap<String ,Integer> h=  new Hashmap<>() ;
  //   h.put("india", 20);

  //   ArrayList<String>keys = h.keyset() ;
  //   for(String key : keys){
  //     System.out.println(key);
  //   }
  //   System.out.println(h.get("India"));
  //  }


   public static void main(String[] args) {
    LinkedHashMap<String,String> h = new LinkedHashMap<>() ;
    h.put("India", "most pooulation");
    h.put("China", "most powerfulcountry") ;
    h.put("Japan", "most advancedtechnology country") ;
    h.put("Southkorea", "most educated country") ;

    System.out.println(h);
    System.out.println(h.get("China"));
    System.out.println();


     TreeMap <String,String> n = new TreeMap<>() ;
     n.put("India", "most pooulation");
     n.put("China", "most powerfulcountry") ;
     n.put("Japan", "most advancedtechnology country") ;
     n.put("Southkorea", "most educated country") ;


     System.out.println(n);
}
}