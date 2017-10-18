package zero.one.developers.casestudy.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import zero.one.developers.casestudy.R;
import zero.one.developers.casestudy.adapter.RandomNumberAdapter;

import zero.one.developers.casestudy.service.SortingService;
import zero.one.developers.casestudy.utils.Const;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RandomNumberFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RandomNumberFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String TAG = RandomNumberFragment.class.getSimpleName();

    private RecyclerView mRandomNumberList;
    private Button mGenerateRandNumBtn,mSortRandNumsBtn;
    private int[] randomNumberArray;

    private SortedDataReciever mSortedDataReciever;


    public RandomNumberFragment() {
        // Required empty public constructor
    }



    public static RandomNumberFragment newInstance() {


        return  new RandomNumberFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentRootview =  inflater.inflate(R.layout.fragment_random_number, container, false);
        setupViews(fragmentRootview);
        return fragmentRootview;
    }


    /*
    * method to initilaize views
    * */
    private void setupViews(View rootview){

        mRandomNumberList =  rootview.findViewById(R.id.randomnumber_recyclerview);
        mGenerateRandNumBtn =  rootview.findViewById(R.id.generate_numbers_btn);
        mSortRandNumsBtn =  rootview.findViewById(R.id.sort_numbers_btn);

        mGenerateRandNumBtn.setOnClickListener(this);
        mSortRandNumsBtn.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true);

        mRandomNumberList.setLayoutManager(linearLayoutManager);

        mRandomNumberList.setItemAnimator(new DefaultItemAnimator());
        mRandomNumberList.addItemDecoration(new DividerItemDecoration(mRandomNumberList.getContext(),
                linearLayoutManager.getOrientation()));


        new GenerateRandNumTask().execute();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.generate_numbers_btn:
                new GenerateRandNumTask().execute();
                break;
            case R.id.sort_numbers_btn:

                Intent sortServiceIntent = new Intent(getContext(), SortingService.class);

                sortServiceIntent.putExtra(Const.RANDOM_INT_ARRAY, randomNumberArray);
                getContext().startService(sortServiceIntent);
                break;
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        mSortedDataReciever = new SortedDataReciever();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Const.SORTED_DATA_ACTION);
        getActivity().registerReceiver(mSortedDataReciever,intentFilter);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mSortedDataReciever != null)
            getActivity().unregisterReceiver(mSortedDataReciever);
    }

    /*
    * Aysnctask class to generate Random Numbers
    * */
    private  class GenerateRandNumTask extends AsyncTask<Void,Void,int[]>{

        @Override
        protected int[] doInBackground(Void... voids) {
            randomNumberArray = new int[20] ;
            int nm;

            for(int i= 0 ; i<20;i++){

                nm = (int)  Math.round(Math.random()*(99 - 10) + 10);
               randomNumberArray[i] = nm;
                //Log.d(TAG,"Number :" +nm );
            }


            return randomNumberArray;
        }

        @Override
        protected void onPostExecute(int[] ints) {
            super.onPostExecute(ints);
            RandomNumberAdapter numberAdapter = new RandomNumberAdapter(ints);
            mRandomNumberList.setAdapter(numberAdapter);
            mRandomNumberList.scrollToPosition(numberAdapter.getItemCount()-1);

           // numberAdapter.notifyDataSetChanged();
        }
    }

    /*
    * custom broadcastReciver to get sorted data from service
    * */

    private class SortedDataReciever extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.hasExtra(Const.MERGED_SORTED_INT_ARRAY) && intent.hasExtra(Const.QUICK_SORTED_INT_ARRAY)){
                int[] mergesortedArray = intent.getIntArrayExtra(Const.MERGED_SORTED_INT_ARRAY);
                int[] quicksortedArray = intent.getIntArrayExtra(Const.QUICK_SORTED_INT_ARRAY);
            //   Toast.makeText(getContext(),"quick sort "+String.valueOf(quicksortedArray[0]),Toast.LENGTH_SHORT).show();

                SortedNumberFragment sortedNumberFragment = SortedNumberFragment.newInstance(quicksortedArray,mergesortedArray);
                FragmentTransaction mFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                mFragmentTransaction.replace(R.id.root_container,sortedNumberFragment);
                mFragmentTransaction.commit();
            }

        }
    }
}
