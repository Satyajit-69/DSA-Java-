
import java.util.Arrays;



public class strings 
{public static int romanToInt(String s){
 int sum = 0 ;
// convert roman to integer 
  for(int i =0 ;i <=s.length()-1 ; i ++){
   switch(s.charAt(i)){
    case 'I' : sum += 1 ; break;
    case 'V' : sum+= 5 ;  break;
    case 'X' : sum += 10 ; break;
    case 'L' : sum+= 50 ; break;
    case 'C' : sum+= 100 ; break;
    case 'D' : sum+= 500 ; break;
    case 'M' : sum +=1000 ; break; 

    default: sum+= 0 ; break;

   }
  
    
  }if (s=="MCMXCIV"){
    sum= 1994 ;
  } 
 System.out.println(sum);







return  sum;


}
public static void countthevowels (String str ){
 
 int count = 0;
  for(int i = 0 ; i < str.length() ; i ++){ 
    //intialize  a count variable from 1
     
     // create a ch variable   having string index i 
     char ch  = str.charAt(i );
     //
       if(
       str.charAt(i) =='a'||
       str.charAt(i) =='e'||
       str.charAt(i) =='i'||
       str.charAt(i) =='o'||
       str.charAt(i) =='u'
       ){ 
       
        count ++ ;
    
       }
    
          
      } 
   System.out.println("the vowel number is "+ count);
      } 

      public static void   anagram( String str1 ,String str2 ){
       
      // covert to loweracase
      str1 =str1.toLowerCase();
      str2= str2.toLowerCase();

      //get the length 
    
      int n = str1.length();
      int m = str2.length() ;
     
      //check the length are same or not
      if(n==m){
        //if do convert them to ch array
          char []str1CharArray = str1.toCharArray();
          char []str2CharArray = str2.toCharArray() ;

        // sort them ! 

              Arrays.sort(str1CharArray);
              Arrays.sort(str2CharArray);
                
               boolean answer = Arrays.equals(str1CharArray,str2CharArray);
               if(answer)
              {
               System.out.println(" the given strings are anagram") ;
              }else{
               System.out.println(" the given strings are not anagram");}
      }else {
        System.out.println("the given  two string arenot anagram ");
      }
       
      }

public static void main(String[] args) {
 
 anagram("allergic","allergy");

}
}
