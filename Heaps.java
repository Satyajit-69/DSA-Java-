
import java.util.*;
public class Heaps {
       static class  heaps {
       ArrayList <Integer> arr = new ArrayList<>() ;
       public  void add(int data){ //o(log n)
       //add at last index
        arr.add(data);


        int x = arr.size()-1 ; //x is child index
        int par = (x-1)/2 ; //parent index

        while (arr.get(x) > arr.get(par)) {
              //swap
              int temp = arr.get(x);
              arr.set(x,arr.get(par)) ;
              arr.set(par,temp) ;

              x=par ;
              par =(x-1)/2 ;
          }
       }
       public int peek(){
              if(arr.isEmpty()){
                     return 0 ;
              }
              return arr.get(0) ; //least element of the arraylist
       }
       public int remove(){
              int data = arr.get(0) ;
              //step -> 1 swap with first and last index
              int temp = arr.get(0) ;
              arr.set(0, arr.get(arr.size()-1));
              arr.set(arr.size()-1, temp) ;

              //step-> 2 delete last index 
              arr.remove(arr.size()-1) ;

              //step -> 3 fix the heap with the help of heapify function
              heapify(0) ;
              return  data;
       }      
       private void heapify(int i){
              int left = 2*i+1 ;
              int right = 2*i+2 ;
              int minIdx = i ;
             

              //check 
              if(left < arr.size()  && arr.get(minIdx) < arr.get(left)) {
                 minIdx =left;
              }
              if(right < arr.size() && arr.get(minIdx) < arr.get(right)){
                   minIdx = right ;
              }

              if(minIdx != i){ // smaller value
                     //swap
                     int temp = arr.get(i);
                     arr.set(i,arr.get(minIdx)) ;
                     arr.set(minIdx,temp) ;

                     heapify(minIdx);
              }
       } 
       public boolean isEmpty(){
              return arr.isEmpty() ;
       }
       public void heapsort(int arr[]){

              //step-1 build maxheap
              int n = arr.length ;
              for (int i = n/2 ; i>= 0 ; i--){
                     Heapify(arr,i,n);
              }
              //step ->2  push the largest at end
              for(int i = n-1 ;i>=0 ;i--){
                   //swap largest with last & remove from heap by simple decreasing size n
                   int temp = arr[0];
                   arr[0]= arr[i];
                   arr[i]= temp ;

                   Heapify(arr,0,i) ;
                 

              }
       }
       public void Heapify(int array[] ,int i , int s) {
              int left = 2*i+1 ;
              int right = 2*i +2 ;
              int maxidx = i ;


                  if(left < s && array[maxidx] < array[left]) {
                     maxidx =left;
                  }
                  if(right < s && array[maxidx] < array[right]){
                       maxidx = right ;
                  }
    
                  if(maxidx != i){ 
                     //swap
                     int temp = array[i];
                     array[i]= array[maxidx] ;
                     array[maxidx] = temp ;

                     Heapify(array,maxidx,s) ;
              }
       }
    }
       public static  void knearestcars(){
              int pts[][]= {{3,3},{5,-1},{2,4}} ;
              int k= 2 ;
 
              PriorityQueue <Point> pq = new  PriorityQueue<>() ;
 
              for(int i = 0 ;i<pts.length ; i++){
               int distsq = pts[i][0] * pts[i][0] + pts[i][1]*pts[i][1] ;
               pq.add(new Point(pts[i][0],pts[i][1], distsq ,i)) ;
              }
 
 
              //nearest K cars
              for(int i =0 ; i<k ; i++){
               System.out.println("C"+pq.remove().idx);
        
        }
 
       }
       static  class Point implements Comparable<Point>{ // compare the points
              int x ;
              int y ;
              int distsq ;
              int idx ;

              public Point(int x , int y, int distsq , int idx) {
                        this.x = x ;
                        this.y = y ;
                        this.distsq = distsq;
                        this.idx = idx ;
              }
             

            @Override
            public int compareTo(Point p2) { //sorting ascending order
              return this.distsq - p2.distsq ;
            }

             
       } 
       public static void Combinerope(){
              int ropes[]= {4,3,2,6};
              PriorityQueue<Integer>pq = new PriorityQueue<>() ;
              for(int i =0 ;i<ropes.length ;i++){
               pq.add(ropes[i]);
              }
 
              int cost =0 ;
              while(pq.size()>1) {
               int min  = pq.remove() ;
               int min2 = pq.remove() ;
               cost += min+min2 ;
               pq.add(min+min2);
              }
 
              System.out.println("cost of connecting n ropes " +cost);
       }
       static class Row implements Comparable<Row>{
                   int soldiers ;
                   int idx ;

                   public Row(int soldiers , int idx )
                {
                     this.soldiers = soldiers ;
                     this.idx = idx ;
                }

                @Override
                public int compareTo(Row r2) {
                     if(this.soldiers == r2.soldiers) {
                            return  this.idx -  r2.idx ; //sort on idx basis
                     }
                     return  this.soldiers -r2.soldiers ; //sort on the basis of soldiers count
                }
           }    
       public static void weakestsoldiers(){
               int army[][] ={{1,0,0,0},
                         {1,1,1,1},
                         {1,0,0,0},
                         {1,0,0,0}} ;

         
           int k=2 ;
           PriorityQueue<Row> pq = new  PriorityQueue<>() ;
           //add army 
           for(int i =0 ;i <army.length ; i++){
              int count = 0;
              for(int j =0 ; j<army[0].length ;j++){
                     count += army[i][j] == 1 ? 1 : 0   ;
              }
              pq.add(new Row(count, i));
           }

           for(int i =0 ;i<k ;i++){
              System.out.println("R" +pq.remove().idx);
           }
       }
       static  class  Pair implements Comparable<Pair>{
              int val ;
              int idx ;
              public Pair(int val , int idx) {
                     this.val = val ;
                     this.idx = idx ;
              }
              @Override
              public int compareTo(Pair p2){
                     //descending order 
                  return p2.val - this.val ;
              }
       }
       static  class points implements  Comparable<points>{
              int x ;
              int y ;
              int distsq ;
              int idx ;

              public points(int x ,int y,int distsq ,int  idx) {
              this.x = x ;
              this.y = y ;
              this.distsq = distsq ;
              this.idx = idx ;
              }

        

              //sorting on the basis of distsqr 

              @Override 

              public int compareTo(points p2){
                  return this.distsq - p2.distsq;
              }

       }
       public static void knearestcars(int pts[][] ,int k){
          PriorityQueue <points> p = new PriorityQueue<>() ;
             for(int i=0; i<pts.length ;i++){
              int distsq = pts[i][0] * pts[i][0] + pts[i][1] * pts[i][1] ; //x sqr + y sqr == dist sq
              p.add(new points(pts[i][0], pts[i][1], distsq, i)) ;
             }
            
             System.out.println("your nearest "+ k + " cars are :");
             for (int i = 0; i < k; i++) {
              System.out.println("C->"+p.remove().idx);   
             }
         }
       public static void print(Node k){ 
             while (k != null) {
                 System.out.print(k.data);
                 k = k.next ;
                 
             }

         }

         public static void main(String[] args) {
            int arr[] = {3,2,1,5,6,4,} ;
            List <Integer > l = kthLargest(arr, 2) ;
            System.out.println(l);
         }

         
      
       
       

       public static  List<Integer> kthLargest(int arr[],int k) {
              PriorityQueue<Integer> pq = new PriorityQueue<>() ;
              List<Integer> res = new ArrayList<>() ;
              for(int i =0 ;i<arr.length ;i++){
                     pq.add(arr[i]) ;
                     if(pq.size()>k){
                            pq.remove() ;
                     }
              }
              while(!pq.isEmpty()){
                     res.add(pq.remove()) ;
              }
              return res ;
       }

}




              
       

       
       



       
