public class diviideandconquer{
    public static int search(int arr[] , int tar , int si , int ei){
        //base 
        if(si>ei){
            return -1 ;
        }
        //kaam
        int mid = si+ (ei-si) /2;

        //case found
        if(arr[mid]==tar){
            return mid;
        }

        //mid on l1 
        if(arr[si]<=arr[mid]){
            //case a : left search :(
            if (arr[si]<=tar && tar<=arr[mid]){
               return search(arr, tar, si, mid-1) ;
              }else{
                //case b : rightsearch 
                return search(arr, tar, mid+1,ei ) ;
              }            
        }
        else {// mid lie on l2
           //case a ~> right search
           if(arr[mid]<=tar&& tar<=arr[ei]){
            return  search(arr, tar, mid+1, ei) ;
           }else {
           //case b ~> left search
            return search(arr, tar, si, mid-1);
           }
        }
    }
    public static int[] mergeSort(int arr[]) {
       //base case
       if(arr.length  <= 1){
        return arr;
       }
        
       //Split the array
       int mid = arr.length /2 ;
       int left [] = new int[mid] ;
       int right[] = new int[arr.length - mid] ;

       //fill the left and right part 
        for(int i = 0; i<mid ;i++){
            left[i] = arr[i] ;
        } 
        for(int j = mid ;j<arr.length ;j++) {
            right[j-mid] = arr[j] ;
        }

        //Sort both parts 
        left = mergeSort(left) ;
        right = mergeSort(right) ;

        //Merge Sorted Arrays
        return Merge(left,right) ;
    }
    public static int[]  Merge (int left[] ,int right[]) {
        int result[] = new int[left.length + right.length] ;

        int i = 0 ;
        int j = 0 ;
        int k = 0 ;

        //Merge 
        while (i < left.length &&  j< right.length) {
             if(left [i] < right[j]) {
                result[k++] = left[i++] ;
             }

             else {
                result[k++] = right[j++] ;
             }
        }

        //Copy the remaining
        while (i< left.length) {
                result [k++] = left[i++] ;
        }
        while (j< right.length) {
            result [k++] = right[j++] ;
        }

        return result ;
    }
    public static void main(String[] args) {
        int arr []={4,5,6,7,0,1,2} ;
        int res[] = QuickSort(arr) ;
        for(int i : res ){
            System.out.print(i+" ");
        }
    }

    public static int[] QuickSort(int nums[]) {
        return Quick(nums,0,nums.length-1) ;
    }
    public static int[] Quick(int nums[],int si ,int ei) {
        if (si >= ei) {
            return nums;
        }

        //take pivot 
        int pivot = partition(nums,si,ei) ;

        //Recursion
        Quick(nums, si, pivot-1) ; //left
        Quick(nums, pivot+1, ei) ; //right

        return nums ;

    }
    public static int partition(int nums[],int si ,int ei) {
        int pivot = nums[ei]; // Choose the last element as the pivot
        int i = si - 1; // Index for smaller element
    
        for (int j = si; j < ei; j++) {
            if (nums[j] <= pivot) {
                i++;
                // Swap nums[i] and nums[j]
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
    
        // Swap nums[i + 1] and nums[ei] (pivot)
        int temp = nums[i + 1];
        nums[i + 1] = nums[ei];
        nums[ei] = temp;
    
        return i + 1; // Return the pivot index
    }
}
