package com.example.thrilion.marvelsherosearcher.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thrilion.marvelsherosearcher.POJO.Superhero;
import com.example.thrilion.marvelsherosearcher.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuperheroInfoFragment extends Fragment {

    private static final String TAG = "SuperheroInfoFragment";
    private static final String IMG_EXTENSION = ".jpg";
    private static Superhero mSuperhero;
    private ImageView mImgSuperhero;
    private TextView mTxtSuperheroName;
    private TextView mTxtSuperheroDesc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView =  inflater.inflate(R.layout.fragment_superhero_info, container, false);

        mImgSuperhero = (ImageView) fragmentView.findViewById(R.id.img_superhero);
        mTxtSuperheroName = (TextView) fragmentView.findViewById(R.id.txt_superhero_name);
        mTxtSuperheroDesc = (TextView) fragmentView.findViewById(R.id.txt_superhero_description);

        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Picasso.with(getActivity()).load(mSuperhero.getImage() + IMG_EXTENSION).into(mImgSuperhero);
        mTxtSuperheroName.setText(mSuperhero.getName());
        mTxtSuperheroDesc.setText(mSuperhero.getDescription());
    }

    public static void setSuperhero(Superhero superhero){
        mSuperhero = superhero;
    }

}
