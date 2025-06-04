
import java.util.*;
public class Stackb {  
        public static  void pushatBottom(Stack<Integer>s , int data) { // o(n)
          if(s.isEmpty()){
            s.push(data) ;
             return ; 
          }
          int top = s.pop() ;//remove element
          pushatBottom(s, data); // recursion for up element
          s.push(top) ;//add the element while returning
        }
        public static  String reverse(String str){
        Stack<Character> s  = new Stack<>() ;
         int idx =0 ;
         while(idx < str.length()){
            s.push(str.charAt(idx)); // simply removing  the charecters in a stack 
            idx++ ;
         }

          StringBuilder sb = new  StringBuilder("");
              while (!s.empty()){
                char curr = s.pop() ; //aadding the element
                sb.append(curr) ;
              }

              return sb.toString() ;
        }
        public static  void reversestack(Stack<Integer>s) {
          if(s.isEmpty()){
            return ;
          }
          int top = s.pop() ; //getting the top
          reversestack(s);
          pushatBottom(s, top);
        }
        public static  void stockSpan(int stocks[], int span[]) {
          Stack<Integer> s = new Stack<>() ;
          span[0] = 1 ;//default val = 100
          s.push(0) ;

          //checking  every day price
          for(int i = 1; i<stocks.length ;i++){
             int cur = stocks[i] ; //getting todays price
             while(!s.isEmpty() && cur >= stocks[s.peek()]){
              s.pop() ; //chote wale ko nikal do
             }
             if(s.isEmpty()){
              span[i] = i+1  ; //last element
             }else {
              int prevHigh = s.peek() ;//top value
              span[i] = i-prevHigh ;//get the span value
             }
             s.push(i) ; 
          }
        }
        public static  int[] nextgreaterelement(int arr[]){
           Stack<Integer> s = new Stack<>();
           int nexttarget[] = new int[arr.length] ;
            //backward loop
           for(int i = 0 ; i<=arr.length-1 ;i++){
           while(!s.isEmpty() && arr[i] <=arr[s.peek()] ){
            //comparing with top value
             s.pop() ; // remove small elements
           }
           if(s.isEmpty()) {
             nexttarget[i] = -1 ;
           }
           else {
            nexttarget[i] = arr[s.peek()];
           }
           s.push(i) ; //adding element to stack
        }
         return  nexttarget ;
      }
        public static boolean isValidparanthesis(String s) {
          Stack<Character> l = new Stack<>()  ;
          for(int i  = 0 ; i< s.length() ; i++) {
            char ch = s.charAt(i) ;
            if(ch == '{'  || ch == '[' || ch == '(') { l.push(ch) ;} //opening bracket
            else if (s.length() % 2  != 0){return false ;}
            else if( ch == '}' && l.isEmpty() || ch== ']'  && l.isEmpty()|| ch ==')'  && l.isEmpty()){return  false ;}
            else {
              if(l.peek() == '(' && ch == ')' ||
                 l.peek() == '{' && ch == '}' ||
                 l.peek() == '[' && ch == ']') {
                 l.pop() ; //pair condition satishfies
                 }
              else{
                return  false ;
              }
            }
          }

          if(l.isEmpty()) {
            return  true ;
          }else {return  false ;}
        }  
        public static boolean duplicateparanthesis(String str){
           //stack
           Stack<Character> s = new Stack<>() ;
           if(str.length() %2  != 0) {return false ; }
           for(int i = 0 ; i<str.length() ; i++) 
           {
            
             char  cur = str.charAt(i) ;
             //closing pair
             if(cur == ')') {
               int  count = 0 ;
                while( s.pop() != '(') {
                    count ++ ;
                    }  
                       if(count <1) {
                        return true ; //duplicate
                       }
               }else { //opening element
               s.push(cur) ;
           }
        }
        return  false ;
      }
        public static  void maxarea (int arr []){
        int maxarea = 0 ;
        int nsr []= new int[arr.length] ;
        int nsl [] = new int[arr.length] ;

        //Next smaller right
        Stack<Integer>s = new Stack<>() ;
        
        for(int i  = arr.length -1 ; i>= 0 ; i--) {
          while(!s.isEmpty() && arr[s.peek()] >= arr[i]) {
            s.pop() ; // remove greater element
          }
          if(s.isEmpty()){
           nsr[i] = arr.length ; //  not fiound the smaller element
          }
          else{ nsr[i] = s.peek() ;}
          s.push(i) ; // add element
        }


        //Next smaller  left
        s = new Stack<>() ;

        for(int i  =  0 ; i<arr.length  ; i++) {
          while(!s.isEmpty() && arr[s.peek()] >= arr[i]) {
            s.pop() ; // remove greater element
          }
          if(s.isEmpty()){
           nsl[i] = -1; //  not found the smaller element
          }
          else{ nsl[i] = s.peek();}
          s.push(i) ; // add element
        }

         //currrent area =width  = j- i -1 = nsr[i] - msr[i] -1
         for(int i  =0 ; i<arr.length ;i++) {
          int height = arr[i] ;
          int width = nsr[i] - nsl[i] -1 ;
          int curArea = height * width ;
          maxarea = Math.max(maxarea, curArea)  ;
         }
         System.out.println(maxarea);
       }
        public static  String Simplyfypath(String path) {
          String output = "/" ; // our final output
          String arr[]= path.split("/") ; // split the  charecters except "/"
          int i =0 ; //iterator for arr 
          Stack<String> s = new Stack<>() ; // stack to store the  strings
          
           for(String j : arr) //direct traversing
           {//condition
             if(j.equals("..")){ //it means we have to return to our parent array
              if( ! s.isEmpty()) {s.pop() ;}else{continue; }
             }else if(j.equals("/") || j.equals(".") || j.isEmpty()) {
              continue;  // ignore
             }else {
              s.push(j) ;
             }
           }

           // now store the char string  to array by poping out the stack
           while(!s.isEmpty())
            {arr[i++] = s.pop() ;
            }
            //copy to the output string
            // back word  loop  -> bcz the  array stored element are in reverse order
            for( int j = i-1 ; j>=0 ; j--){
              if(j!= 0){
                output = output + arr[j] +"/" ;
              }else { // we have reached the arrays last index
                 output = output + arr[j];
              }
                         
            }
           return  output ;

        }  
        public static  String  DecodeString(String str)
        {    //create stacks
          Stack<Integer> numStack =  new Stack<>() ;
          Stack<StringBuilder> stringstack = new Stack<>() ;
          StringBuilder sb = new StringBuilder() ;
          //iterate the input
          int num =0 ;
          for(char  ch  : str.toCharArray()) //string to charecter array
           {  //num check
             if(ch >= '0' && ch <= '9') {
               int digit  = ch - '0' ; //getting the ch value
               num =  num * 10  + digit ;
             }
             //opening bracket
             else if(ch =='[') { // push num into numstack &  add the ch to strings
               stringstack.push(sb) ;
               sb= new StringBuilder() ; 
               numStack.push(num) ;
               num =0 ;
             }
             //closing bracket
             else if( ch == ']') {
               //add string   according  to the num value   by poping out
               StringBuilder  temp = sb ;
               sb= stringstack.pop() ;
               int count  =  numStack.pop() ;
               while(count > 0){
                 sb.append(temp);
                 count --  ;
               }
             } //else -> add the ch to resultant string
             else{
               sb.append(ch) ;
             }
           }
           //return the op string
           return  sb.toString() ;
         }
            
     public static  int rainwater(int height[]){
      //create a stack
      Stack<Integer> s=  new Stack<>() ;
      int ans  =0 ;
      for(int i = 0 ; i<height.length ;i++){
        while(!s.isEmpty() && height[s.peek()]<height[i]){
          int m  = s.pop() ; 
           if(s.isEmpty()){
            break;
           }
           int l = s.peek() ;
           int h = Math.min(height[l] , height[i]) -  height[m] ;
           int w = i-l -1 ;
           ans += h*w ;
       }
       s.push(i);
       }
       return  ans ;
     }

 
              
        public static void main(String[] args) {
         int h[] = {4,1,2,3,4};
         System.out.println("the  trapping rain water is "+ rainwater(h) );
        }
   }
   
  
   

 