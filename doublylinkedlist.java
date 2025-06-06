
public class doublylinkedlist {
     public class Node{
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
            doublylinkedlist d = new  doublylinkedlist() ;
            d.addfirst(3); 
            d.addlast(34);
            
            d.print();
            d.reverse();
            d.print();
           }

        }

