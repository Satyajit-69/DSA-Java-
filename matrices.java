
public class matrices
 {
  public static int diagonal(int matrix[][]){//0(n^2)
      int sum =0 ;
      // for (int i = 0 ; i < matrix.length ; i ++){
      //    for (int j =0 ; j < matrix[0].length ; j++){
      //      if(i ==j){
      //       sum+=  matrix[i][j] ;
      //      }
      //      else if 
      //      (i+j == matrix.length-1 ){
      //       sum+= matrix[i][j];
      //      }
      //    }
      // } 
        

 for(int i = 0 ; i<matrix.length ; i ++){
  //pd
  sum+= matrix[i][i];
  //sd
  sum+= matrix[i][matrix.length-1-i] ;
 }
   return  sum;

  }
  public static void printSpiral(int matrix[][])
   {
    int n = matrix.length ;// no of rows
    int m = matrix[0].length;//no of column   


    int sr = 0 ;
    int sc = 0 ;
    int er = n-1 ;
    int ec = m - 1;

    while( sr <=er && sc <= ec)
    {
     //top  j = 
       for(int j = sc ; j <= ec ; j ++)
     {
       System.out.print(matrix[sr][j]+" ");
     }
    
     //right
     for(int  i = sr+ 1 ;i<=er ; i ++ )
     {
     System.out.print(matrix[i][ec]+" ");
     }
    // bottom
    for(int j = ec - 1 ; j >= sc ; j --)
    { if(sr==er)
    {
     break;
    }
    
      System.out.print(matrix[er][j]+" ");
    }
    //  left
    for(int i = er-1 ;i >=sr+1 ; i --)
    {if(sc==ec)
      {
       break;
      }
    System.out.print(matrix[i][sc]+" ");
    }
  
    //update
   
    sr++;
    er--;
    sc++;
    ec--;
  
    }

   }
  //  public static void printsecond (matrix [][]){
  //    int sum= 0 ;
  //   for(int i = 0 ; i <=matrix.length-1; i ++){
  //     for(int j = 0 ;j <=matrix[0].length-1 ; j ++){
  //       if(matrix[i][j]== 7){
  //         counter ++ ;
  //       }
  //     }
  //   }System.out.println(" we have inputed the number "+ counter + " times");
  //  }

  
   public static void nums(int matrix[][]){
    // approach - 1
    int sum =0 ;
    int m = matrix[0].length;    
  
    for ( int j = 0; j< matrix[0].length ; j ++){
      
  //here we know the value of i i.e. 1 
      
      sum+=matrix[1][j];
    }
    System.out.println(" sum of all elements in 2nd row " + sum);
  }
   public static void findtranspose ( int matrix[][]){
    int n = matrix.length ;
    int m = matrix[0].length ;
    int transpose[][] = new int [m][n];
    // here arr[i][j] = transpose arr [j][i]

    for ( int i = 0 ; i < m ; i ++)
  {
      for (int j = 0  ; j < n ; j ++){
        transpose[i][j]= matrix[j][i] ; System.out.print( transpose[i][j] + " ");   
      }System.out.println();
  }
  }
   public static boolean staircasesearch(int matrix[][], int key ){
    int row =0;
    int column= matrix[0].length -1;

    while( row <=matrix.length && column>=0){
      if(matrix[row][column]== key)
      {
        System.out.println(" found the key in " + row +","+  column + " cell");
        return  true ;
      }
      else if (matrix[row][column]>= key){
        column-- ;
      }else{
        row ++ ;
      }
      
    }System.out.println(" key not found");
    return  false ;

  }
   public boolean searchMatrix(int[][] matrix, int target) {
    for (  int i = 0; i < matrix.length ; i ++){
      for( int j =0; j < matrix[0].length ; j ++){
          if( matrix[i][j]==target){
              return true ;
          
          }
      } System.out.println(" not found ");
    } return  false  ;
   
} 
   public int[] sortArray(int[] nums) 
{
 for(   int i = 0 ; i< nums.length  ; i ++)
 {
    int mp = i ;
    for (int  j = i+1 ; j < nums.length ; j ++)
    {
      if ( nums[mp]> nums[j])
      {
        mp= j ;}
        //swapping
        int temp= nums[mp];
        nums[mp] =nums[i];
        nums[i]= temp ;
      }
    } return  nums;
 }
   public static void spiralOrder(int[][] matrix) {
        int n = matrix.length ; // rows
        int m = matrix.length ; // columns

        int sr = 0;
        int sc = 0;
        int er = n-1 ;
        int ec = m -1 ;

        while(sr<=er && sc <=ec) 
        {
            //top
            for(int j = sc ;  j <= ec ; j ++){
                System.out.print (matrix[sr][j] + " ");
            }
            //right 

            for(int i = sr+1 ; i <= er ; i ++ ){
                System.out.print(matrix[i][ec] +  " ") ;
            } 
            //bottom 

            for(int j = ec-1 ; j >=sc ; j --){
            if(sr == er){
              break;
            }
                System.out.print(matrix[er][j] + " ");
            }
            //left 
            for(int i = er-1 ; i >= sr + 1 ;i--){
              if (sc == ec ){
                break;
              }
              System.out.print(matrix[i][sc]);
            }
            System.out.println();
        }
  // update 
   sr++ ;
   er-- ;
   sc++;
   ec--;

  
    }
   public static void main(String[] args)
  {
   
      int matrix[][] =
      {{1,2,3},
       {4,5,6},
       {7,8,9}};
      spiralOrder(matrix);
      
  }
  }
