
class Node {
    int data ;
    Node next ;
     Node(int d) {
      data = d ;
      next = null ;
     }
    }
    class  Main {
    Node head ;
    public  void push (int data) {
    Node newNode  = new Node(data) ;
    newNode.next = head ;
    head = newNode ;
    }


    public void print (){
    Node temp = head ;
    while(temp!= null) {
      System.out.print(temp.data + "->");
      temp = temp.next ;
    }
    System.out.println("null");
    }


    public  void  swapping(  int x , int y ) {
    if(x==y)return;

    Node pX = null ;
    Node currX = head ;

    while(currX != null && currX.data != x) { // to reach previous of X
      pX =  currX ;
      currX = currX . next ;
    }
    Node pY = null ;Node currY = head ;
    while(currY != null && currY.data != y) { // we reached at prev of Y
      pY = currY ;
      currY = currY.next ;
    }
    if(currX == null || currY ==null)
    return;

      if(pX != null)
    { pX.next = currY ;}
     else {
      head = currY ;
     }
     if(pY != null)
      {pY .next = currX ;}
      else {
        head = currX ;
      }

     Node temp = currX.next ;
     currX.next = currY.next ;
     currY.next  = temp ;
   }
   public Node oddEvenList() {
        if (head == null || head.next == null) {
            return head;
        }
           //  odd - 1 / 3 /5 /7 ....
            Node odd = head;
           //  even - 2 / 4 / 6 / 8 .......
            Node even = head.next;
            Node evenHead = even;

        while (even != null && even.next != null) {
           //change the pointers for odd
            odd.next = odd.next.next;
            odd = odd.next;

            //change the pointers for even
            even.next = odd.next;
            even = even.next;
        }
        
        odd.next = evenHead;
        return head;
    }
   public static void main(String[] args) {//8->12->10->5->4->1->6
    Main m = new  Main() ;
    m.push(6);
    m.push(1);
    m.push(4);
    m.push(5);
    m.push(10);
    m.push(12);
    m.push(8);
    m.print();
    m.oddEvenList() ;
    m.print();
   }
  
  
  }