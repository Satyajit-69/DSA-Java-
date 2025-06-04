
public class input {
     static  class  Node  {
        Node children[] = new Node[26] ;
        boolean eow = false ;
        int freq ;
        public Node() {
            for(int i =0 ;i<26 ;i++){
                children[i] = null ;
            }
            freq = 1 ;
        }
 
     }
    public static  Node root = new Node() ;
     public static void main(String[] args) {
        String word[] = {"apple","appk","dog"} ;
        for(int  i= 0; i<word.length ;i++){
            insert(word[i]);
        }
        root.freq =-1 ;
        findprefix(root, "");
        
     }
     public static void findprefix(Node root , String ans) {
            
            if (root == null) { //base case
                return;
            }

            if(root.freq ==1){ // unique or not used yet
                System.out.println(ans);
                return;
            }


        //for loop
        for(int i= 0 ;i<root.children.length ;i++){
           if(root.children[i] != null){
            findprefix(root.children[i], ans+ (char) (i +'a'));
           }
          }
          
       }
     public static void insert(String word ) { //O(L)
        Node curr = root ;
        for(int level= 0 ; level<word.length() ; level++){
            int idx = word.charAt(level)-'a' ;

            //check 
            if(curr.children[idx] == null){ //not exist 
                curr.children[idx] = new Node() ; //new node
            }else{
                 curr.children[idx].freq++ ;
            }
           
            curr = curr.children[idx] ;
        }

        curr.eow = true ;
    }
}
