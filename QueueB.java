
import java.util.*;
public class QueueB {
   public static void reversefirstK( Queue<Integer> q , int k)
   {//steps
   Stack<Integer> s = new  Stack<>() ;

   if(k<=0  || q.isEmpty() | k>q.size()){
      return;
   }
    //(1)-> remove 1st k elements from queue and add those elements from stack
   for(int i =0 ; i<k ;i++) {
     s.push (q.remove());
      
   }
   //(2)->remove element from stack and add them again into queue
   while(!s.isEmpty()){
      q.add(s.pop()) ;
   }
   //(3)-> remove sz-k elements from queue and add then again into the queue from end
   
   }
   public static void helper(Queue<Integer> q  , int k){
      if(k ==0 ){
         return;
      }
      int front = q.poll() ;
      helper(q,k-1);
      q.add(front);
   }
   public static void recursiveCall(Queue<Integer> q  , int k){
      helper(q, k); //step -1  and step -2
      //step 3
       int size = q.size()- k ;
       while(size -- >0){
         q.add(q.remove()) ;
       }
   }
   public static void printSubarr(int arr[], int k){
      Deque <Integer> s  = new  LinkedList<>() ;
      //for every element , the prevoius 
      //smaller elements are useless so
      //remove them from q
      for(int i =0 ;i<k ;i++){
         while(!s.isEmpty() && arr[i]>= arr[s.peekLast()]){
            //remove the rear
            s.removeLast() ;
         }
        //add new element  at rear 
      }
   }
 
  public static void main(String[] args) {
   int arr[]= {1,2,3,1,4,5,2,3,6} ;
   int k =3 ;
   int n = arr.length ;
   Slidingwindowbruteforce(arr, k, n);
   System.out.println();
   SWDEQUE(arr, k, n);
  }

  public static void Slidingwindowbruteforce(int arr[], int k , int  n) {
     for(int i  =0 ;i<=n-k ;i++){
      //calculate the cur max 
      int curmax =  0; 

      //finfing the maximum of the subarrays
      for(int j = 0;j<k ; j++) {
         if(arr[i + j] > curmax) {curmax = arr[i+ j] ;}
        //  System.out.println("now the currmax becomes" + curmax);
      }

           System.out.print(curmax + " ");
     }
  }
  public static void SWDEQUE (int arr[] , int K , int N ) {
       //create a deque
       Deque<Integer> Qi = new LinkedList<>();

        /* Process first k (or first window)
        elements of array */
        int i;
        for (i = 0; i < K; i++) {

            // For every element, the previous
            // smaller elements are useless so
            // remove them from Qi
            while (!Qi.isEmpty()
                   && arr[i] >= arr[Qi.peekLast()])
             { // Remove from rear
                Qi.removeLast();
             }
               
            // Add new element at rear of queue
            Qi.addLast(i);
        }

        // Process rest of the elements,
        // i.e., from arr[k] to arr[n-1]
        for (; i < N; ++i) {

            // The element at the front of the
            // queue is the largest element of
            // previous window, so print it
            System.out.print(arr[Qi.peek()] + " ");

            // Remove the elements which
            // are out of this window
            while ((!Qi.isEmpty()) && Qi.peek() <= i - K)
                Qi.removeFirst();

            // Remove all elements smaller
            // than the currently
            // being added element (remove
            // useless elements)
            while ((!Qi.isEmpty())
                   && arr[i] >= arr[Qi.peekLast()])
                Qi.removeLast();

            // Add current element at the rear of Qi
            Qi.addLast(i);
        }

        // Print the maximum element of last window
        System.out.print(arr[Qi.peek()]);

     
  }
}
