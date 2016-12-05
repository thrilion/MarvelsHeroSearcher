package com.example.thrilion.marvelsherosearcher.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thrilion.marvelsherosearcher.POJO.Comic;
import com.example.thrilion.marvelsherosearcher.R;

import java.util.List;

/**
 * Created by 0kitachi on 05/12/2016.
 */

public class ComicsContentAdapter extends RecyclerView.Adapter<ComicsContentAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTxtComicTitle;
        public TextView mTxtComicDescription;
        public ImageView mImgComic;
        public ViewHolder(View itemView) {
            super(itemView);
            this.mTxtComicTitle = (TextView) itemView.findViewById(R.id.txt_recycler_title);
            this.mTxtComicDescription = (TextView) itemView.findViewById(R.id.txt_recycler_description);
            this.mImgComic = (ImageView) itemView.findViewById(R.id.img_recycler);
        }
    }

    private Context mContext;
    private List<Comic> mComicList;

    public ComicsContentAdapter(Context context, List<Comic> comicList){
        this.mContext = context;
        this.mComicList = comicList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View viewRow = LayoutInflater.from(this.mContext).inflate(R.layout.recycler_row, parent, false);
        return new ComicsContentAdapter.ViewHolder(viewRow);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.mComicList.size();
    }

}
