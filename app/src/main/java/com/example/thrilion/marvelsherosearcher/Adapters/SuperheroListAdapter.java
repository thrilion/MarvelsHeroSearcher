package com.example.thrilion.marvelsherosearcher.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thrilion.marvelsherosearcher.POJO.Superhero;
import com.example.thrilion.marvelsherosearcher.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 0kitachi on 02/12/2016.
 */

public class SuperheroListAdapter extends RecyclerView.Adapter<SuperheroListAdapter.ViewHolder>{

    private static final String TAG = "SuperheroListAdapter";

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTxtNameList;
        public TextView mTxtDescriptionList;
        public ImageView mImgList;
        public ViewHolder(View itemView) {
            super(itemView);
            this.mTxtNameList = (TextView) itemView.findViewById(R.id.txt_recycler_title);
            this.mTxtDescriptionList = (TextView) itemView.findViewById(R.id.txt_recycler_description);
            this.mImgList = (ImageView) itemView.findViewById(R.id.img_recycler);
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
        final View viewRow = LayoutInflater.from(this.mContext).inflate(R.layout.recycler_row, parent, false);
        return new ViewHolder(viewRow);
    }

    @Override
    public void onBindViewHolder(SuperheroListAdapter.ViewHolder holder, int position) {
        holder.mTxtNameList.setText(this.mHeroList.get(position).getName());
        holder.mTxtDescriptionList.setText(this.mHeroList.get(position).getDescription());
        Picasso.with(mContext).load(this.mHeroList.get(position).getImage()).into(holder.mImgList);
    }

    @Override
    public int getItemCount() {
        return this.mHeroList.size();
    }

    public void updateSuperheroList(ArrayList<Superhero> hero_list) {
        this.mHeroList = hero_list;
    }

    public void clearHeroList(){
        this.mHeroList.clear();
    }

    public List<Superhero> getHeroList(){ return mHeroList; }
}