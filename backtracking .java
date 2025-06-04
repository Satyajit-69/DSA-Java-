


class backtracking  {
  public static char [][] L ={{},{}, {'a','b','c'} , {'d', 'e' , 'f'} , {'g','h' , 'i'} , {'j','k','l'} ,{'m','n','o'}
  ,{'p', 'q' ,'r' ,'s'} , {'t' ,'u' ,'v'} ,{'w','x','y','z'}} ;
 
  public static void lettercombination( String D){
    int  len = D.length() ;
    if(len == 0){
      System.out.println("");
       return;
    }

    //recursion
      recurison(0 , len , "",  D);
  }
  public static void  recurison( int pos , int len ,String B , String D){
    if(pos == len){
      //we have reached  the end , print the  string
      System.out.println(B);
    }
    else { // charecter.numerical value = to access the value 
       char letters[] = L[Character.getNumericValue(D.charAt(pos))];
       for(int j = 0 ; j< letters.length ;j++)
       recurison(pos+1, len, B+letters[j], D) ;
    }





  } public static void main(String[] args) {
    
     int n  = 8;
     solveKt(n) ;
  }
  public static boolean solveKt(int n){
    int sol[][]= new int[8][8];
    for(int x =0  ;x<n ;x++){
      for(int y = 0  ; y<n ;y++){
        sol[x][y] = -1 ; // default
      }
    }
    int xMove[]= {2,1,-1,-2,-2,-1,1,2};
    int yMove[]= {1,2,2,1,-1,-2,-2,-1};
    
    //starting point cell(0,0)
    sol[0][0] = 0 ;//fill

    if(! solveKtutil(0,0,1,xMove,yMove,sol,n)){
      System.out.println("solution doesnot exist :(");
      return false;
    }else{
      print(sol,n) ;
      return  true ;
    }

  }
  public static void print( int sol[][], int n){
    for(int i =  0 ;i<n ;i++){
      for(int j =0 ; j<n; j++)
      {System.out.print(sol[i][j]+" ");
        }
        System.out.println();
    }  
   }
  public static  boolean  isSafe(int x , int y , int sol[][],int n){
     return  x>=0 && x<n && y>=0 && y<n && sol[x][y] == -1 ;
    }
    //this function is looking  for solution -> by playing every move 
   public  static boolean solveKtutil(int x , int y ,int movei,int xMove[],int yMove[] ,int sol[][], int n){
       int k , nextX , nextY ;
       if(movei == n*n){
        return true ;
       }
       for(k=0;k<8 ;k++){
        nextX = x + xMove[k];
        nextY=  y + yMove[k];

        if(isSafe(nextX, nextY, sol, n)){
          sol[nextX][nextY] = movei ;
          if(solveKtutil(nextX, nextY, movei+1, xMove, yMove, sol, n)){
            return  true ;
          }
           else{
          sol[nextX][nextY] = -1 ; //break
          }
        }
       }
       return  false ;
   }
   
}
