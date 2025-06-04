
import java.util.*;
public class bst {
        static class Node{
              int data ;
              Node left;
              Node  right ;
              Node(int data){
                     this.data = data ;
              }
       }
        static  class binary_search_tree{
        public Node insert(Node root , int val){
        if(root == null){
        root = new Node(val) ;
        return root ;
        }
        if(root.data >val){
              //left subtree
              root.left = insert(root.left, val) ;
        }else{
              //right subtree
              root.right = insert(root.right, val) ;
        }

        return root ;
       }
        public void inordertraversal(Node root){
              if(root == null){return ;}
               //left
               inordertraversal(root.left);
               //root
               System.out.print(root.data +" ");
               //right
               inordertraversal(root.right);

       }
        public boolean search( Node root , int key) {
              if(root == null) {
                     return false ;
              }
              if(root.data == key) {
                     return true ;
              }
              if(root.data >key){
                      return search(root.left, key);
              }
              else{
                      return search(root.right, key) ;
              }
              
              
       }      
        public Node delete(Node root , int val){
              if(root.data <val){
                     root.right = delete(root.right, val) ;//search in right
              }
              else if(root.data>val){
                     root.left = delete(root.left, val) ; //search in left
              }
              else{//voila-> there you are
                     //case 1 - no child
                     if(root.left == null && root.right == null){
                            return null ;//return null 
                     }
                     //case 2 - single child
                     if(root.left == null){
                            return root.right ;//return right child
                     }else if(root.right == null){
                            return root.left ;//return left child
                     }
                     //case 3 - both children
                     Node inordersuccessor = findInordersuccessor(root.right) ;//find the inorder successor
                     root.data = inordersuccessor.data ;// replace the node's data with it
                     delete(root.right, inordersuccessor.data) ;// delete the node
              }
              return root ;
       }
        public Node findInordersuccessor(Node root){
             //Inoder successor is defined as the left most child in right subtree
              while(root.left != null){
                     root = root.left ;
              }
              return root ; //return right subtree node
       }
        public void printinRange(Node root ,int k1 , int k2){
              if(root == null){
                     return ;
              }
              //case - 01 ->>
              if(k1<=root.data && root.data<=k2) {//root lies between k1 and k2
              printinRange(root.left, k1, k2);
              System.out.print(root.data +" ");
              printinRange(root.right, k1, k2);
              }
              else if(root.data>k2){// root's val greater than k2's value
                     printinRange(root.right, k1, k2);
              }
              else {//root's val is smaller than k2's value
                     printinRange(root.left, k1, k2);
              }
       } 
        public void RoottoLeaf(Node root , ArrayList<Integer>List){
             //base case
              if(root == null){return ;}
              //add root val in the list
              List.add(root.data) ;
              //reached at the leaf position
              if(root.left == null && root.right == null){
                printpath(List);
              }


              //recursion
              RoottoLeaf(root.left, List);
              RoottoLeaf(root.right, List);

              List.remove(List.size()-1) ;
       }
        public void printpath(ArrayList<Integer>AL){ //helper function to print the path arraylist
              for(int i=0 ;i<AL.size() ;i++){
                     System.out.print(AL.get(i)+"->");
              }
              System.out.println("Null");
       }
        public boolean isValid(Node root , Node min , Node max){
              if(root == null){return true ;}
              if(min != null && root.data <= min.data){return false ;}
              else if (max != null && root.data >= max.data){return false ; }
              return isValid(root.left, min, root) && isValid(root.right, root, max);
       }
        public Node Mirror(Node root){
              //base case 
              if(root == null){return null ;}
              //left subtree
              Node ls= Mirror(root.left);
              //right subtree
              Node rs = Mirror(root.right);

              //swapping
              root.left = rs ;
              root.right = ls ;

              return root ;

       }
        public void preorder(Node root){
              if(root == null){return  ;}
              System.out.print(root.data +" ");
              preorder(root.left);
              preorder(root.right);
       }
        public Node createBst(ArrayList<Integer>List, int si , int ei){
              if(si>ei){return null ;}
              int mid = (si+ei) /2 ;
              Node root = new Node(List.get(mid)) ;
              root.left = createBst(List, si, mid-1) ;//left subtree
              root.right = createBst(List, mid+1, ei) ;//right subtree
              return  root ;
       }
        public Node ConverttoaBalancedBST(Node root){
              //inorder sequence
              ArrayList<Integer>Inorder = new ArrayList<>() ;
              getInorder (root,Inorder);
                    
             //balanced bst creation using the sorted inorder
               root = createBst(Inorder, 0, Inorder.size() -1);
               return root ;
       }
        public void getInorder (Node root , ArrayList<Integer>list){
              if(root == null){
              return ;
              }
              getInorder(root.left, list);
              list.add(root.data) ;
              getInorder(root.right, list);
       }
        static class  Info {
              boolean Bst ;
              int size ;
              int min ; 
              int max ;

                public Info(boolean Bst , int size , int min  , int max){
                     this.Bst = Bst ;
                     this.size = size ;
                     this.min = min ;
                     this.max = max ;
                }
                     
              }
        static int maxBst = 0;
        public  Info largeBst(Node root){
                     //Base case 
                  if(root == null){return new Info(true,0, Integer.MAX_VALUE, Integer.MIN_VALUE);}

                  //recursion
                  Info leftInfo = largeBst(root.left) ;
                  Info rightInfo = largeBst(root.right) ;
                  int size = leftInfo.size +rightInfo.size +1 ;

                  //calculation
                  int min = Math.min(root.data, Math.min(leftInfo.min, rightInfo.min)) ;
                  int max = Math.max(root.data, Math.max(leftInfo.max, rightInfo.max)) ;

                  //conditions
                   //1-> invalid
                  if(root.data <= leftInfo.max || root.data >= rightInfo.min){
                     return new Info(false, size, min, max) ; 
                  }
                  if(leftInfo.Bst && rightInfo.Bst ){// bst
                     maxBst = Math.max(maxBst , size) ;
                     //System.out.println("Now max bst becomes"+ maxBst);
                     return new Info(true, size, min, max);
                  }

                  return new Info(false, size, min, max) ;
              }    
        public  Node mergeBsts(Node root1,  Node root2) {
           //step ->1
             //calculate  inorder of 1st bst
             ArrayList<Integer> l1 = new ArrayList<>() ;
             getInorder(root1, l1); 
           //step ->2
             ArrayList <Integer>l2 = new ArrayList<>() ;
             getInorder(root2, l2);

           //step ->3
             //merge
            ArrayList <Integer> finalarray = new ArrayList<>() ;
             int i= 0 ,j = 0 ;
             while(i<l1.size() && j<l2.size()){
               //comparision
               if(l1.get(i) <= l2.get(j)) {
                     finalarray.add(l1.get(i)) ;
                     i++ ;
               }else{
                     finalarray.add(l2.get(j)) ;
                     j++ ;
               }
              }

                //remaining elements
                while(i< l1.size()) {
                     finalarray.add(l1.get(i)) ;
                     i++ ;
                }
                while(j<l2.size()){
                     finalarray.add(l2.get(j));
                     j++ ;
                }
               
           //step ->4  
              return createBst(finalarray, 0, finalarray.size() -1) ;
           
        }
        public  int  printthesum(Node root , int k1 , int k2){
         if(root == null){return 0 ;}
         int sum = 0 ;
         if(root.data >= k1 && root.data <= k2) {
              printthesum(root.left, k1, k2) ;
              sum +=root.data  ;
              printthesum(root.right, k1, k2) ;
         }
         else if (root.data >= k2 ){
              printthesum(root.right, k1, k2) ;
         }
         else {
              printthesum(root.left, k1, k2) ;
         }

         return sum ;
       }
        static  int sum ;
        public  int rangeSumBST(Node root, int low, int high) { 
             //variable to return
              if(root == null){return  0;}
              //left
              rangeSumBST(root.left, low, high) ;
              //root
                
               //element present in the range
               if(root.data <= high && root.data>= low){
                      sum += root.data ;
               }
              
               rangeSumBST(root.right, low, high) ;
        
        
               return sum ;
        }
        public  int maxSumBST(Node root) {
        //base case
        if(root == null) {return 0 ;} 
        else{ //helper function which returns maximum sum of bst
        helper(root) ;
        //if result is greater than 0 then simply return it !
        return result<0 ? 0: result ;
        }
        } 
        //helper function will return a int []
        //which have left max element in 0 idx
        //which have right min element in 1 idx
        public  int[] helper(Node curr) {
                   //base case
                  if(curr == null) { // non node case
                     return  new int[]{Integer.MAX_VALUE ,Integer.MIN_VALUE,0 };
                     }
                     int left[] =helper(curr.left) ;
                     int right[] = helper(curr.right) ;
       
                     //0 index -> largest in my left subtree
                     //1 index -> minmum in my right subtree
       
                     //no bst case
                     if(left[1] >= curr.data || right[0]<= curr.data)
                     { return new int[]{Integer.MIN_VALUE ,Integer.MAX_VALUE ,0} ;
                    }
                    //calculate the sum
                    int sum = left[2]+ right[2] +curr.data ;
                    result= Math.max(sum,result) ;//compatre the sum with result
                    return new int[] {  Math.min(curr.data,left[0])   ,  Math.max(curr.data,right[1]) , sum} ;
                  //here we are returning an array which has left max  at 0 , right min at 1 , and sum at 2
       }
        static int result ;//variable to return 
        public static void main(String[] args) {
              Node root = new Node(3);
              root.left = new Node(1);
              root.right = new Node(4) ;
              root.left.right = new Node(2) ;

              binary_search_tree b = new binary_search_tree();
              b.inordertraversal(root);
              System.out.println();



              System.out.println(kthclosestelement(root, 1            ));
               
              }    
              static  int sum2 ;
              public  void helper2(Node root) {
              
              if(root != null)//first go the right subtree
              {  helper2(root.right) ;
              sum2 += root.data ;
              root.data = sum2 ;
              helper2(root.left) ; //left subtree
              }
              }
              public Node bstToGst(Node root){
              sum2 = 0;
              helper2(root);
              return root;
              }
              } 
        
       static int count ;
       public static int kthclosestelement( Node root ,int k) {
              //create a stack 
              Stack <Node> s = new Stack<>() ;
              int target  =0 ;
              int ans = 0 ;
              Node curr = root ;
              
              while (!s.isEmpty() || curr != null) {
               while (curr != null){
                s.push(curr) ;
                curr = curr.left ;     
              }                
               curr = s.pop() ;
               target ++ ;

       
                if (target == k) {
                     ans = root.data ;
                     break;
                }
               curr = curr.right ;
        }
        return ans ;
}

}