public class AVLtrees {
 //Node class
  static class  Node {
   int data , height ;
   Node left ,right ;
    Node(int data ) {
       this.data = data ;
       height =1 ;
    }   
 }
  public static Node root ;
  public static int  height(Node root){
       //base case
       if(root == null){return 0 ;}
       return root.height ;
  }
  public static void preorder(Node root){
       //base case
       if(root == null){return ;}
       System.out.print(root.data +" ");
       preorder(root.left);
       preorder(root.right);
  }
  public static int  bf(Node root){
       if(root == null){return 0 ;}
       // bf -> height of left - height of right
       return height(root.left)- height(root.right) ;
  }
  public static Node leftrotate(Node x){
       Node y = x.right;
       Node z = y.left ;
       
       //perform rotation
       y.left= x ;
       x.right = z ;

       //update height
       x.height = Math.max(height(x.left) , height(x.right)) +1;
       y.height = Math.max(height(y.left) , height(y.right)) +1;

       //return new root -> y
       return y ;
  }
  public static Node rightrotate(Node y){
       Node x = y.left ;
       Node z = x.right ;
       
       //update rotation
       x.right = y ;
       y.left= z ;

       //update height
       x.height = Math.max(height(x.left) , height(x.right)) +1;
       y.height = Math.max(height(y.left) , height(y.right)) +1;
       
       //return new root -> x
       return x ;
  }
  public static Node insert(Node root , int key){
       //base case
       if(root == null){return new Node(key);}

       //check where the key to place
       if(root.data>key){
              root.left = insert(root.left, key) ;
       }else if(root.data<key){
              root.right = insert(root.right, key)  ;}
        else{
              return root ; //duplicates are not allowed in avl trees
        }

        //update height
        root.height = 1 + Math.max(height(root.left), height(root.right));

        //calculate bf
        int balancefactor = bf(root);

        //cases
           
         // 1-> LL case
         if(balancefactor > 1 && key<root.left.data){return rightrotate(root);}
         // 2-> RR case
         if(balancefactor <-1 && key>root.right.data){return leftrotate(root);}
         // 3-> LR case
         if(balancefactor > 1 && key>root.left.data){
              root.left = leftrotate(root.left);
              return rightrotate(root.right);
         }
         // 4-> RL case
         if(balancefactor <-1 && key<root.right.data){
              root.right = rightrotate(root.right);
              return leftrotate(root);
         }
         return root ; //Balanced AVL
  }
  public static void main(String[] args) {
       root = insert(root,10);
       root = insert(root,20);
       root = insert(root,30);
       root = insert(root,40);
       root = insert(root,50);
       root = insert(root,25);
       preorder(root);
  }
}
