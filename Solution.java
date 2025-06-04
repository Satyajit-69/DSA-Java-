
import java.util.* ;
public  class Solution {
   public static void main(String[] args) {
      int n =3 ;
      Node [] a = new Node[n] ;
      Node head1 = new Node(1) ;
      a[0] = head1 ;
      head1.next = new Node(3) ;
      head1.next.next = new Node(5) ;
      Node head2 = new Node(4) ;
      a[1] = head2 ;
      head2.next = new Node(6) ;
      head2.next.next = new Node(8) ;

      Node res  = mergeKsortedLists(a, n) ;
      if (res != null) {
        print(res);
      } System.out.println();


   }


   public static Node mergeKsortedLists(Node [] a, int n){
       PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator()) ;
       for (int i = 0; i < n; i++) {
           if (a[i] != null) {
               pq.add(a[i]) ;
           }
       }
       Node dummy = new Node(0) ;
       Node tail = dummy ;
       while (!pq.isEmpty()) {
           Node temp = pq.poll() ;
           tail.next = temp ;
           tail = tail.next ;
           if (temp.next != null) {
               pq.add(temp.next) ;
           }
       }
       return dummy.next ;
   }

    public static void print(Node k){
        while (k != null) {
            System.out.print(k.data + " ");
            k = k.next ;
            
        }
  
    }
  
}


  class Node {
    int data ;
    Node next ;
    Node(int data){
        this.data = data ;
        this.next = null ;
    }

  }

  class  NodeComparator implements Comparator<Node>{
    public int compare(Node n1 , Node n2){
        return n1.data - n2.data ;
    }
  }
