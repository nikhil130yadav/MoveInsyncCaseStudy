package zero.one.developers.casestudy.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;

import zero.one.developers.casestudy.R;
import zero.one.developers.casestudy.fragment.RandomNumberFragment;

/**
 * Created by Inspiron on 10/17/2017.
 */

public class RandomNumberAdapter extends RecyclerView.Adapter<RandomNumberAdapter.ViewHolder> {

    private Context mContext;
    private int[] mRandomList;
    public RandomNumberAdapter(int[] randomList) {
        this.mRandomList = randomList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from( parent.getContext());
        View rootview = inflater.inflate(R.layout.random_number_listitem,parent,false);

        return new ViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cv = (CardView) holder.cardView;

        if(position % 2 == 0) {
            cv.setCardBackgroundColor(mContext.getResources().getColor(R.color.pink));
        }else
        {
            cv.setCardBackgroundColor(mContext.getResources().getColor(R.color.teal_a700));}


        TextView generateRandNumTv = (TextView) cv.findViewById(R.id.randomnumber_textview);
        generateRandNumTv.setText(String.valueOf(mRandomList[position]));

    }

    @Override
    public int getItemCount() {
        return mRandomList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.cardView = (CardView)itemView;
        }
    }
}
