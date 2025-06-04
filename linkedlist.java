
import java.util.*;

public class linkedlist {
    public static  class  Node {
     int data ;
     Node next ;
       Node(int d) {
        data  = d ;
        next = null ;
       }
       }
    public static  Node Head  ;
    public static  Node Tail ;
    public static  int size ;

    public void addfirst( int data){ 
        //step 1 => create new node
        Node newNode  = new Node(data) ;
         size ++ ;
        if(Head == null){
            Head = Tail = newNode ;
            return; 
        }
       
         //step 2 => new node next =  head
         newNode.next = Head ; //link
         
         //step 3  =>  head = new node
           Head = newNode ;
    }
    public void addLast(int data){
        // step 1  -> create a new node
        Node newNode = new  Node(data) ;
        size ++ ;
        if(Head == null){
            Head = Tail = newNode ;
            return; 
        }
       
        //step -> tail.next = new node 
        Tail.next = newNode ;

        //step -> tail = new node
        Tail = newNode ;
    }
    public void print(){
        // create node  temp
       Node temp = Head ;
       //loop
       if(Head== null){
        System.out.println("the LL is empty");
        return;
       }
       while(temp != null){
        System.out.print(temp.data +"->");
        temp = temp.next  ;
       }System.out.println("null");
    }
    public void addinMiddle(int data, int idx){

         if(idx == 0 ){
            addfirst(data);
            return ; 
         }
         Node newNode = new Node(data) ;
         size++;
         Node tempN =  Head ;
         //search the prevoius idx
         int i = 0 ;
         for(i= 0 ;i < idx-1 ; i++)
         {
          tempN =  tempN.next ; 
         }
         //found the prev idx
         newNode.next = tempN.next ; // 
         tempN.next = newNode ;
        
        
    }
    public int removeF() {
        if(size ==0 ){
            System.out.println("ll is empty");
            return  Integer.MIN_VALUE ;
        }  
        else if (size == 1){
            int val = Head.data ;
            Head= Tail = null ;
            return  val ;
        }
        int val = Head.data ;
        Head = Head.next ;
        size -- ;
        return  val ;
    }
    public int removeL(){
        if(size ==0 ){
            System.out.println("ll is empty");
            return Integer.MIN_VALUE ;
        }else if  (size == 1){
            int val = Head.data ;
            Head = Tail = null ;
            return  val ;
        }
        
        Node prev =  Head ;
        //prev =  size - 2
        for(int i  = 0 ; i<size-2 ; i++){
            prev = prev.next ;
        }
       int val = Tail.data ;
       prev.next = null ;
       Tail=  prev ;
       size-- ;
       return  val ;
    }
    public int iterativesearch( int key){
        int  i = 0 ;
        Node temp =  Head ;
        while(temp!= null){
            //check
             if(temp.data == key){
                return i;
                }
            temp = temp.next ;
            i++ ;
           
        } return  -1 ;
    }
    public int recursivesearch(int key ) {
        return  helper(Head , key) ;
    }
    public int  helper( Node Head, int key){
      if(Head == null){
        return  -1 ;
      }
      if(Head.data == key){
        return  0 ;
      }
      int idx = helper(Head.next, key) ;
      if(idx == -1){
        return  -1 ;
      }
      return  idx + 1  ;
    }
    public void reverse() {
        Node prev = null ;
        Node curr = Tail = Head ;
        Node next ;
        while(curr != null){
            next = curr.next  ;
            curr.next = prev ;
            prev = curr ;
            curr = next ;
        }
        Head = prev ;
    }
    public void removefromN ( int n ) {
        //calculate  size
     int sz = 0 ;
     Node temp  = Head ;
     while(temp != null){
        temp = temp.next ;
        sz++ ;
      }
      if(n== size){
        Head = Head.next ; //remove 1st operation
      }
      //sz-n
      int i =1 ;
      int  iTofind = sz-n ;
      Node prev = Head ;
      while(i<iTofind){
        prev = prev.next ;
        i++ ;
      }
      prev.next = prev.next.next ;
      return ;
    }
     // slow fast approach
    public Node findMid(Node Head) {

        Node slow = Head ;
        Node fast = Head ;

        while(fast !=  null && fast.next != null) {
            slow = slow.next ; //+1
            fast = fast.next.next ; //+2
        }
        return  slow ;
    }
    public  boolean  isPalindrome(){
        if(Head == null ) {
            return  true ;
        }
        //step 1 =: find mid
         Node middle  = findMid(Head) ;

        //step 2 +: reverse 2nd half
        Node  prev = null ;
        Node curr =  middle ;
        Node  next ;
         while(curr !=  null){
            next = curr.next ;
            curr.next = prev ;
            prev = curr ;
            curr = next ;
         }
         Node right =  prev ; // right half head
         Node left  = Head ; // left half head

         
        //step 3 =: check (1st half == 2nd half)
         while (right != null) {
            if(left.data  != right.data) {
               return  false ;
            }
            left = left.next ;
            right = right.next ;
        }
        return  true ;
    }
    public static  boolean  isCycle () {
        Node slow = Head ;
        Node fast = Head ;

        while(fast != null && fast.next != null)
        {
            slow= slow.next ; //+1
            fast = fast.next.next ;
            if(slow == fast) {
                return  true ; // cycle exist
            }
        }
            return false  ; // cycle doesnot exist
     } 
    public static  void removeCycle() {
        //detect  cycle
         Node slow = Head ;
         Node fast = Head ;
         boolean cycle = false ;
        
         while(fast !=  null && fast.next != null) {
            slow = slow.next ;
            fast = fast.next.next ;
            if(fast == slow) {
                cycle = true ;
                break;
              }
            }
            if(cycle== false) {
                return ;
            } 
            //find meeting point
            slow = Head ;
            Node prev = null ;//last node
            while(slow != fast){
            prev = fast ;
            slow = slow.next ;
            fast = fast.next ;//equally move
            }
           //remove cycle -> last.next = null
            prev.next = null ;
      }
    private Node getMid(Node Head){
        Node slow = Head ;
        Node fast = Head.next ;

        while(fast !=  null && fast.next != null) {
            slow = slow.next ; //+1
            fast = fast.next.next ; //+2
        }
        return  slow ; // mid node 
    }
    private Node merge(Node Head1 , Node Head2) {
         Node mergeL = new Node(-1) ; // temp LL
         Node temp = mergeL ;

         while(Head1 != null &&  Head2 != null) {
            if(Head1.data <= Head2.data) {
                temp.next = Head1;
                Head1 = Head1.next ;
                temp = temp.next ;
            }else {
                temp.next = Head2 ;
                Head2 = Head2.next ;
                temp = temp.next ;
            }
         }
           //remaining  parts
            while(Head1 != null){
            temp.next = Head1 ;
            Head1 = Head1.next ;
            temp = temp.next ;
           }
           while(Head2 != null){
            temp.next = Head2 ;
            Head2 = Head2.next ;
            temp = temp.next ;
           }
            return  mergeL.next ;
       }
    public  Node MergeSort(Node Head) { //0(NlogN)
        //base case 
        if(Head == null  || Head.next == null) { /// ll is already sorted
            return  Head ;
        }
        //find mid
        Node mid  = getMid(Head) ;
        
        //left and right
        Node rightHead = mid.next ;
        mid.next = null ;
        Node left = MergeSort(Head) ;
        Node right = MergeSort(rightHead) ;
        // merge 
        return  merge(left , right) ;
      }
    public void zigzagLL() {
        //step 1  -> find mid
        Node slow= Head ;
        Node fast = Head.next;
        while(fast!= null && fast.next != null ){
           slow= slow.next ;
           fast = fast.next.next ;
        }  Node mid  = slow ;

        // 2nd reverse

        Node curr= mid.next  ;
        mid.next = null ;
        Node prev=  null ;
        Node next ;

        while(curr != null){
            next = curr.next ;
            curr.next = prev ;
            prev = curr ;
            curr = next ;
        }
         Node left = Head ;
         Node right = prev ;
         Node nextL;
         Node nextR ;

         //alt merge- zig zag merge

         while(left  != null && right != null) {
            nextL = left.next ;
            left.next  = right ;
            nextR = right.next ;
            right.next = nextL ;
           //update
            left  = nextL ;
            right = nextR ;
         }
    }
    public void SegregateoddevenLL(){
        Node end = Head;
        Node curr = Head ;
        Node prev = null ;


        while(end.next != null){
           end = end.next ;
        }
        Node newEnd = end ;

        while(curr.data %2 != 0 && curr != end){ //odd element assemble
            newEnd.next = curr ;
            curr = curr.next;
            newEnd.next.next = null ;
            newEnd = newEnd.next ;
        }

        if(curr.data %2 == 0) {//even case =>  even element assemble
            Head = curr ;
            while(curr!= end){
                if(curr.data %2 == 0){
                    prev = curr ;
                    curr= curr.next ;
                }else{
                    prev.next = curr.next ;
                    curr.next= null ;
                    newEnd.next = curr ;
                    newEnd = curr ;
                    curr = prev.next ;

                    
                }
            }
        }else{
            prev= curr;
            if(newEnd != end && end.data %2 != 0)
             {prev.next = end.next ;
             end.next = null ;
             newEnd.next = end; 
             
        }
    }


    }
    public Node oddevenLL(){
        //base case = 
        while(Head == null){
             return  Head;
        }
        Node odd = Head  ;
        Node even = Head.next ;
        Node evenHead = even ;

        while(even != null && even.next  != null){

            ///change pointers for odd list
            odd.next =  odd.next.next ;
            odd= odd.next ;

            //change the pointers for even list
            even.next = even.next.next ;
            even= even.next ;
        }

        //assign even list at the end of odd list
         odd.next = evenHead ;
         

         return Head ;
    }
    public static  Node mergeKlist( Node arr[] , int last){
        while(last !=  0 ){
            int i = 0 , j= last ;
            while(i<j){
               arr[i]= SortedMerge(arr[i], arr[j]);
               i++;
               j--;
                if(i>=j)
                {last =j ;}
            }
        }
        return  arr[0];
    }
    public static  Node SortedMerge(Node a , Node b){
       Node result= null ;
       if(a == null){
        return  b ;
       }
       else if(b == null){
        return  a ;
       }
       if(a.data <= b.data){
        result =a ;
        result.next = SortedMerge(a.next, b);
       }else{
        result = b ;
        result.next = SortedMerge(a, b.next) ;
       }
       return  result ;
    }
    
    public static void main(String[] args) {
     Node head = new Node(1);
     head.next = new Node(2) ;
     head.next.next = new Node(3);
    
     head.next.next.next = new Node(2) ;
     head.next.next.next.next = new Node(1) ;
     printlist(head);
     if(ispalindrome(head)){
        System.out.println(" the ll is palindrome");
     }else{
        System.out.println(" the ll is not palindrome");
     }
    

} 
  public static void  printlist(Node node){
    while(node != null){
        System.out.print(node.data + "->");
        node = node.next ;
    }System.out.println("null");
  }
 
  public static boolean ispalindrome(Node head) {
        // Edge case: empty list or single node list
        if (head == null || head.next == null) {
            return true;
        }
        
        Stack<Integer> stack = new Stack<>();
        Node slow = head;
        Node fast = head;
        
        // Push elements onto stack until halfway through the list
        while (fast != null && fast.next != null) {
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // If the list has odd number of nodes, move slow pointer one step forward
        if (fast != null) {
            slow = slow.next;
        }
        
        // Compare elements with stack top while traversing the remaining list
        while (slow != null) {
            if (slow.data != stack.pop()) {
                return false;
            }
            slow = slow.next;
        }
        
        return true;

} 
}

