import java.util.*;

public class Binarytree {
  static class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
      this.data = data;
      this.left = null;
      this.right = null;
    }
  }

  static class BinTree {
    static int idx = -1; // null

    public Node buildtree(int nodes[]) {
      idx++;
      if (nodes[idx] == -1) {
        return null;
      }
      Node newNode = new Node(nodes[idx]);
      newNode.left = buildtree(nodes);
      newNode.right = buildtree(nodes);

      return newNode;
    }

    public void preOrder(Node root) {
      if (root == null) {
        return;
      }
      System.out.print(root.data + " ");
      // left subtree
      preOrder(root.left);
      preOrder(root.right);
    }

    public void inorder(Node root) {
      if (root == null) {
        return;
      }
      inorder(root.left);
      System.out.print(root.data + " ");
      inorder(root.right);
    }

    public void Postorder(Node root) {
      if (root == null) {
        return;
      }
      // left
      Postorder(root.left);
      // right
      Postorder(root.right);
      // root
      System.out.print(root.data + " ");
    }

    public int height(Node root) {
      if (root == null) {
        return 0;
      }
      int leftheight = height(root.left); // search in left nodes
      int rightheight = height(root.right); // search in right nodes
      int height = Math.max(leftheight, rightheight) + 1; // check which has more depth adding own val 1
      return height;

    }

    public void levelorder(Node root) {
      if (root == null) {
        return;
      }
      Queue<Node> q = new LinkedList<>();
      q.add(root);

      while (!q.isEmpty()) {
        Node curr = q.poll();
        if (curr == null) {
          System.out.println();
          if (q.isEmpty()) {
            break;
          } else {
            q.add(null);
          }
        } else {
          System.out.print(" " + curr.data + " ");
          if (curr.left != null) {
            q.add(curr.left);
          }
          if (curr.right != null) {
            q.add(curr.right);
          }
        }
      }
    }

    public void kthlevelorder(Node root, int k) { // iterative approach
      if (root == null) {
        return;
      }
      // queue
      Queue<Node> q = new LinkedList<>();

      q.add(root);
      int level = 1;

      while (!q.isEmpty()) {
        int nodecount = q.size();
        if (level == k) {
          while (nodecount > 0) {
            Node node = q.poll();
            System.out.print(node.data + " ");
            nodecount--;
          }
          return;
        }
        while (nodecount > 0) {
          Node node = q.poll();
          if (node.left != null) {
            q.add(node.left);
          }
          if (node.right != null) {
            q.add(node.right);
          }
          nodecount--;
        }
        level++;
      }
    }

    public void kthlevelorder2(Node root, int level, int k) { // recursive approach
      if (root == null) {
        return;
      }
      if (level == k) {
        System.out.print(root.data + " ");
      }
      kthlevelorder2(root.left, level + 1, k);
      kthlevelorder2(root.right, level + 1, k);
    }

    public int count(Node root) {
      if (root == null) {
        return 0;
      }
      int leftcount = count(root.left);
      int rightcount = count(root.right);

      int count = leftcount + rightcount + 1;// +1 as self count i.e. tree count

      return count;
    }

    public int sumofNodes(Node root) {
      // base case
      if (root == null) {
        return 0;
      }
      int sum = 0;
      int leftsum = sumofNodes(root.left);
      int rightsum = sumofNodes(root.right);

      sum += leftsum + rightsum + root.data;

      return sum;

    }

    public boolean issubtree(Node root, Node subroot) {
      // base case
      if (root == null) {
        return false;
      }
      if (root.data == subroot.data) {
        if (isIdentical(root, subroot)) {
          return true;
        }
      }
      return issubtree(root.left, subroot) || issubtree(root.right, subroot);
    }

    public boolean isIdentical(Node node, Node subroot) {
      if (node == null && subroot == null) {
        return true;
      } else if (node == null || subroot == null || node.data != subroot.data) {
        return false;
      }
      if (!isIdentical(node.left, subroot.left)) {
        return false;
      }
      if (!isIdentical(node.right, subroot.right)) {
        return false;
      }

      return true;
    }

    public int diameter(Node root) {
      // base case
      if (root == null) {
        return 0;
      }

      int leftdiam = diameter(root.left);
      int rightdiam = diameter(root.right);

      int lh = height(root.left);
      int rh = height(root.right);

      int selfdiameter = lh + rh + 1; // passes through node

      return Math.max(Math.max(rightdiam, leftdiam), selfdiameter);
    }

    public Info diameter2(Node root) {
      // base case
      if (root == null) {
        return new Info(0, 0);
      }
      Info left = diameter2(root.left);
      Info right = diameter2(root.right);

      int Finaldiam = Math.max(Math.max(left.diameter, right.diameter), left.height + right.height + 1);
      int Finalheight = Math.max(left.height, right.height) + 1;
      return new Info(Finaldiam, Finalheight);
    }

    public void topview(Node root) {
      // level order
      Queue<Info2> q = new LinkedList<>();
      HashMap<Integer, Node> map = new HashMap<>();
      int min = 0, max = 0;

      q.add(new Info2(root, 0));

      while (!q.isEmpty()) {
        Info2 curr = q.poll();
        if (curr == null) {
          if (q.isEmpty()) {
            break;
          } else {
            q.add(null);
          }
        } else {
          if (!map.containsKey(curr.hd)) {// first time my hd is occuring
            map.put(curr.hd, curr.node);
          }
          if (curr.node.left != null) {
            q.add(new Info2(curr.node.left, curr.hd - 1));
            min = Math.min(curr.hd - 1, min); // track the minimum level can be reached
          }
          if (curr.node.right != null) {
            q.add(new Info2(curr.node.right, curr.hd + 1));
            max = Math.max(curr.hd + 1, max); // track the maximum level can be reached
          }
        }
      }
      // print topview
      for (int i = min; i <= max; i++) {// i => key
        System.out.print(map.get(i).data + " ");
      }
      System.out.println();
    }

    public Node lca(Node root, int n1, int n2) {// O(Nx)
      // 1. path -> root to node
      ArrayList<Node> path1 = new ArrayList<>();
      ArrayList<Node> path2 = new ArrayList<>();

      getpath(root, n1, path1);
      getpath(root, n2, path2);

      // lowest common ancestor
      // the loop will run till the vals get unequalled
      int i = 0;
      for (; i < path1.size() && i < path2.size(); i++) {
        if (path1.get(i) != path2.get(i)) {
          break;
        }
      }
      // last equal node -> i-1 th
      Node lca = path1.get(i - 1);

      return lca;
    }

    public boolean getpath(Node root, int n, ArrayList<Node> path) {// O(N)
      if (root == null) {
        return false;
      }
      path.add(root);
      if (root.data == n) {
        return true;
      }

      boolean foundleft = getpath(root.left, n, path);
      boolean foundright = getpath(root.right, n, path);

      if (foundleft || foundright) {
        return true;
      }

      path.remove(path.size() - 1);
      return false;
    }

    public Node lca2(Node root, int n1, int n2) {
      // base case
      if (root == null || root.data == n1 || root.data == n2) {
        return root;
      }

      Node leftLca = lca2(root.left, n1, n2);
      Node rightLca = lca2(root.right, n1, n2);

      // left lca= valid and rightlca - null
      if (rightLca == null) {
        return leftLca;
      }
      // opposite
      if (leftLca == null) {
        return rightLca;
      }

      return root;
    }

    public int minDist(Node root, int n1, int n2) {
      Node lca = lca2(root, n1, n2);
      int dist1 = lcadist(lca, n1);
      int dist2 = lcadist(lca, n2);

      return dist1 + dist2;
    }

    public int lcadist(Node root, int n) {
      if (root == null) {
        return -1;
      }
      if (root.data == n) {
        return 0;
      }
      int left = lcadist(root.left, n);
      int right = lcadist(root.right, n);

      if (left == -1 && right == -1) {
        return -1;
      } else if (left == -1) { // val at right
        return right + 1;
      } else { // val at left
        return left + 1;
      }
    }

    public int Kthancestor(Node root, int n, int k) {
      // base case
      if (root == null) {
        return -1;
      }

      if (root.data == n) {
        return 0;
      }

      int leftdist = Kthancestor(root.left, n, k);
      int rightdist = Kthancestor(root.right, n, k);

      if (leftdist == -1 && rightdist == -1) {
        return -1;
      }

      int max = Math.max(leftdist, rightdist);
      if (max + 1 == k) {
        System.out.println(root.data);
        ;
      }
      return max + 1;
    }

    public int sumtree(Node root) {
      if (root == null) {
        return 0;
      }

      int lefichild = sumtree(root.left);
      int rightchild = sumtree(root.right);

      int data = root.data;
      int newleft = root.left == null ? 0 : root.left.data;
      int newright = root.right == null ? 0 : root.right.data;
      root.data = newleft + newright + lefichild + rightchild;

      return data;
    }

    public int Minheight(Node root) {
      // base case
      if (root == null) {
        return 0;
      }
      int ld = Minheight(root.left);
      int rd = Minheight(root.right);
      if (root.left == null) {
        return rd + 1;
      }
      if (root.right == null) {
        return ld + 1;
      }
      return Math.min(ld, rd) + 1;
    }

    public int Maxdepth(Node root) {
      if (root == null) {
        return 0;
      }
      int ld = Maxdepth(root.left);
      int rd = Maxdepth(root.right);

      if (root.left == null) {
        return rd + 1;
      }
      if (root.right == null) {
        return ld + 1;
      }

      return Math.max(ld, rd) + 1;
    }

    public boolean isBalanced(Node root) {
      int left = height(root.left);
      int right = height(root.right);

      int diff = left - right;

      if (Math.abs(diff) <= 1) {
        System.out.println("the tree is balanced :)");
        return true;
      }
      System.out.println("the tree is not balanced");
      return false;
    }

    // assignments
    public boolean univalued(Node root) {
      // base case
      if (root == null) {
        return true;
      }

      if (root.left != null && root.data != root.right.data) {
        return false;
      }
      if (root.right != null && root.data != root.left.data) {
        return false;
      }
      boolean left = univalued(root.left);
      boolean right = univalued(root.right);

      return left && right;

    }

    public Node invert(Node root) {
      if (root == null) {
        return null;
      }
      Node left = invert(root.left);
      Node right = invert(root.right);

      // swapping
      root.left = right;
      root.right = left;

      return root;
    }

    public Node deleteasvalX(Node root, int target) {
      if (root == null) {
        return null;
      }
      // updation
      root.left = deleteasvalX(root.left, target);
      root.right = deleteasvalX(root.right, target);
      // check leaf node val
      if (root.left == null && root.right == null) {
        if (root.data == target) {
          return null;
        } else {
          return root;
        }
      }
      return root;
    }
  }

  static class Info {
    int diameter;
    int height;

    public Info(int diameter, int height) {
      this.diameter = diameter;
      this.height = height;
    }
  }

  static class Info2 {
    Node node;
    int hd;

    public Info2(Node node, int hd) {
      this.node = node;
      this.hd = hd;
    }
  }

  public static int maximumPath(Node root) {

    int maximum = Integer.MIN_VALUE;
    maxPath(root, maximum);

    return maximum;

  }

  public static int maxPath(Node root, int maximum) {
    if (root == null) {
      return 0;
    }

    int leftPath = maxPath(root.left, maximum);
    int rightPath = maxPath(root.right, maximum);

    maximum = Math.max(leftPath, rightPath) + root.data;
    return Math.max(leftPath, rightPath) + root.data;
  }

  public List<String> binaryTreePaths(Node root) {
    List<String> res = new ArrayList<>();
    helper(root, "", res);
    return res;
  }

  private static void helper(Node root, String path, List<String> res) {
    if (root == null)
      return;

    // build path
    if (path.length() == 0)
      path = Integer.toString(root.data);
    else
      path = path + "->" + root.data;

    // leaf node
    if (root.left == null && root.right == null) {
      res.add(path);
      return;
    }

    helper(root.left, path, res);
    helper(root.right, path, res);
  }

  public static List<List<Integer>> zigzagLevelOrder(Node root) {
    List<List<Integer>> res = new ArrayList<>();

    if (root == null) {
      return res;
    }

    Queue<Node> q = new LinkedList<>();
    q.add(root);
    boolean leftToright = true;

    while (!q.isEmpty()) {

      int size = q.size();

      List<Integer> currLevel = new ArrayList<>(Collections.nCopies(size, 0));

      for (int i = 0; i < size; i++) {
        Node node = q.poll();
        // find position
        int index = leftToright ? i : (size - 1 - i);
        // set value in the appropriate position
        currLevel.set(index, node.data);

        // check for the subroot
        if (node.left != null) {
          q.add(node.left);
        }

        if (node.right != null) {
          q.add(node.right);
        }
      }

      res.add(currLevel);
      leftToright = !leftToright; // toggling the direction
    }

    return res;
  }

  public static ArrayList<Integer> boundaryTraversal(Node root) {
    ArrayList<Integer> res = new ArrayList<>();
    if (root == null)
      return res;

    if (!isLeaf(root)) {
      res.add(root.data);
    }

    addLeftBoundary(root, res);
    addLeaves(root, res);
    addRightBoundary(root, res);

    return res;
  }

  public static void addLeftBoundary(Node root, ArrayList<Integer> res) {
    Node curr = root.left;
    while (curr != null) {
      if (!isLeaf(root)) {
        res.add(curr.data);
        return;
      }

      if (curr.left != null) {
        curr = curr.left;
      } else {
        curr = curr.right;
      }

    }
  }

  public static void addRightBoundary(Node root, ArrayList<Integer> res) {
    Node curr = root.right;

    while (curr != null) {
      if (!isLeaf(root)) {
        res.add(curr.data);
        return;
      }

      if (curr.right != null) {
        curr = curr.right;
      } else {
        curr = curr.left;
      }

    }
  }

  private static void addLeaves(Node root, ArrayList<Integer> res) {
    if (isLeaf(root)) {
      res.add(root.data);
      return;
    }
    if (root.left != null)
      addLeaves(root.left, res);
    if (root.right != null)
      addLeaves(root.right, res);
  }

  public static boolean isLeaf(Node root) {
    return root.left == null || root.right == null;
  }

  static class Tuple {
    Node node;
    int vertical;
    int level;

    public Tuple(Node node, int vertical, int level) {
      this.node = node;
      this.vertical = vertical;
      this.level = level;
    }
  }

  public static List<List<Integer>> verticalOrder(Node root) {

    List<List<Integer>> res = new ArrayList<>();
    if (root == null)
      return res;

    // vertical -> level -> min heap
    TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();

    Queue<Tuple> q = new LinkedList<>();
    q.add(new Tuple(root, 0, 0));

    while (!q.isEmpty()) {
      Tuple t = q.poll();

      Node curr = t.node;
      int vertical = t.vertical;
      int level = t.level;

      // new vertical order
      map.putIfAbsent(vertical, new TreeMap<>());
      // New level inside an existing vertical
      map.get(vertical).putIfAbsent(level, new PriorityQueue<>());

      map.get(vertical).get(level).add(curr.data);

      if (curr.left != null) {
        q.add(new Tuple(curr.left, vertical - 1, level + 1));
      }

      if (curr.right != null) {
        q.add(new Tuple(curr.right, vertical + 1, level + 1));
      }
    }

    // Build answer
    for (TreeMap<Integer, PriorityQueue<Integer>> levels : map.values()) {
      List<Integer> col = new ArrayList<>();
      for (PriorityQueue<Integer> pq : levels.values()) {
        while (!pq.isEmpty()) {
          col.add(pq.poll());
        }
      }
      res.add(col);
    }

    return res;
  }

  static class topViewNode {
    Node node;
    int vertical;

    public topViewNode(Node node, int vertical) {
      this.node = node;
      this.vertical = vertical;
    }
  }

  public static List<Integer> topView(Node root) {
    List<Integer> res = new ArrayList<>();
    // base case
    if (root == null) {
      return res;
    }

    Queue<topViewNode> q = new LinkedList<>();
    Map<Integer, Node> hm = new HashMap<>();
    int min = 0;
    int max = 0;

    q.add(new topViewNode(root, 0));

    while (!q.isEmpty()) {
      topViewNode node = q.poll();

      if (!hm.containsKey(node.vertical)) {// first time my hd is occuring
        hm.put(node.vertical, node.node);
      }
      if (node.node.left != null) {
        q.add(new topViewNode(node.node.left, node.vertical - 1));
        min = Math.min(node.vertical - 1, min); // track the minimum level can be reached
      }
      if (node.node.right != null) {
        q.add(new topViewNode(node.node.right, node.vertical + 1));
        max = Math.max(node.vertical + 1, max); // track the maximum level can be reached
      }
    }

    for (int i = min; i <= max; i++) {
      res.add(hm.get(i).data);
    }
    return res;

  }

  // System.out.println(lv);
  // System.out.println(lv);

  static class bottomView {
    Node node;
    int hd;

    bottomView(Node node, int hd) {
      this.node = node;
      this.hd = hd;
    }
  }

  public static List<Integer> bottomView(Node root) {
    List<Integer> res = new ArrayList<>();
    if (root == null)
      return res;

    // hd -> node value
    TreeMap<Integer, Integer> map = new TreeMap<>();
    Queue<bottomView> q = new LinkedList<>();

    q.add(new bottomView(root, 0));

    while (!q.isEmpty()) {
      bottomView curr = q.poll();

      // OVERWRITE for bottom view
      map.put(curr.hd, curr.node.data);

      if (curr.node.left != null) {
        q.add(new bottomView(curr.node.left, curr.hd - 1));
      }

      if (curr.node.right != null) {
        q.add(new bottomView(curr.node.right, curr.hd + 1));
      }
    }

    // Extract bottom view from map
    for (int val : map.values()) {
      res.add(val);
    }

    return res;
  }

  public static ArrayList<Integer> leftView(Node root) {
    ArrayList<Integer> res = new ArrayList<>();
    gettheleftnodes(root, res, 0);
    return res;
  }

  public static void gettheleftnodes(Node root, ArrayList<Integer> res, int level) {
    if (root == null) {
      return;
    }

    if (res.size() == level) {
      res.add(root.data);
    }

    gettheleftnodes(root.left, res, level + 1);
    gettheleftnodes(root.right, res, level + 1);
  }

  public static ArrayList<Integer> righttView(Node root) {
    ArrayList<Integer> res = new ArrayList<>();
    gettherightnodes(root, res, 0);
    return res;
  }

  public static void gettherightnodes(Node root, ArrayList<Integer> res, int level) {
    if (root == null) {
      return;
    }

    if (res.size() == level) {
      res.add(root.data);
    }

    gettherightnodes(root.right, res, level + 1);
    gettherightnodes(root.left, res, level + 1);
  }

  public static boolean isSymmetric(Node root) {

    if (root == null) {
      return true;
    }

    return checkforSymmetic(root.left, root.right);
  }

  public static boolean checkforSymmetic(Node r1, Node r2) {
    if (r1 == null || r2 == null) {
      return r1 == r2;
    }

    if (r1.data != r2.data) {
      return false;
    }

    return checkforSymmetic(r1.left, r2.right) && checkforSymmetic(r1.right, r2.left);
  }

  public static void main(String[] args) {
    Node root = new Node(3);
    root.left = new Node(5);
    root.right = new Node(1);
    root.right.left = new Node(0);
    root.right.right = new Node(8);
    root.left.left = new Node(6);
    root.left.right = new Node(2) ;
    root.left.right.left = new Node(7) ;
    root.left.right.right = new Node(4) ;

    // List<List<Integer>> res = new ArrayList<>();
    // res = verticalOrder(root);
    // List<Integer> lv = leftView(root);
    // List<Integer> rv = righttView(root);

    // System.out.println(rv);

   
    List<Integer> res = new ArrayList<>() ;
    Node target = new Node(5) ;
    res =  distanceK(root, 2, target);
    for(int i : res) {
      System.out.print(i + " ");
    }
    System.out.println();
  
  }

  public static int maxLevelSum(Node root) {
    if (root == null)
      return 0;

    Queue<Node> q = new LinkedList<>();
    q.add(root);

    int maxSum = Integer.MIN_VALUE;
    int maxLevel = 1;
    int level = 1;

    while (!q.isEmpty()) {
      int size = q.size();
      int levelSum = 0;

      for (int i = 0; i < size; i++) {
        Node node = q.poll();
        levelSum += node.data;

        if (node.left != null)
          q.add(node.left);
        if (node.right != null)
          q.add(node.right);
      }

      if (levelSum > maxSum) {
        maxSum = levelSum;
        maxLevel = level;
      }

      level++;
    }

    return maxLevel;
  }

  public static Node builtFromInandPost(int inOrder[], int preOrder[]) {

    // preorder -> Root Left Right
    // Inorder -> Left Root Right
    int n = inOrder.length;
    int m = preOrder.length;

    Map<Integer, Integer> hm = new HashMap<>();

    for (int i = 0; i < n; i++) {
      hm.put(inOrder[i], i);
    }

    Node root = builtHelper(inOrder, 0, n - 1, preOrder, 0, m - 1, hm);
    return root;

  }

  public static Node builtHelper(int inOrder[], int inStart, int inEnd, int preOrder[], int preStart, int preEnd,
      Map<Integer, Integer> map) {
    if (preStart > preEnd || inStart > inEnd) {
      return null;
    }

    // create the root
    Node root = new Node(preOrder[preStart]);

    int rootIndex = map.get(root.data);
    int numsLeft = rootIndex - inStart;

    root.left = builtHelper(inOrder, inStart, rootIndex - 1, preOrder, preStart + 1, preStart + numsLeft, map);
    root.right = builtHelper(inOrder, rootIndex + 1, inEnd, preOrder, preStart + numsLeft + 1, preEnd, map);

    return root;

  }

  public static Node buildtreeInOrderandPostOrder(int inOrder[], int postOrder[]) {

    int n = inOrder.length;
    int m = postOrder.length;

    // inOrder -> Left Root Right
    // postOrder -> Left Right Root

    Map<Integer, Integer> hm = new HashMap<>();
    // store the inOrder
    for (int i = 0; i < n; i++) {
      hm.put(inOrder[i], i);
    }

    Node root = buildTreeHelper(inOrder, 0, n - 1, postOrder, 0, m - 1, hm);

    return root;
  }

  public static Node buildTreeHelper(int inOrder[], int inStart, int inEnd, int postOrder[], int postStart, int postEnd,
      Map<Integer, Integer> map) {

    // boundary
    if (inStart > inEnd || postStart > postEnd) {
      return null;
    }

    // root creation
    Node root = new Node(postOrder[postEnd]);

    // calculate
    int rootIndex = map.get(root.data);
    int numsLeft = rootIndex - inStart;

    root.left = buildTreeHelper(inOrder, inStart, rootIndex - 1, postOrder, postStart, postStart + numsLeft - 1, map);

    root.right = buildTreeHelper(inOrder, rootIndex + 1, inEnd, postOrder, postStart + numsLeft, postEnd - 1, map);

    return root;

  }

  // Encodes a tree to a single string.
  public static String serialize(Node root) {

    if (root == null) {
      return "";
    }

    String sb = "";

    // perform the level order traversal
    Queue<Node> q = new LinkedList<>();
    q.add(root);

    while (!q.isEmpty()) {
      Node curr = q.poll();

      // convert to string
      if (curr == null) {
        sb += "n ";
      } else {
        sb += Integer.toString(curr.data) + " ";
      }
      // push the left and right even if they are null
      if (curr != null) {
        q.add(curr.left);
        q.add(curr.right);
      }
    }

    return sb;

  }

  // Decodes your encoded data to tree.
  public static Node deserialize(String data) {
    if (data.length() == 0) {
      return null;
    }

    String values[] = data.split(" ");
    Node root = new Node(Integer.parseInt(values[0]));

    Queue<Node> q = new LinkedList<>();
    q.add(root);

    for (int i = 1; i < values.length; i++) {
      Node curr = q.poll();

      if (!values[i].equals("n")) {
        Node left = new Node(Integer.parseInt(values[i]));
        curr.left = left;
        q.add(left);
      }

      if (!values[++i].equals("n")) {
        Node right = new Node(Integer.parseInt(values[i]));
        curr.right = right;
        q.add(right);
      }
    }

    return root;
  }

  public static List<Integer> morrisInorder(Node root) {
    List<Integer> inorder = new ArrayList<>();
    Node curr = root;

    while (curr != null) {

      // Case 1: left does NOT exist
      if (curr.left == null) {
        inorder.add(curr.data);
        curr = curr.right;
      }
      // Case 2: left exists
      else {
        Node prev = curr.left;

        // find rightmost node in left subtree
        while (prev.right != null && prev.right != curr) {
          prev = prev.right;
        }

        // create thread
        if (prev.right == null) {
          prev.right = curr;
          curr = curr.left;
        }
        // thread exists -> remove it
        else {
          prev.right = null;
          inorder.add(curr.data);
          curr = curr.right;
        }
      }
    }
    return inorder;
  }

  public static List<Integer> morrisPreorder(Node root) {
    List<Integer> preOrder = new ArrayList<>();
    Node curr = root;

    while (curr != null) {

      // if left doesnot exist
      if (curr.left == null) {
        preOrder.add(curr.data);
        curr = curr.right;
      }

      else {
        Node prev = curr.left;

        // find the right most node on left subtree
        while (prev.right != null && prev.right != curr) {
          prev = prev.right;
        }

        // now prev is at the rightmost node
        // prev -> curr

        // create the thread
        if (prev.right == null) {
          prev.right = curr;
          preOrder.add(curr.data);
          curr = curr.left;
        }

        // delete the thread
        // left iteration done
        else {
          prev.right = null;
          curr = curr.right;
        }
      }
    }

    return preOrder;
  }
  
  static Node prev = null ;
  public static void flattenTreeRecursive (Node root) {
        if(root == null) {
          return ;
        }

        flattenTreeRecursive(root.right);
        flattenTreeRecursive(root.left);


        //delete the left 
        //point to right 
        //update the prev
        root.right = prev ;
        root.left = null ;
        prev = root ;
  }

  public static void flattenMorris(Node root) {

    Node curr = root ;
    while (curr != null) {
       if(curr.left != null) {
          Node prev = curr.left ;
          
          //find the right most node
          while (prev.right != null) {
             prev = prev.right ;  
          }

          //connect
          prev.right = curr.right ;
          curr.right = curr.left ;//change the direction
          curr.left = null ;  
        }
        //update
        curr = curr.right ;
    }

  }

  public static List<Integer> distanceK(Node root, int k, Node target) {

    List<Integer> res = new ArrayList<>();
    if (root == null) return res;

    // 1. Mark parents
    Map<Node, Node> parent = new HashMap<>();
    markParent(root, parent);

    // 2. BFS from target
    Map<Node, Boolean> visited = new HashMap<>();
    Queue<Node> q = new LinkedList<>();

    q.offer(target);
    visited.put(target, true);

    int level = 0;

    while (!q.isEmpty()) { 
        if (level == k) break;
        int size = q.size();
        level++; 
        //BFS 
        for (int i = 0; i < size; i++) {
            Node curr = q.poll();

            // left
            if (curr.left != null && visited.get(curr.left) == null) {
                q.offer(curr.left);
                visited.put(curr.left, true);
            }

            // right
            if (curr.right != null && visited.get(curr.right) == null) {
                q.offer(curr.right);
                visited.put(curr.right, true);
            }

            // parent
            Node p = parent.get(curr);
            if (p != null && visited.get(p) == null) {
                q.offer(p);
                visited.put(p, true);
            }
        }
    }

    // nodes at distance k
    while (!q.isEmpty()) {
        res.add(q.poll().data);
    }

    return res;
}

  public static void markParent(Node root, Map<Node, Node> parent) {
    Queue<Node> q = new LinkedList<>();
    q.offer(root);

    while (!q.isEmpty()) {
        Node curr = q.poll();

        if (curr.left != null) {
            parent.put(curr.left, curr);
            q.offer(curr.left);
        }

        if (curr.right != null) {
            parent.put(curr.right, curr);
            q.offer(curr.right);
        }
    }
}

  public static int sumRootToLeaf(Node root) {
    return helper(root, 0);
}

private static int helper(Node node, int current) {
    if (node == null) {
        return 0;
    }

    // Shift left (binary multiply by 2) and add current bit
    current = (current << 1) | node.data;

    // If leaf node
    if (node.left == null && node.right == null) {
        return current;
    }

    return helper(node.left, current) 
         + helper(node.right, current);
}
}
