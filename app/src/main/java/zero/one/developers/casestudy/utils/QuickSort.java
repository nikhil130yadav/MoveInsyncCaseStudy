package zero.one.developers.casestudy.utils;

/**
 * Created by Inspiron on 10/17/2017.
 */

public class QuickSort {
    private int[] originalArray;
    private int totallength = 20;

    public int[] sort(int[] randomNumber){
        this.originalArray = randomNumber;
        quickSort(0,totallength-1);

        return originalArray;
    }

    private void quickSort(int low, int high) {
        int i = low;
        int j = high;
        int middle = low+(high-low)/2;
        int pivot = originalArray[middle];

        while (i <j){
            while (originalArray[i] < pivot){
                i++;
            }
            while (originalArray[j]> pivot){
                j--;
            }

            if (i <= j){
                swapValues(i,j);
                i++;
                j--;
            }
            if (low < j)
                quickSort(low,j);
            if (i < high)
                quickSort(i,high);
        }
    }

    private void swapValues(int i, int j) {
        int temp = originalArray[i];
        originalArray[i] = originalArray[j];
        originalArray[j] = temp;
    }

}
