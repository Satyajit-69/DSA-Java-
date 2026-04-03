import java.util.*;
public class doublylinkedlist {
     public static class Node{
           int data ;
           Node next ; 
           Node prev ;

            public Node(int data){
                  this.data = data ;
                  this.prev = null ;
                  this.next = null ;
           }
        }
           public static  Node head ;
           public static  Node tail ;
           public static  int  size;
           public void addfirst(int data){
                    Node newNode =  new  Node(data) ;
                    size ++ ;
                    if(head == null) {
                        head = tail = newNode ;
                        return ;
                    }
                    newNode.next = head ;
                    head.prev = newNode ;
                    head = newNode ;
           }
           public  int removefirst(){
                  
                  if(head == null){
                    head= tail = null ;
                    return Integer.MIN_VALUE ;
                  }
                  if(size == 1){
                    int val = head.data ;
                    head = tail= null ;
                    size -- ;
                    return  val ;
                  }
                  int val = head.data ;
                  head = head.next ;
                  head.prev = null ;
                  size -- ;
                  return val ;
           }
           public void print () {
            Node temp  = head ;
            while(temp != null){
                System.out.print(temp.data  +"<->");
                temp = temp.next ;
            }
            System.out.println("null");
           }
           public void addlast(int  data){
            //create a new node 
             Node newNode = new Node (data) ;
             size ++ ;
             if(head == null){
                head = tail = null ;
                return  ;
             }
             newNode.prev = tail ;
             tail.next = newNode ;
             tail = newNode ;

           }
           public int  removelast(){
             //base case 
              if(head  == null){
                System.out.println("the doubly ll is empty "); 
                return  Integer.MIN_VALUE ;
              }
              if(size == 1) {
                int val =  tail.data ;
                head= tail= null ;
                size  -- ;
                return  val ;
              }
              int val = tail.data ;
              tail = tail.prev ;
              tail.next = null ;
            return val ;
           }
           public void reverse(){
             Node curr = head ;
             Node prev = null ;
             Node next  ;
              while(curr!= null) {
                next = curr.next ;
                curr.next = prev ;
                curr.prev = next ;
                prev= curr ;
                curr = next ;
              }
              head = prev ;
           }
           public static void main(String[] args) {
              int arr[] = {1,2,3,4,6,6,7} ;
              Node head = arr2Dll(arr) ;
              // add(head, 5);
            
              
              Node updated = deleteAllDuplicates(head);
              print(updated);
              

           } 
           public static Node arr2Dll(int arr[]) {
              int n = arr.length ;
              Node head = new Node(arr[0]) ;
              Node curr = head ;

              for(int i =1 ;i < n ; i++) {
                Node temp = new Node(arr[i]);
                temp.prev = curr ;
                curr.next = temp ;
                curr = temp ;
              }

              return head ;
           } 
           
           public static void print (Node head) {
            Node temp  = head ;
            while(temp != null){
                System.out.print(temp.data  +"<->");
                temp = temp.next ;
            }
            System.out.println("null");
           }
           public static void add(Node head , int data) {
            Node newNode = new Node(data);
            //set the pointers
            newNode.next = null ;

            Node curr = head ;
            //reach the end
            while (curr.next != null) {
              curr = curr.next ;
            }

            newNode.prev = curr ;
            curr.next = newNode ;

          }
           public static Node delete(Node head){
              if(head == null) {
                return null ;
              }
              
              Node curr = head ;
              //reach the end 
              while (curr.next != null) {
                curr = curr.next ;
              }

              Node newTail = curr.prev ;
              newTail.next = null ;
              curr = newTail ;

              return head ;
           }
           public static Node reverseDll(Node head) {
               Node curr = head ; //iterator
               Node next ; 
               Node prev = null ;
               while (curr != null) {
                //we are pointing reverse order where prev is next and next is prev
                  next = curr.next ;
                  curr.next = prev ;
                  curr.prev = next ;
                  
                  //update
                  prev = curr ;
                  curr = next ;

               } 
               
               head = prev ;
               

               return head ;
           }
           public static Node deleteOccurence(Node head , int key) {
            
            if(head == null || head.next == null){
              return null ;
            }

            //iterators 
            Node temp = head ;

           
            while(temp != null) {


              if(temp.data == key) {
                 
                //head case
                if(temp == head){
                  head = temp.next ;
                }

                //change
                Node nextNode = temp.next ;
                Node prevNode = temp.prev ;

                
                //this is to prevent the null poiner exception
                if(nextNode != null && nextNode.prev != null) {
                   nextNode.prev = prevNode ;
                }
                if(prevNode != null && prevNode.next != null) {
                  prevNode.next = nextNode ;
                }
                
                //move the temp
                temp = nextNode ;
                
                //to keep the chain
                temp.prev = null ;
                temp.next = null ;
         
              }
              temp = temp.next ;
            }
            
            return head;

           }


      public static ArrayList < ArrayList < Integer >> findPairs(Node head , int targetsum) {
         if(head == null || head.next == null)  {
           return null ;
         }
        
         Node left = head ;
         Node right = findTail(head) ;
         
         ArrayList < ArrayList < Integer >> res = new ArrayList<>() ;

         while (left != null && right != null && left != right && right.next != left)  {

          int currsum = left.data + right.data ;
          if(currsum == targetsum){
            ArrayList<Integer> pair = new ArrayList<>() ;
            pair.add(left.data) ;
            pair.add(right.data) ; 


            res.add(pair) ;
            //update
            left = left.next ;
            right = right.prev ;


          }
          else if(currsum > targetsum) {
            left = left.next ;
          }
          else {
            right = right.prev ;
          }
         
            
         }
          
         return res ;
      }
    
      public static Node findTail (Node head) {
        if(head == null || head.next == null) {
          return null;
        }

        Node temp = head ;
        while (temp.next != null) {
          temp = temp.next ;
        }

        return temp ;
      }
      
      public static Node deleteAllDuplicates(Node head) {

            //base case
            if(head == null || head.next == null) {
              return head ;
            }

            Node temp = head ;
            Node nextNode = temp.next ;
            

            while(temp != null && temp.next != null) {
               
                  
              while(nextNode != null && nextNode.data == temp.data) {
                nextNode = nextNode.next ;
              }
              
              //we have on the non duplicate value
              temp.next = nextNode ;
              if(nextNode != null){
                nextNode.prev = temp ;
              }


              //move 
              temp = temp.next ;
            } 
            
            return head ;


      }

        
  }

