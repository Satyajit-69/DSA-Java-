public class segmentTrees {
    static int tree[] ;

    public static void init(int n) {
        tree = new int[4*n] ;
    }
    public static int buildST(int arr[] , int idx ,int start , int end) {
        if(start == end) {
            tree[idx] = arr[start] ;
            return arr[start] ;
        }
        //get the mid
        int mid = (start + end) /2 ;
        buildST(arr, 2*idx+1, start, mid) ;  //left subtree -> 2*i+1
        buildST(arr, 2*idx+2, mid+1, end) ;  //right subtree -> 2*i+2

        tree[idx] = tree[2*idx+1] + tree[2*idx+2] ; //left + right
        return tree[idx] ;


    }
   
    public static int  getSum(int arr[], int qi, int qj) {
        int n = arr.length;
        return getSumutil(0, 0, n - 1, qi, qj);
    }
    public static int  getSumutil(int i, int si, int sj, int qi, int qj) {
        // Case 1: No overlap
        if (qj < si || qi > sj) { 
            return 0;
        }
    
        // Case 2: Complete overlap
        if (qi <= si && qj >= sj) { 
            return tree[i];
        }
    
        // Case 3: Partial overlap
        int mid = (si + sj) / 2;
        int left = getSumutil(2 * i + 1, si, mid, qi, qj);
        int right = getSumutil(2 * i + 2, mid + 1, sj, qi, qj);
        
        return left + right;
    }
    public static void update(int arr[],int idx,int newVal) {
        int n = arr.length ;
        int diff=  newVal - arr[idx] ;
        arr[idx] = newVal ;

        //segment tree changes
        updateUtil(0, 0, n-1, idx, diff);
    }
    public static void updateUtil(int i ,int si,int sj,int idx,int diff) {
        //non lie on region
        if(idx > sj || idx < si) {
            return ;
        }        

        //lie in the range
        tree[i] += diff ;
        if(si != sj) {
            int mid = (si + sj) /2 ;
            updateUtil(2*i+1, si, mid, idx, diff); //left
            updateUtil(2*i+2, mid+1, sj, idx, diff); //right
        }
    }

    public static void BuildST(int i , int si ,int sj,int arr[]) {
             //base case
             if(si ==  sj) {
                tree[i] = arr[si] ;
                return ; 
             }

             //mid 
             int mid = (sj+si)/2 ;
             BuildST(2*i+1, si, mid, arr) ;
             BuildST(2*i+2,mid+1,sj, arr) ;

             tree[i] = Math.max(tree[2*i+1],tree[2*i+2]) ;



    }
    public static int  getmax(int arr[] ,int qi,int qj) {
        int n = arr.length ;
        return getMaxutil(0, 0, n-1, qi, qj) ;
    }
    public static int  getMaxutil(int i ,int si,int sj,int qi,int qj) {
        //no overlap
        if(si > qj || sj < qi) {
            return Integer.MIN_VALUE ;
        }
        //complete overlap
        else if(si >= qi && sj <= qj) {
            return tree[i] ;
        }
        //partial overlap
        else {
            int mid = (si+sj)/2 ;
            int leftans = getMaxutil(2*i+1, si, mid, qi, qj) ;
            int rightans = getMaxutil(i, mid+1, sj, qi, qj);

            return Math.max(leftans, rightans) ;
        }
    }
    public static void Update(int idx ,int arr[],int newVal) {
        arr[idx] = newVal ;
        int n = arr.length ;
        UUtil(0, 0, n-1, idx, newVal);
    }
    public static void UUtil(int i,int si,int sj,int idx,int newVal) {
      //non lie on region
      if(idx > sj || idx < si) {
        return ;
    }        

    //lie in the range
    tree[i] = Math.max(tree[i], newVal) ;

    if(si != sj) {
        int mid = (si + sj) /2 ;
        UUtil(2*i+1, si, mid, idx, newVal);
        UUtil(2*i+2, mid+1, sj, idx, newVal);
    }
    }
    public static void main(String[] args) {
        int arr[] = {6,8,-1,2,17,1,3,2,4};
        int n = arr.length;
        init(n);
        BuildST(0, 0, n - 1,arr);

        // 
        // System.out.println(getmax(arr, 2, 5));
        for(int i : tree) {
            System.out.print(i+" ");
        }
        System.out.println();

        update(arr, 2, 20);

        for(int i : tree) {
            System.out.print(i+" ");
        }
        System.out.println();
        
        // System.out.println(getmax(arr, 2, 5));
       
    }
}
