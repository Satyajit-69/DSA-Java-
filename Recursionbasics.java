


 public class Recursionbasics {
    public static void towerofhanoi(char  source , char  auxilary , char  destiny , int n ){ ///here no of disk = 3
        
    //base case 
    if(n==0){
        return; 
    }
    // faith 1st and 2nd disk will move to aux 
     towerofhanoi(source, destiny, auxilary, n-1);
     int total = 3 ;


     // work

     //moving 3 rd disk from source to destiny
     System.out.println(total - n +1+  "th  disk "+ " will  move from "+ source +" tower "+ " to "+ destiny);
     

     //faith ~> 1st and 2nd will move to destiny from aux tower 
     towerofhanoi(auxilary, source, destiny, n-1);
 


    }
    public static void main(String[] args) {
        towerofhanoi('a','b', 'c', 3);
    }
 }