

public  class javabasics {
 public static void main(String[] args) {
    String res = generateBinary(4);
    System.out.println(res);
 }
  public static int count( int... arr){
    int sum  = 0 ;
    for(int a :arr){
      sum += arr[a] ;

    }
    return  sum ;
  }
  public static String generateBinary(int n) {
         if(n == 1){
          return "1" ;
         }
         if(n==0){
          return "0";
         }
          
        
         return  generateBinary(n/2) + (n%2);
  }
}