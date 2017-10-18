package zero.one.developers.casestudy.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import zero.one.developers.casestudy.R;

/**
 * Created by Inspiron on 10/18/2017.
 */

public class SortedAdapter extends RecyclerView.Adapter<SortedAdapter.ViewHolder> {
    private Context mContext;
    private int[] mRandomList;
    public SortedAdapter(int[] randomList) {
        this.mRandomList = randomList;
    }


    @Override
    public SortedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from( parent.getContext());
        View rootview = inflater.inflate(R.layout.sorted_number_item,parent,false);

        return new SortedAdapter.ViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(SortedAdapter.ViewHolder holder, int position) {
        CardView cv = (CardView) holder.cardView;
        TextView generateRandNumTv = (TextView) cv.findViewById(R.id.sortednumber_textview);
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
