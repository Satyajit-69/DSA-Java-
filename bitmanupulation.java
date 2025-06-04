

public class bitmanupulation {
    public static void oddoreven(int n){
       int bitMask =1  ;
       if((n & bitMask) == 0){
        //even number
        System.out.println(" even number");
       }else {
        System.out.println(" odd number ");
       }

   
    }
     public  static  int  getithbit(int n){
      int i = 3 ;
      int bitMask = 1 << i ;
      if((n&bitMask)== 0) {
         return 0;

      }else{
         return  1;
      }

     }
     public  static   int setithbit(int  n){
      int i = 2 ;
      int bitmask = 1<<i ;
      return  n | bitmask ;
     }
     public static  int clearithbit( int n , int i ){
      int bitMask =~(1<< i) ;

         return n&bitMask ;
      }
     public static  int updateithbit ( int n ,int i, int newbit) {
    
      n = clearithbit(n, i) ;
      int bitMask = newbit<< i ; 
      return  n|bitMask ;
     }
     public static int clearlastithbit (int n  , int i) {
      int bitMask = ((-1)<<i) ;
      return  n & bitMask ;
     }
     public static int clearrangeithbit(int i  , int j  , int  n){
      int a = ((~0) << (j+1) );
      int b =( 1 << i) -1 ;
      int bitMask =  a | b ;
      return n& bitMask ;
     }
     public static boolean isPOwerofTwo(int n ) {
      return  (n&(n-1))== 0 ;
     }
     public static int countsetbits(int n ){
      int count = 0;
      while(n > 0){
          if ((n&1) != 0 ) { //check our least significant
            count++ ;
          }
          n =n >>1 ;
      }
      return  count ;
     }
     public static int fastExponentation( int a , int n){
      int ans = 0 ;

      while((n&1)!= 0){
       
      }
      return  ans ;
     }
   public static void swapingwithoutthirdvariable(){
      int a = 3 ;
      int b = 4 ;
      //swaping using xor

      a = a^b ;
      b =a^b ;
      a = a^b ;

      System.out.println( " the value of a and b after swapping are  " + a + ","+ b );
   
   }
public static void main(String[] args) {
 for( char ch = 'A' ; ch <= 'Z' ; ch ++){
   System.out.println((char)(ch | ' '));
 }
}
}