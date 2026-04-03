public class basicsorting {
  public static void bubblesort(int arr[]) {
    for (int turn = 0; turn < arr.length - 1; turn++) {
      for (int j = 0; j < arr.length - 1 - turn; j++) {
        if (arr[j] < arr[j + 1]) {
          // swap
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }
  }

  public static void selectionsort(int arr[]) {
    for (int i = 0; i < arr.length - 1; i++) {
      // get the minPos
      int minpos = i;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[minpos] > arr[j]) {
          minpos = j;
        }
      }
      // swap
      int temp = arr[minpos];
      arr[minpos] = arr[i];
      arr[i] = temp;

    }
  }

  public static void insertion(int arr[]) {
    // idea - pick element from Unsorted part and place it right pos in sorted part
    for (int i = 1; i < arr.length; i++) {
      int cur = arr[i];
      int previous = i - 1;
      // finding the correct position to insert
      while (previous >= 0 && arr[previous] < cur) {
        arr[previous + 1] = arr[previous];
        previous--;
      }
      // insertion
      arr[previous + 1] = cur;

    }

  }

  public static void countings(int arr[]) {
    int largest = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) { // counter
      largest = Math.max(arr[i], largest);
    }
    int count[] = new int[largest + 1];
    for (int i = 0; i < arr.length; i++) {
      count[arr[i]]++;
    }

    // sorting
    int j = 0;
    for (int i = 0; i < arr.length; i++) {
      while (count[i] > 0) {
        arr[j] = i;
        j++;
        count[i]--;
      }
    }

  }

  public static void counting(int arr[]) {

    int mx = 0;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > mx) {
        mx = arr[i];

      }
    }
    int count[] = new int[mx + 1];
    for (int i = 0; i < arr.length; i++) {
      int element = arr[i];
      count[element]++;

    }

    int k = 0;
    for (int i = mx; i >= 0; i--) {
      int freq = count[i];
      for (int j = 1; j < freq; j++) {
        arr[k] = 1;
        k++;
      }
    }

    System.out.println(" sorted array in decreasing order");
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i]);
    }

  }

  public static void addTwoNumbers() {
    int arr1[] = { 1, 2, 3 };
    int arr2[] = { 4, 5, 6 };

    int sum = 0;
    for (int i = 0; i < arr1.length; i++) {
      for (int j = 0; j < arr2.length; j++) {
        sum += arr1[i] + arr2[j];
      }
      System.out.print(sum + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int arr[] = { 2, 1, 0,1,2,0,2,1,0 };
    sortOs1s2s(arr) ;
    System.out.println("After sorting");

    for(int i : arr) {
      System.out.print(i + " ");
    }

    System.out.println();
  }

  public static void printarr(int arr[]) {

    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }
  public static void sortOs1s2s(int arr[]) {
    int count0 = 0 ;
    int count1 = 0 ;
    int count2 = 0 ;

    //fist we count 0s 1s 2s 
    for(int i : arr)  {
       if(i == 0) {
        count0++ ;
       }
       else if (i == 1) {
        count1++ ;
       }
      
    }


    for(int i  = 0 ; i<arr.length ; i++) {
      if(count0 > 0) {
        arr[i] = 0 ;
        count0 -- ;
      }
      else if(count1 > 0) {
        arr[i] = 1 ;
        count1 -- ;
      }
      else {
        arr[i] = 2 ;
      }
    }



  } 
}
