import java.util.*;

public class Binarytree {
    static  class  Node {
        int data ;
        Node left ;
        Node right ;
         Node (int data) {
            this.data = data; 
            this.left = null ;
            this.right = null ;
         }
    }
    static  class  BinTree {
    static  int idx = -1 ; //null
  public Node buildtree (int nodes[]) {
           idx ++;
           if(nodes[idx] == -1){
            return  null ;
           }
           Node newNode = new  Node(nodes[idx]) ;
           newNode.left = buildtree(nodes);
           newNode.right = buildtree(nodes) ;
           
           return  newNode ;
        }   
  public void preOrder(Node root) {
    if(root == null) {return ;}
    System.out.print(root.data +" ");
    //left subtree
    preOrder(root.left);
    preOrder(root.right);
 }
  public void inorder(Node root) {
    if(root == null ){
        return;
    }
     inorder(root.left);
     System.out.print(root.data +" ");
     inorder(root.right);
  }
  public void Postorder(Node root) {
    if(root == null) {return;}
    //left
    Postorder(root.left);
    //right-=
    Postorder(root.right);
    //root
    System.out.print(root.data +" ");
  }
  public int  height(Node root){
    if(root == null){return 0 ;}
    int leftheight  = height(root.left);
    int rightheight = height(root.right) ;
    
    int height = Math.max( leftheight, rightheight) +1;
 //body
    return  height ;

}
  public void levelorder(Node root){
    if(root == null){
        return;
  }
   Queue <Node> q= new  LinkedList<>() ;
   q.add(root);
   q.add(null) ;

   while(!q.isEmpty()){
    Node curr = q.poll() ;
    if(curr == null ){
        System.out.println();
        if(q.isEmpty()){
            break;
        }else{
            q.add(null) ;
        }
    }else{
        System.out.print (" "+curr.data +" ");
        if(curr.left != null) {
            q.add(curr.left) ;
        }
        if(curr.right != null){
            q.add(curr.right) ;
        }
    }
   }
}
  public void kthlevelorder( Node root , int k) { // iterative approach
    if(root == null) {return;}
    // queue
    Queue <Node > q =  new LinkedList<>() ;

     q.add(root) ;
     int level = 1 ;

     while(!q.isEmpty()){
     int nodecount = q.size() ;
      if(level == k){
        while(nodecount> 0)
      {
        Node node = q.poll() ;
        System.out.print(node.data + " " );
        nodecount -- ;
      }
      return;
      }
       while ( nodecount> 0) {
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
  public void kthlevelorder2 ( Node root , int level , int k){ // recursive approach
    if(root == null) {return;}
    if(level == k){System.out.print(root.data +" ");}
    kthlevelorder2(root.left, level+1, k);
    kthlevelorder2(root.right, level+1, k);
  }
  public int  count(Node root) {
    if(root == null){return  0 ;}
    int leftcount =  count(root.left);
    int rightcount = count(root.right);

    int count = leftcount + rightcount +1 ;// +1 as self count i.e. tree count

    return  count ;
   }
  public int  sumofNodes(Node root){
    //base case
     if(root ==  null){
        return  0 ;
     }
     int sum  = 0 ;
     int leftsum = sumofNodes(root.left);
     int rightsum = sumofNodes(root.right) ;

      sum+= leftsum + rightsum + root.data ;

      return  sum ;

 }  
  public boolean issubtree( Node root , Node subroot) { 
   // base case
    if(root == null){
        return  false ;
    }
    if(root.data == subroot.data){
    if(isIdentical(root, subroot)){
        return  true;
    }
    }
     return  issubtree(root.left, subroot)||  issubtree(root.right, subroot);
   }
  public boolean isIdentical(Node node , Node subroot){
    if(node == null && subroot == null){
        return  true;
    }else if(node == null || subroot == null || node.data != subroot.data){
        return  false;
    }
     if(!isIdentical(node.left, subroot.left)){
        return  false;
     }
     if(!isIdentical(node.right, subroot.right)){
        return  false ;
     }
     
     return  true;
  }
  public int  diameter(Node root){
    //base case 
    if(root == null ){
      return 0 ;
    }

    int leftdiam = diameter(root.left) ;
    int rightdiam = diameter(root.right);

    int lh = height(root.left);
    int rh = height(root.right);

    int selfdiameter =  lh+ rh +1 ; // passes through node

     return Math.max (Math.max(rightdiam, leftdiam) , selfdiameter) ;
  }
  public Info diameter2(Node root){
  //base case 
   if(root == null ){
    return new Info(0, 0) ;
   }
    Info left = diameter2(root.left);
    Info right =  diameter2(root.right);
    
    int Finaldiam =  Math.max(Math.max(left.diameter, right.diameter) , left.height + right.height +1);
    int Finalheight =  Math.max(left.height, right.height) +1 ;
    return new Info (Finaldiam ,Finalheight) ;
}
  public void topview(Node root){
  //level order 
  Queue <Info2> q= new  LinkedList<>() ;
  HashMap<Integer , Node> map = new HashMap<>() ;
  int min=0 , max =0 ;

  q.add(new Info2(root, 0));
  q.add(null) ;

  while(!q.isEmpty()){
  Info2 curr = q.poll() ;
  if(curr == null ){
      if(q.isEmpty()){
          break;
      }else{
          q.add(null) ;
      }
  }else{
    if(! map.containsKey(curr.hd)) {//first time my hd is occuring
    map.put(curr.hd, curr.node) ;
    }
    if(curr.node.left !=  null){
    q.add(new Info2(curr.node.left, curr.hd-1));
    min = Math.min(curr.hd-1, min) ;
  }
  if(curr.node.right != null){
    q.add(new Info2(curr.node.right, curr.hd+1)) ;
    max = Math.max(curr.hd+1, max) ;
  }
  }
  }
  //print topview
  for(int i = min ;i <=max ;i++){//i => key
    System.out.print(map.get(i).data+" ");
  }System.out.println();
 }
  public Node lca(Node root , int n1 , int n2){//O(Nx)
    //1. path -> root to node
    ArrayList <Node> path1 = new ArrayList<>() ;
    ArrayList <Node> path2 = new ArrayList<>() ;

    getpath(root , n1 , path1) ;
    getpath(root , n2 , path2) ;

    //lowest common  ancestor
     // the loop will run till the vals  get unequalled
    int i =0 ;
    for(; i<path1.size() && i< path2.size() ;i++) {
        if(path1.get(i) != path2.get(i)) {
            break;
        }
    }
    //last equal node -> i-1 th
    Node lca = path1.get(i-1) ;

    return  lca ;
  }
  public boolean getpath(Node root ,int n , ArrayList<Node>path){//O(N)
    if(root == null){return false ;}
    path.add(root);
    if(root.data == n){
      return  true ;
    }

     boolean foundleft = getpath(root.left, n, path);
     boolean foundright = getpath(root.right, n, path) ;

     if(foundleft || foundright){
      return  true ;
     }

     path.remove(path.size()-1) ;
     return false ;
  }
  public Node lca2(Node root , int n1 , int n2){ 
    //base case
    if(  root == null || root.data == n1  || root.data ==n2){
      return root ;
    }

    Node leftLca = lca2(root.left, n1, n2) ;
    Node rightLca = lca2(root.right , n1  ,n2);
  

    //left lca=  valid and rightlca - null
     if(rightLca == null){
      return leftLca ;
     }
     //opposite
     if(leftLca == null){
      return  rightLca ;
     }

     return  root ;
  }
  public int  minDist (Node root , int n1 , int n2){
     Node lca = lca2(root, n1, n2) ;
     int dist1 = lcadist(lca, n1) ;
     int dist2 = lcadist(lca,n2);
      
     return dist1+dist2 ;
  }
  public int  lcadist(Node root , int n){
    if(root == null) {
      return -1 ;
    }
    if(root.data == n){
       return 0 ;
    }
     int left = lcadist(root.left, n) ;
     int right = lcadist(root.right, n) ;

     if(left == -1 && right == -1){
      return -1 ;
     }
     else if(left == -1){ //val at right
      return right+1;
     }
     else { //val at left
      return left+1 ;
     }
  }
  public int  Kthancestor(Node root , int n , int  k){
// base case
   if(root == null){
    return -1 ;
   }

    if(root.data == n ){
      return 0 ;
    }

    int leftdist = Kthancestor(root.left, n, k);
    int rightdist  = Kthancestor(root.right, n, k) ;

    if(leftdist == -1 && rightdist ==  -1){
      return -1 ;
    }

   int max = Math.max(leftdist, rightdist) ;
   if(max+1 == k){
   System.out.println(root.data);;
   }
   return max +1 ;
  }
  public int  sumtree(Node root){
     if(root == null){
      return 0;
     }
    
    int lefichild = sumtree(root.left);
    int rightchild = sumtree(root.right) ;

    int data = root.data ;
    int newleft = root.left == null ? 0: root.left.data ;
    int newright= root.right == null ? 0 : root.right.data ;
    root.data =  newleft + newright + lefichild + rightchild ;

    return data ;
  }
  public int  Minheight(Node root){
    //base case
    if(root == null ){
      return 0 ;
    }
    int ld = Minheight(root.left);
    int rd = Minheight(root.right);
    if(root.left == null){return rd+1 ;}
    if(root.right == null){return ld+1 ;}
    return Math.min(ld, rd)+1 ;
  }  
  public int  Maxdepth (Node root) {
  if(root == null){
    return 0 ;
  }
  int ld = Maxdepth(root.left);
  int rd = Maxdepth(root.right) ;

  if(root.left == null){return rd+1 ;}
  if(root.right == null) {return ld+1 ;}

  return Math.max(ld, rd) +1 ;
}
  public boolean isBalanced(Node root){
     int left = height(root.left) ;
     int right = height(root.right) ;

     int diff = left - right ;

     if(Math.abs(diff) <= 1 )
     {
      System.out.println("the tree is balanced :)");
      return true ;
    }
    System.out.println("the tree is not balanced");
    return false ;
  }
  //assignments 
  public boolean univalued(Node root) {
    //base case
     if(root == null){return true ;}
    
    if(root.left != null && root.data != root.right.data) {return false;}
    if(root.right!= null && root.data != root.left.data) {return false;}
    boolean left = univalued(root.left);
    boolean right= univalued(root.right);

    return left && right ;

  }
  public  Node invert(Node root){
    if(root == null){
      return null ;
    }
    Node left = invert(root.left);
    Node right = invert(root.right) ;

    //swapping
    root.left = right ;
    root.right = left ;

    return root ;
  }
  public Node deleteasvalX(Node root , int target){
     if(root == null){return null ;}
      //updation
     root.left = deleteasvalX(root.left, target)  ;
     root.right =deleteasvalX(root.right, target) ;
     //check leaf node val
     if(root.left == null && root.right == null){
      if(root.data == target){
      return null ;}else {
      return root ;
     }
    }
     return root ;
  }
}
    static class Info{
     int diameter ;
     int height ;
      public Info(int diameter , int height){     
        this.diameter=  diameter;
        this.height = height;
    } 
  }
    static class Info2{
    Node node ;
    int hd ;

    public Info2(Node node , int hd) {
      this.node = node ;
      this.hd = hd ;
    }
   } 
 public static void main(String[] args) {
    Node root = new Node(10);
    root.left = new  Node(20);
    root.right = new Node(20);
    root.left.left = new Node(20) ;
    root.left.right = new Node(20) ;
    root.left.left.left = new Node(20) ;
    root.left.left.right = new Node(20) ;


    root.right.right = new Node(30) ;
    root.right.right.right = new Node(20);
    root.right.right.left = new Node(40) ;
    // root.left.left.left = new Node(7);
    // root.left.left.left.left = new Node(8);
    // root.right.right = new Node(6) ;
    
     /*
               10
             /   \
            11     12 
          /          \
         19           19
     */  
   
     BinTree b =  new BinTree() ;
     Node s = b.deleteasvalX(root, 20) ;
     b.preOrder(s);
    } 
    }

