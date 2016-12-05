package com.example.thrilion.marvelsherosearcher.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thrilion.marvelsherosearcher.POJO.Event;
import com.example.thrilion.marvelsherosearcher.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 0kitachi on 05/12/2016.
 */

public class EventsContentAdapter extends RecyclerView.Adapter<EventsContentAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTxtEventTitle;
        public TextView mTxtEventDescription;
        public ImageView mImgComic;
        public ViewHolder(View itemView) {
            super(itemView);
            this.mTxtEventTitle = (TextView) itemView.findViewById(R.id.txt_recycler_title);
            this.mTxtEventDescription = (TextView) itemView.findViewById(R.id.txt_recycler_description);
            this.mImgComic = (ImageView) itemView.findViewById(R.id.img_recycler);
        }
    }

    private Context mContext;
    private List<Event> mEventList;

    public EventsContentAdapter(Context context, List<Event> eventList){
        this.mContext = context;
        this.mEventList = eventList;
    }

    @Override
    public EventsContentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View viewRow = LayoutInflater.from(this.mContext).inflate(R.layout.recycler_row, parent, false);
        return new EventsContentAdapter.ViewHolder(viewRow);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTxtEventTitle.setText(this.mEventList.get(position).getTitle());
        holder.mTxtEventDescription.setText(this.mEventList.get(position).getDescription());
        Picasso.with(mContext).load(this.mEventList.get(position).getImage()).into(holder.mImgComic);
    }

    @Override
    public int getItemCount() {
        return this.mEventList.size();
    }

    public void updateComicList(ArrayList<Event> eventList) {
        this.mEventList = eventList;
    }
}
