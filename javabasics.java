

public  class javabasics {
 public static void main(String[] args) {
     System.out.println(count(1,2,3));
 }
  public static int count( int... arr){
    int sum  = 0 ;
    for(int a :arr){
      sum += arr[a] ;

    }
    return  sum ;
  }
}