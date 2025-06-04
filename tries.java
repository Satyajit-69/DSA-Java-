
public  class  tries {
    static  class  Node {
    Node children[] = new Node[26] ;
    boolean eow = false ; 
    Node() {
        for(int i  =0 ; i<26 ; i++) {
            children[i] = null ;
        }
    }    
    }
    static  Node root = new Node() ;
    public static void insert(String word ) { //O(L)
        Node curr = root ;
        for(int level= 0 ; level<word.length() ; level++){
            int idx = word.charAt(level)-'a' ;

            //check 
            if(curr.children[idx] == null){ //not exist 
                curr.children[idx] = new Node() ; //new node
            }
            curr = curr.children[idx] ;
        }

        curr.eow = true ;
    }
    public static boolean search(String key){
        Node curr= root ;
        for(int level =  0; level<key.length() ; level++){
            int idx = key.charAt(level) -'a' ; //char idx
            if (curr.children[idx]== null) { //doesnot exist
               return false ;
            }
            curr = curr.children[idx]; //update
        }   
            return  curr.eow == true ;
    }
    public static boolean wordBreak(String key){
        if(key.length() == 0){
            return  true ;
        }
        for(int i =1 ;i<= key.length();i++){
            //substring (0,1)
           if(search(key.substring(0,i))&& wordBreak(key.substring(i))){
           return  true ;
           }
        }
        return  false ;
    }
    public static boolean startsWith(String prefix){
        Node curr = root ;
        for(int i=0; i<prefix.length() ;i++){
            int idx = prefix.charAt(i)-'a' ;
            if(curr.children[idx]==null) {
                return  false  ;
            }
            //updation
            curr = curr.children[idx] ;
        }
        return true ;
    }
    public static int countNodes (Node root) {
        if(root == null){
        return  0 ;}

        int count =0; 
        for(int i=0 ;i<26 ;i++){
           if (root.children[i] != null) {
              count+= countNodes(root.children[i])  ; 
           } 
        }
        return count+1 ; //+1 for self count   
    }
    static  String ans ="" ; 
    public static void longestWord(Node root , StringBuilder temp){
         if(root == null){return; }
         for(int i = 0; i<26 ;i++){ 
            if(root.children[i] != null && root.children[i].eow == true){
                char ch = (char)(i+'a') ;
                temp.append(ch) ;
                if(temp.length() > ans.length()){
                    ans = temp.toString() ;
                }
                longestWord(root.children[i], temp);
                temp.deleteCharAt(temp.length()-1) ;
            }
         }
    }
    public static void main(String[] args) {
        String words[] ={"a","banana" ,"app" ,"appl" ,"ap","apply","applz"} ;
      

        for(int i =0;  i<words.length ;i++){
            insert(words[i]);
        }
        // longestWord(root,new StringBuilder() );
        // System.out.println(ans);
    


    }
}