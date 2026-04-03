
import java.util.*;

import javax.swing.tree.TreeNode;

public class bst {
       static class Node {
              int data;
              Node left;
              Node right;

              Node(int data) {
                     this.data = data;
              }
       }

       static class binary_search_tree {
              public Node insert(Node root, int val) {
                     if (root == null) {
                            root = new Node(val);
                            return root;
                     }
                     if (root.data > val) {
                            // left subtree
                            root.left = insert(root.left, val);
                     } else {
                            // right subtree
                            root.right = insert(root.right, val);
                     }

                     return root;
              }

              public void inordertraversal(Node root) {
                     if (root == null) {
                            return;
                     }
                     // left
                     inordertraversal(root.left);
                     // root
                     System.out.print(root.data + " ");
                     // right
                     inordertraversal(root.right);

              }

              public boolean search(Node root, int key) {
                     if (root == null) {
                            return false;
                     }
                     if (root.data == key) {
                            return true;
                     }
                     if (root.data > key) {
                            return search(root.left, key);
                     } else {
                            return search(root.right, key);
                     }

              }

              public Node delete(Node root, int val) {
                     if (root.data < val) {
                            root.right = delete(root.right, val);// search in right
                     } else if (root.data > val) {
                            root.left = delete(root.left, val); // search in left
                     } else {// voila-> there you are
                             // case 1 - no child
                            if (root.left == null && root.right == null) {
                                   return null;// return null
                            }
                            // case 2 - single child
                            if (root.left == null) {
                                   return root.right;// return right child
                            } else if (root.right == null) {
                                   return root.left;// return left child
                            }
                            // case 3 - both children
                            Node inordersuccessor = findInordersuccessor(root.right);// find the inorder successor
                            root.data = inordersuccessor.data;// replace the node's data with it
                            delete(root.right, inordersuccessor.data);// delete the node
                     }
                     return root;
              }

              public Node findInordersuccessor(Node root) {
                     // Inoder successor is defined as the left most child in right subtree
                     while (root.left != null) {
                            root = root.left;
                     }
                     return root; // return right subtree node
              }

              public void printinRange(Node root, int k1, int k2) {
                     if (root == null) {
                            return;
                     }
                     // case - 01 ->>
                     if (k1 <= root.data && root.data <= k2) {// root lies between k1 and k2
                            printinRange(root.left, k1, k2);
                            System.out.print(root.data + " ");
                            printinRange(root.right, k1, k2);
                     } else if (root.data > k2) {// root's val greater than k2's value
                            printinRange(root.right, k1, k2);
                     } else {// root's val is smaller than k2's value
                            printinRange(root.left, k1, k2);
                     }
              }

              public void RoottoLeaf(Node root, ArrayList<Integer> List) {
                     // base case
                     if (root == null) {
                            return;
                     }
                     // add root val in the list
                     List.add(root.data);
                     // reached at the leaf position
                     if (root.left == null && root.right == null) {
                            printpath(List);
                     }

                     // recursion
                     RoottoLeaf(root.left, List);
                     RoottoLeaf(root.right, List);

                     List.remove(List.size() - 1);
              }

              public void printpath(ArrayList<Integer> AL) { // helper function to print the path arraylist
                     for (int i = 0; i < AL.size(); i++) {
                            System.out.print(AL.get(i) + "->");
                     }
                     System.out.println("Null");
              }

              public boolean isValid(Node root, Node min, Node max) {
                     if (root == null) {
                            return true;
                     }
                     if (min != null && root.data <= min.data) {
                            return false;
                     } else if (max != null && root.data >= max.data) {
                            return false;
                     }
                     return isValid(root.left, min, root) && isValid(root.right, root, max);
              }

              public Node Mirror(Node root) {
                     // base case
                     if (root == null) {
                            return null;
                     }
                     // left subtree
                     Node ls = Mirror(root.left);
                     // right subtree
                     Node rs = Mirror(root.right);

                     // swapping
                     root.left = rs;
                     root.right = ls;

                     return root;

              }

              public void preorder(Node root) {
                     if (root == null) {
                            return;
                     }
                     System.out.print(root.data + " ");
                     preorder(root.left);
                     preorder(root.right);
              }

              public Node createBst(ArrayList<Integer> List, int si, int ei) {
                     if (si > ei) {
                            return null;
                     }
                     int mid = (si + ei) / 2;
                     Node root = new Node(List.get(mid));
                     root.left = createBst(List, si, mid - 1);// left subtree
                     root.right = createBst(List, mid + 1, ei);// right subtree
                     return root;
              }

              public Node ConverttoaBalancedBST(Node root) {
                     // inorder sequence
                     ArrayList<Integer> Inorder = new ArrayList<>();
                     getInorder(root, Inorder);

                     // balanced bst creation using the sorted inorder
                     root = createBst(Inorder, 0, Inorder.size() - 1);
                     return root;
              }

              static class Info {
                     boolean Bst;
                     int size;
                     int min;
                     int max;

                     public Info(boolean Bst, int size, int min, int max) {
                            this.Bst = Bst;
                            this.size = size;
                            this.min = min;
                            this.max = max;
                     }

              }

              static int maxBst = 0;

              public Info largeBst(Node root) {
                     // Base case
                     if (root == null) {
                            return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
                     }

                     // recursion
                     Info leftInfo = largeBst(root.left);
                     Info rightInfo = largeBst(root.right);
                     int size = leftInfo.size + rightInfo.size + 1;

                     // calculation
                     int min = Math.min(root.data, Math.min(leftInfo.min, rightInfo.min));
                     int max = Math.max(root.data, Math.max(leftInfo.max, rightInfo.max));

                     // conditions
                     // 1-> invalid
                     if (root.data <= leftInfo.max || root.data >= rightInfo.min) {
                            return new Info(false, size, min, max);
                     }
                     if (leftInfo.Bst && rightInfo.Bst) {// bst
                            maxBst = Math.max(maxBst, size);
                            // System.out.println("Now max bst becomes"+ maxBst);
                            return new Info(true, size, min, max);
                     }

                     return new Info(false, size, min, max);
              }

              public Node mergeBsts(Node root1, Node root2) {
                     // step ->1
                     // calculate inorder of 1st bst
                     ArrayList<Integer> l1 = new ArrayList<>();
                     getInorder(root1, l1);
                     // step ->2
                     ArrayList<Integer> l2 = new ArrayList<>();
                     getInorder(root2, l2);

                     // step ->3
                     // merge
                     ArrayList<Integer> finalarray = new ArrayList<>();
                     int i = 0, j = 0;
                     while (i < l1.size() && j < l2.size()) {
                            // comparision
                            if (l1.get(i) <= l2.get(j)) {
                                   finalarray.add(l1.get(i));
                                   i++;
                            } else {
                                   finalarray.add(l2.get(j));
                                   j++;
                            }
                     }

                     // remaining elements
                     while (i < l1.size()) {
                            finalarray.add(l1.get(i));
                            i++;
                     }
                     while (j < l2.size()) {
                            finalarray.add(l2.get(j));
                            j++;
                     }

                     // step ->4
                     return createBst(finalarray, 0, finalarray.size() - 1);

              }

              public int printthesum(Node root, int k1, int k2) {
                     if (root == null) {
                            return 0;
                     }
                     int sum = 0;
                     if (root.data >= k1 && root.data <= k2) {
                            printthesum(root.left, k1, k2);
                            sum += root.data;
                            printthesum(root.right, k1, k2);
                     } else if (root.data >= k2) {
                            printthesum(root.right, k1, k2);
                     } else {
                            printthesum(root.left, k1, k2);
                     }

                     return sum;
              }

              static int sum;

              public int rangeSumBST(Node root, int low, int high) {
                     // variable to return
                     if (root == null) {
                            return 0;
                     }
                     // left
                     sum += rangeSumBST(root.left, low, high);
                     // root

                     // element present in the range
                     if (root.data <= high && root.data >= low) {
                            sum += root.data;
                     }

                     sum += rangeSumBST(root.right, low, high);

                     return sum;
              }

              public int maxSumBST(Node root) {
                     // base case
                     if (root == null) {
                            return 0;
                     } else { // helper function which returns maximum sum of bst
                            helper(root);
                            // if result is greater than 0 then simply return it !
                            return result < 0 ? 0 : result;
                     }
              }

              // helper function will return a int []
              // which have left max element in 0 idx
              // which have right min element in 1 idx
              public int[] helper(Node curr) {
                     // base case
                     if (curr == null) { // non node case
                            return new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE, 0 };
                     }
                     int left[] = helper(curr.left);
                     int right[] = helper(curr.right);

                     // 0 index -> largest in my left subtree
                     // 1 index -> minmum in my right subtree

                     // no bst case
                     if (left[1] >= curr.data || right[0] <= curr.data) {
                            return new int[] { Integer.MIN_VALUE, Integer.MAX_VALUE, 0 };
                     }
                     // calculate the sum
                     int sum = left[2] + right[2] + curr.data;
                     result = Math.max(sum, result);// compatre the sum with result
                     return new int[] { Math.min(curr.data, left[0]), Math.max(curr.data, right[1]), sum };
                     // here we are returning an array which has left max at 0 , right min at 1 , and
                     // sum at 2
              }

              static int result;// variable to return

              static int sum2;

              public void helper2(Node root) {

                     if (root != null)// first go the right subtree
                     {
                            helper2(root.right);
                            sum2 += root.data;
                            root.data = sum2;
                            helper2(root.left); // left subtree
                     }
              }

              public Node bstToGst(Node root) {
                     sum2 = 0;
                     helper2(root);
                     return root;
              }
       }

       public static void main(String[] args) {
              int preorder[] = { 3, 1, 2, 4, 5, 8 };
              Node root = buildBST(preorder);
              System.out.println(findTarget(root, 14));

       }

       static int count;

       public static int kthclosestelement(Node root, int k) {
              // create a stack
              Stack<Node> s = new Stack<>();
              int target = 0;
              int ans = 0;
              Node curr = root;

              while (!s.isEmpty() || curr != null) {
                     while (curr != null) {
                            s.push(curr);
                            curr = curr.left;
                     }
                     curr = s.pop();
                     target++;

                     if (target == k) {
                            ans = root.data;
                            break;
                     }
                     curr = curr.right;
              }
              return ans;
       }

       public static Node lca(Node root, Node a, Node b) {
              // base case
              if (root == null) {
                     return null;
              }

              // 1st case (both are on left)
              if (root.data > a.data && root.data > b.data) {
                     return lca(root.left, a, b);
              }
              // 2nd case (both are on right)

              if (root.data < a.data && root.data < b.data) {
                     return lca(root.right, a, b);
              }

              /// root is the intersection point
              return root;
       }

       public static int kthSmallest(Node root, int k) {
              // base case
              if (root == null) {
                     return 0;
              }

              ArrayList<Integer> Inorder = new ArrayList<>();

              // add elements
              getInorder(root, Inorder);

              if (Inorder.size() < k) {
                     return -1;
              }
              // return the kth value
              return Inorder.get(k);

       }

       public static void getInorder(Node root, ArrayList<Integer> list) {
              if (root == null) {
                     return;
              }
              getInorder(root.left, list);
              list.add(root.data);
              getInorder(root.right, list);
       }

       public static Node buildBST(int preorder[]) {
              int n = preorder.length;
              if (n == 0) {
                     return null;
              }

              return buildHelper(preorder, 0, n);

       }

       public static Node buildHelper(int arr[], int si, int ei) {
              if (si >= ei) {
                     return null;
              }
              Node root = new Node(arr[si]);
              // idea
              // find out the points of left and right
              int leftEnd = ei;
              for (int i = si + 1; i < ei; i++) {
                     if (arr[i] > root.data) {
                            leftEnd = i;
                            break;
                     }
              }

              root.left = buildHelper(arr, si + 1, leftEnd);
              root.right = buildHelper(arr, leftEnd, ei);

              return root;
       }

       public static Node inOrderSuccessor(Node root, Node key) {

              // left most node of right subtree
              Node successor = null;

              while (root != null) {

                     if (root.data > key.data) {
                            successor = root; // store potential successor
                            root = root.left; // try to find smaller greater value
                     } else {
                            root = root.right;
                     }
              }

              return successor;
       }

       public static Node predecessor(Node root, Node key) {

              // Right most node of left subtree
              Node predecessor = null;

              while (root != null) {
                     if (root.data < key.data) {
                            predecessor = root;
                            root = root.right; // try to find better
                     } else {
                            root = root.left;
                     }
              }

              return predecessor;

       }

       class BSTIterator {

              private Stack<Node> stack = new Stack<>();
              private boolean reverse; // true = reverse inorder

              public BSTIterator(Node root, boolean isReverse) {
                     reverse = isReverse;
                     pushAll(root);
              }

              public boolean hasNext() {
                     return !stack.isEmpty();
              }

              public int next() {
                     Node temp = stack.pop();

                     if (!reverse) {
                            pushAll(temp.right); // normal inorder
                     } else {
                            pushAll(temp.left); // reverse inorder
                     }

                     return temp.data;
              }

              private void pushAll(Node root) {
                     while (root != null) {
                            stack.push(root);

                            if (!reverse) {
                                   root = root.left; // normal
                            } else {
                                   root = root.right; // reverse
                            }
                     }
              }
       }

       public static boolean findTarget(Node root, int k) {
              if (root == null)
                     return false;

              List<Integer> list = new ArrayList<>();
              getInorder(root, list);

              int left = 0;
              int right = list.size() - 1;

              while (left < right) {
                     int sum = list.get(left) + list.get(right);

                     if (sum == k) {
                            return true;
                     } else if (sum < k) {
                            left++;
                     } else {
                            right--;
                     }
              }

              return false;
       }

       public static void getInorder(Node root, List<Integer> list) {
              if (root == null)
                     return;

              getInorder(root.left, list);
              list.add(root.data);
              getInorder(root.right, list);
       }

       public static Node first;
       public static Node prev;
       public static Node middle;
       public static Node last;

       public static void recoverTree(Node root) {
              // initialize
              first = middle = last = null;

              prev = new Node(Integer.MIN_VALUE);

              // perform inorder
              inorder(root);

              // non adjacent
              if (first != null && last != null) {
                     int t = first.data;
                     first.data = last.data;
                     last.data = t;
              }
              // adjacent
              else if (first != null && middle != null) {
                     int t = first.data;
                     first.data = middle.data;
                     middle.data = t;
              }
       }

       public static void inorder(Node root) {
              if (root == null) {
                     return;
              }
              inorder(root.left);
              if (prev != null && prev.data > root.data) {

                     // perform work
                     if (first == null) {
                            first = prev;
                            middle = root;
                     } else {
                            last = root;
                     }
              }
              prev = root;
              inorder(root.right);
       }

       static class NodeValue {
              int minNode, maxNode, maxSize;

              NodeValue(int minNode, int maxNode, int maxSize) {
                     this.minNode = minNode;
                     this.maxNode = maxNode;
                     this.maxSize = maxSize;
              }
       }

       public static int largestBST(Node root) {
              return helper(root).maxSize;
       }

       public static NodeValue helper(Node root) {

              if (root == null) {
                     return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
              }

              NodeValue left = helper(root.left);
              NodeValue right = helper(root.right);

              if (left.maxNode < root.data && right.minNode > root.data) {
                     return new NodeValue(
                                   Math.min(root.data, left.minNode), // minimum
                                   Math.max(root.data, right.maxNode), // maximum
                                   left.maxSize + right.maxSize + 1 // size
                     );
              }

              return new NodeValue(Integer.MIN_VALUE, Integer.MAX_VALUE,
                            Math.max(left.maxSize, right.maxSize));
       }

}