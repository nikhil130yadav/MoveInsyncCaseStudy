package zero.one.developers.casestudy.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import zero.one.developers.casestudy.utils.Const;
import zero.one.developers.casestudy.utils.MergeSort;
import zero.one.developers.casestudy.utils.QuickSort;

/**
 * Created by Inspiron on 10/18/2017.
 */

public class SortingService extends Service {

    int[] sortedArray;
    int[] quickSortedArray;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        int[] unsortedArray;


        // if (intent.hasExtra(Const.RANDOM_INT_ARRAY)){
        unsortedArray = intent.getIntArrayExtra(Const.RANDOM_INT_ARRAY);

        MergeSort mMergeSort = new MergeSort();
        sortedArray =mMergeSort.sort(unsortedArray);
        QuickSort mQuickSort = new QuickSort();
        quickSortedArray = mQuickSort.sort(unsortedArray);
        Intent sortedDataIntent = new Intent();
        sortedDataIntent.setAction(Const.SORTED_DATA_ACTION);
        sortedDataIntent.putExtra(Const.MERGED_SORTED_INT_ARRAY,sortedArray);
        sortedDataIntent.putExtra(Const.QUICK_SORTED_INT_ARRAY,quickSortedArray);
        sendBroadcast(sortedDataIntent);
        stopSelf();

    }


}
