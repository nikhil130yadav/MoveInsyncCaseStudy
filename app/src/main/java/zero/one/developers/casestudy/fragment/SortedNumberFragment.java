package zero.one.developers.casestudy.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import zero.one.developers.casestudy.R;
import zero.one.developers.casestudy.adapter.RandomNumberAdapter;
import zero.one.developers.casestudy.adapter.SortedAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SortedNumberFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SortedNumberFragment extends Fragment {

    private static int[] mMergeSortedArray;
    private static int[] mQuickSortedArray;

    public static SortedNumberFragment newInstance(int[] quickSortedArray,int[] mergeSortedArray) {
        mQuickSortedArray =  quickSortedArray;
        mMergeSortedArray = mergeSortedArray;
        return new SortedNumberFragment();
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View sortedRootview = inflater.inflate(R.layout.fragment_sorted_number, container, false);
        setupViews(sortedRootview);

        return  sortedRootview;
    }

    private void setupViews(View rootview){

      /*
        * quick column setup
        * */
        RecyclerView mQuickSortedList = rootview.findViewById(R.id.quicksort_recyclerview);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,true);
        mQuickSortedList.setLayoutManager(linearLayoutManager);
        mQuickSortedList.setItemAnimator(new DefaultItemAnimator());
        mQuickSortedList.addItemDecoration(new DividerItemDecoration(mQuickSortedList.getContext(),
                linearLayoutManager.getOrientation()));

        SortedAdapter quiclAdapter = new SortedAdapter(mQuickSortedArray);
        mQuickSortedList.setAdapter(quiclAdapter);
        mQuickSortedList.invalidate();


        /*
        * merge sort column setup
        * */

        RecyclerView mMergeSortedList = rootview.findViewById(R.id.mergesort_recyclerview);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,true);
        mMergeSortedList.setLayoutManager(linearLayoutManager2);

        mMergeSortedList.addItemDecoration(new DividerItemDecoration(getContext(),
                linearLayoutManager2.getOrientation()));

        SortedAdapter mergeAdapter3 = new SortedAdapter(mMergeSortedArray);

        mMergeSortedList.setAdapter(mergeAdapter3);






    }
}
