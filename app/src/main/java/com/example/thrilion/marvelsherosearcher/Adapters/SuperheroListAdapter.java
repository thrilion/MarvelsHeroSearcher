package com.example.thrilion.marvelsherosearcher.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thrilion.marvelsherosearcher.POJO.Superhero;
import com.example.thrilion.marvelsherosearcher.R;

import java.util.List;

/**
 * Created by 0kitachi on 02/12/2016.
 */

public class SuperheroListAdapter extends RecyclerView.Adapter<SuperheroListAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImgList;
        public TextView mTxtNameList;
        public TextView mTxtDescriptionList;
        public ViewHolder(View itemView) {
            super(itemView);
            this.mImgList = (ImageView) itemView.findViewById(R.id.img_superhero);
            this.mTxtNameList = (TextView) itemView.findViewById(R.id.txt_superhero_name);
            this.mTxtDescriptionList = (TextView) itemView.findViewById(R.id.txt_superhero_description);
        }
    }

    private Context mContext;
    private List<Superhero> mHeroList;

    public SuperheroListAdapter(Context context, List<Superhero> listHeroes){
        this.mContext = context;
        this.mHeroList = listHeroes;
    }

    @Override
    public SuperheroListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewRow = LayoutInflater.from(this.mContext).inflate(R.layout.superhero_list_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(viewRow);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SuperheroListAdapter.ViewHolder holder, int position) {
        holder.mImgList.setImageResource(R.drawable.spider);
        holder.mTxtNameList.setText(this.mHeroList.get(position).getName());
        holder.mTxtDescriptionList.setText(this.mHeroList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return this.mHeroList.size();
    }

}

/*
package com.example.thrilion.marvelsherosearcher.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thrilion.marvelsherosearcher.POJO.Superhero;

import java.util.List;

public class SuperheroListAdapter extends RecyclerView.Adapter<SuperheroListAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImgList;
        public TextView mTxtName;
        public TextView mTxtDescription;
        public ViewHolder(View itemView) {
            super(itemView);
            this.mImgList = (ImageView) itemView.findViewById(R.id.img_list_view_row_item);
            this.mTxtTitle = (TextView) itemView.findViewById(R.id.title_list_view_row_item);
            this.mTxtBody = (TextView) itemView.findViewById(R.id.body_list_view_row_item);
        }
    }

    private Context mContext;
    private List<Superhero> mItemList;

    public SuperheroListAdapter(Context context, List<Superhero> listItem){
        this.mContext = context;
        this.mItemList = listItem;
    }

    @Override
    public SuperheroListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewRow = LayoutInflater.from(this.mContext).inflate(R.layout.list_view_row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(viewRow);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SuperheroListAdapter.ViewHolder holder, int position) {
        holder.mImgList.setImageResource(R.drawable.androidicon);
        holder.mTxtTitle.setText(this.mItemList.get(position).getmTitle());
        holder.mTxtBody.setText(this.mItemList.get(position).getmBody());
    }

    @Override
    public int getItemCount() {
        return this.mItemList.size();
    }
}
 */