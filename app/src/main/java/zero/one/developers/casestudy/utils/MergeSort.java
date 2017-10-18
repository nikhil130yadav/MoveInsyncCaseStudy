package zero.one.developers.casestudy.utils;

/**
 * Created by Inspiron on 10/17/2017.
 */

public class MergeSort {
    private int[] orignalArray;
    private int[] tempArray;

    private int totalLength = 20;

    public int[] sort(int[] randomNumbers){
        this.orignalArray = randomNumbers;
        this.tempArray = new int[totalLength];
        mergeSort(0,totalLength-1);
        return orignalArray;
    }

    private void mergeSort(int low, int high) {
        if(low < high){
            int middle = low + (high-low)/2;
            mergeSort(low, middle);
            mergeSort(middle+1,high);
            merge(low,middle,high);
        }
    }

    private void merge(int low, int middle, int high) {
        for (int i=low;i<=high;i++){
            tempArray[i] = orignalArray[i];
        }
        int i=low,j=middle+1,k=low;

        while (i <=middle && j <= high){
            if (tempArray[i] <= tempArray[j]){
                orignalArray[k] = tempArray[i];
                i++;
            }else{
                orignalArray[k] = tempArray[j];
                j++;
            }
            k++;
        }
        while (i <= middle){
            orignalArray[k] = tempArray[i];
            k++;
            i++;
        }
    }
}
