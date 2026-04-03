import java.util.*;

public class linkedlist {
    public static class Node {
        // implementation
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    public static Node Head;
    public static Node Tail;
    public static int size;

    public void addfirst(int data) {
        // step 1 => create new node
        Node newNode = new Node(data);
        size++;
        if (Head == null) {
            Head = Tail = newNode;
            return;
        }

        // step 2 => new node next = head
        newNode.next = Head; // link

        // step 3 => head = new node
        Head = newNode;
    }

    public void addLast(int data) {
        // step 1 -> create a new node
        Node newNode = new Node(data);
        size++;
        if (Head == null) {
            Head = Tail = newNode;
            return;
        }

        // step -> tail.next = new node
        Tail.next = newNode;

        // step -> tail = new node
        Tail = newNode;
    }

    public void print() {
        // create node temp
        Node temp = Head;
        // loop
        if (Head == null) {
            System.out.println("the LL is empty");
            return;
        }
        while (temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void addinMiddle(int data, int idx) {

        if (idx == 0) {
            addfirst(data);
            return;
        }
        Node newNode = new Node(data);
        size++;
        Node tempN = Head;
        // search the prevoius idx
        int i = 0;
        for (i = 0; i < idx - 1; i++) {
            tempN = tempN.next;
        }
        // found the prev idx
        newNode.next = tempN.next; //
        tempN.next = newNode;

    }

    public static Node arr2LL(int arr[]) {
        int n = arr.length;
        Node head = new Node(arr[0]);
        Node mover = head;

        for (int i = 1; i < n; i++) {
            Node temp = new Node(arr[i]);
            mover.next = temp;
            mover = temp;
        }

        return head;
    }

    public int removeF() {
        if (size == 0) {
            System.out.println("ll is empty");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int val = Head.data;
            Head = Tail = null;
            return val;
        }
        int val = Head.data;
        Head = Head.next;
        size--;
        return val;
    }

    public int removeL() {
        if (size == 0) {
            System.out.println("ll is empty");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int val = Head.data;
            Head = Tail = null;
            return val;
        }

        Node prev = Head;
        // prev = size - 2
        for (int i = 0; i < size - 2; i++) {
            prev = prev.next;
        }
        int val = Tail.data;
        prev.next = null;
        Tail = prev;
        size--;
        return val;
    }

    public int iterativesearch(int key) {
        int i = 0;
        Node temp = Head;
        while (temp != null) {
            // check
            if (temp.data == key) {
                return i;
            }
            temp = temp.next;
            i++;

        }
        return -1;
    }

    public int recursivesearch(int key) {
        return helper(Head, key);
    }

    public int helper(Node Head, int key) {
        if (Head == null) {
            return -1;
        }
        if (Head.data == key) {
            return 0;
        }
        int idx = helper(Head.next, key);
        if (idx == -1) {
            return -1;
        }
        return idx + 1;
    }

    public static void reverse() {
        Node prev = null;
        Node curr = Tail = Head;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Head = prev;
    }

    public void removefromN(int n) {
        // calculate size
        int sz = 0;
        Node temp = Head;
        while (temp != null) {
            temp = temp.next;
            sz++;
        }

        // first element
        if (n == size) {
            Head = Head.next; // remove 1st operation
        }

        // sz-n
        int i = 1;
        int findPos = sz - n;
        Node prev = Head;
        while (i < findPos) {
            prev = prev.next;
            i++;
        }
        prev.next = prev.next.next;
        return;
    }

    // slow fast approach
    public Node findMid(Node Head) {

        Node slow = Head;
        Node fast = Head;

        while (fast != null && fast.next != null) {
            slow = slow.next; // +1
            fast = fast.next.next; // +2
        }
        return slow;
    }

    public boolean isPalindrome() {
        if (Head == null) {
            return true;
        }
        // step 1 =: find mid
        Node middle = findMid(Head);

        // step 2 +: reverse 2nd half
        Node prev = null;
        Node curr = middle;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node right = prev; // right half head
        Node left = Head; // left half head

        // step 3 =: check (1st half == 2nd half)
        while (right != null) {
            if (left.data != right.data) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    public static boolean isCycle() {
        Node slow = Head;
        Node fast = Head;

        while (fast != null && fast.next != null) {
            slow = slow.next; // +1
            fast = fast.next.next;
            if (slow == fast) {
                return true; // cycle exist
            }
        }
        return false; // cycle doesnot exist
    }

    public static void removeCycle() {
        // detect cycle
        Node slow = Head;
        Node fast = Head;
        boolean cycle = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                cycle = true;
                break;
            }
        }
        if (cycle == false) {
            return;
        }
        // find meeting point
        slow = Head;
        Node prev = null;// last node
        while (slow != fast) {
            prev = fast;
            slow = slow.next;
            fast = fast.next;// equally move
        }
        // remove cycle -> last.next = null
        prev.next = null;
    }

    private Node getMid(Node Head) {
        Node slow = Head;
        Node fast = Head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next; // +1
            fast = fast.next.next; // +2
        }
        return slow; // mid node
    }

    private Node merge(Node Head1, Node Head2) {
        Node mergeL = new Node(-1); // temp LL
        Node temp = mergeL;

        while (Head1 != null && Head2 != null) {
            if (Head1.data <= Head2.data) {
                temp.next = Head1;
                Head1 = Head1.next;
                temp = temp.next;
            } else {
                temp.next = Head2;
                Head2 = Head2.next;
                temp = temp.next;
            }
        }
        // remaining parts
        while (Head1 != null) {
            temp.next = Head1;
            Head1 = Head1.next;
            temp = temp.next;
        }
        while (Head2 != null) {
            temp.next = Head2;
            Head2 = Head2.next;
            temp = temp.next;
        }
        return mergeL.next;
    }

    public Node MergeSort(Node Head) { // 0(NlogN)
        // base case
        if (Head == null || Head.next == null) { /// ll is already sorted
            return Head;
        }
        // find mid
        Node mid = getMid(Head);

        // left and right
        Node rightHead = mid.next;
        mid.next = null;
        Node left = MergeSort(Head);
        Node right = MergeSort(rightHead);
        // merge
        return merge(left, right);
    }

    public void zigzagLL() {
        // step 1 -> find mid
        Node slow = Head;
        Node fast = Head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node mid = slow;

        // 2nd reverse

        Node curr = mid.next;
        mid.next = null;
        Node prev = null;
        Node next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node left = Head;
        Node right = prev;
        Node nextL;
        Node nextR;

        // alt merge- zig zag merge

        while (left != null && right != null) {
            nextL = left.next;
            left.next = right;
            nextR = right.next;
            right.next = nextL;
            // update
            left = nextL;
            right = nextR;
        }
    }

    public void SegregateoddevenLL() {
        Node end = Head;
        Node curr = Head;
        Node prev = null;

        while (end.next != null) {
            end = end.next;
        }
        Node newEnd = end;

        while (curr.data % 2 != 0 && curr != end) { // odd element assemble
            newEnd.next = curr;
            curr = curr.next;
            newEnd.next.next = null;
            newEnd = newEnd.next;
        }

        if (curr.data % 2 == 0) {// even case => even element assemble
            Head = curr;
            while (curr != end) {
                if (curr.data % 2 == 0) {
                    prev = curr;
                    curr = curr.next;
                } else {
                    prev.next = curr.next;
                    curr.next = null;
                    newEnd.next = curr;
                    newEnd = curr;
                    curr = prev.next;

                }
            }
        } else {
            prev = curr;
            if (newEnd != end && end.data % 2 != 0) {
                prev.next = end.next;
                end.next = null;
                newEnd.next = end;

            }
        }

    }

    public Node oddevenLL() {
        // base case =
        while (Head == null) {
            return Head;
        }
        Node odd = Head;
        Node even = Head.next;
        Node evenHead = even;

        while (even != null && even.next != null) {

            /// change pointers for odd list
            odd.next = odd.next.next;
            odd = odd.next;

            // change the pointers for even list
            even.next = even.next.next;
            even = even.next;
        }

        // assign even list at the end of odd list
        odd.next = evenHead;

        return Head;
    }

    public static Node mergeKlist(Node arr[], int last) {
        while (last != 0) {
            int i = 0, j = last;
            while (i < j) {
                arr[i] = SortedMerge(arr[i], arr[j]);
                i++;
                j--;
                if (i >= j) {
                    last = j;
                }
            }
        }
        return arr[0];
    }

    public static Node SortedMerge(Node a, Node b) {
        Node result = null;
        if (a == null) {
            return b;
        } else if (b == null) {
            return a;
        }
        if (a.data <= b.data) {
            result = a;
            result.next = SortedMerge(a.next, b);
        } else {
            result = b;
            result.next = SortedMerge(a, b.next);
        }
        return result;
    }

    public static void main(String[] args) {
        int arr1[] = { 1, 2, 3, 5, 6, 7, 4, 5 };

        Node l1 = arr2LL(arr1);

        printlist(l1);
        // printlist(l2);

        Node updated = rotateKtimes(l1, 2);

        printlist(updated);

    }

    public static void printlist(Node node) {
        while (node != null) {
            System.out.print(node.data + "->");
            node = node.next;
        }
        System.out.println("null");
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

    public static boolean hasCycle(Node head) {
        Map<Node, Boolean> visited = new HashMap<>();

        Node temp = head;
        while (temp != null) {
            if (visited.containsKey(temp)) {
                return true; // Cycle detected
            }
            visited.put(temp, true);
            temp = temp.next;
        }

        return false; // No cycle
    }

    public static Node add2numbers(Node head1, Node head2) {
        Node temp1 = head1;
        Node temp2 = head2;

        int carry = 0;
        Node res = new Node(-1);
        Node curr = res;

        while (temp1 != null || temp2 != null) {
            int sum = carry;

            if (temp1 != null) {
                sum += temp1.data;
            }
            if (temp2 != null) {
                sum += temp2.data;
            }
            Node newNode = new Node(sum % 10);
            carry = sum / 10;

            curr.next = newNode;
            curr = newNode;

            // update
            if (temp1 != null) {
                temp1 = temp1.next;
            }

            if (temp2 != null) {
                temp2 = temp2.next;
            }
        }

        if (carry != 0) {
            Node newNode = new Node(carry);
            curr.next = newNode;
        }

        return res.next;
    }

    public static Node startingPointofCycle(Node head) {

        // find the collision point

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // meeting point
            if (slow == fast) {
                slow = head;

                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                return slow;
            }
        }

        return null;

    }

    public static Node add1toaLinkedList(Node head) {
        int carry = helper(head);
        if (carry == 1) {
            Node newNode = new Node(1);
            newNode.next = head;
            head = newNode;
        }
        return head;

    }

    public static int helper(Node temp) {
        // base case
        if (temp == null) {
            return 1;
        }

        int carry = helper(temp.next);
        temp.data += carry;
        if (temp.data < 10) {
            return 0;
        }

        temp.data = 0;
        return 1;

    }

    static Node segregate(Node head) {
        if (head == null || head.next == null)
            return head;

        // Dummy heads
        Node zeroHead = new Node(-1);
        Node oneHead = new Node(-1);
        Node twoHead = new Node(-1);

        Node zero = zeroHead;
        Node one = oneHead;
        Node two = twoHead;

        Node temp = head;
        while (temp != null) {
            if (temp.data == 0) {
                zero.next = temp;
                zero = zero.next;
            } else if (temp.data == 1) {
                one.next = temp;
                one = one.next;
            } else {
                two.next = temp;
                two = two.next;
            }
            temp = temp.next;
        }

        // Join the three lists
        zero.next = (oneHead.next != null) ? oneHead.next : twoHead.next;
        one.next = twoHead.next;
        two.next = null; // end of list

        return zeroHead.next;
    }

    static Node reverseNodesinKgrps(Node head, int k) {

        if (head == null || k <= 1)
            return head;

        Node dummy = new Node(-1);
        dummy.next = head;
        Node prevGroupEnd = dummy;

        while (true) {
            Node kth = getKthnode(prevGroupEnd, k);
            if (kth == null)
                break;
            Node groupStart = prevGroupEnd.next;
            Node nextGroupStart = kth.next;

            // Reverse group
            Node prev = nextGroupStart, curr = groupStart;
            while (curr != nextGroupStart) {
                Node temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            // Connect previous group to new start
            prevGroupEnd.next = kth;
            prevGroupEnd = groupStart;
        }
        return dummy.next;
    }

    private static Node getKthnode(Node head, int k) {
        while (head != null && k > 0) {
            head = head.next;
            k--;
        }
        return head;
    }

    public static Node rotateKtimes(Node head, int k) {

        if (head == null || head.next == null || k == 0) {
            return head;
        }

        int length = 1;
        Node tail = head;

        while (tail.next != null) {
            length++;
            tail = tail.next;

        }

        if (k % length == 0) {
            return head;
        }

        k = k % length; // track how many time to rotate
        int lastEle = length - k; // find the tail node of the result ll

        // rotaition
        tail.next = head;
        Node newLastNode = findLastNode(head, lastEle);
        head = newLastNode.next;
        newLastNode.next = null;

        return head;
    }

    private static Node findLastNode(Node head, int k) {
        int cnt = 1;
        Node temp = head;
        while (cnt != k) {
            temp = temp.next;
            cnt++;
        }

        return temp;

    }

    public static void removeLoop(Node head) {
        if (head == null || head.next == null)
            return;

        Node slow = head, fast = head;
        boolean loopFound = false;

        // Detect loop (Floyd)
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                loopFound = true;
                break;
            }
        }
        if (!loopFound)
            return;

        // Find start of loop
        slow = head;
        if (slow == fast) {
            // Loop starts at head: move fast to last node in the loop
            while (fast.next != slow)
                fast = fast.next;
            fast.next = null;
            return;
        }

        // Move both pointers until they meet at node before loop start
        while (slow.next != fast.next) {
            slow = slow.next;
            fast = fast.next;
        }
        // fast.next (or slow.next) is loop start; unlink it
        fast.next = null;
    }

    static class randomNode {
        int data;
        randomNode next;
        randomNode random;

        public randomNode(int data) {
            this.data = data;
            this.next = null;
            this.random = null;
        }

    }

    public static randomNode cloneLinkedList(randomNode head) {
        // code here
        if (head == null) {
            return null;
        }

        randomNode temp = head;
        Map<randomNode, randomNode> hm = new HashMap<>();

        // first iteration O(N)
        // locate the newNode in the map

        while (temp != null) {
            randomNode newNode = new randomNode(temp.data);
            hm.put(temp, newNode);

            // move
            temp = temp.next;
        }

        temp = head; // reset

        // second iteration
        while (temp != null) {

            randomNode copyNode = hm.get(temp);
            copyNode.next = hm.get(temp.next);
            copyNode.random = hm.get(temp.random);

            // move
            temp = temp.next;
        }

        return hm.get(head);

    }

    public static void linkdelete(Node head, int m, int n) {
        Node curr = head;

        while (curr != null) {
            // Step 1: Skip m-1 nodes
            for (int i = 1; i < m && curr != null; i++) {
                curr = curr.next;
            }

            // If we reached end, break
            if (curr == null)
                return;

            // Step 2: Start deleting next n nodes
            Node temp = curr.next;
            for (int i = 0; i < n && temp != null; i++) {
                temp = temp.next;
            }

            // Step 3: Connect skipped part to the rest
            curr.next = temp;

            // Move current pointer forward
            curr = temp;
        }
    }
}
