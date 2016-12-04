package com.example.thrilion.marvelsherosearcher.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thrilion.marvelsherosearcher.POJO.Superhero;
import com.example.thrilion.marvelsherosearcher.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuperheroInfoFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "SuperheroInfoFragment";
    private static final String IMG_EXTENSION = ".jpg";
    private static Superhero mSuperhero;
    private ImageView mImgSuperhero;
    private TextView mTxtSuperheroName;
    private TextView mTxtSuperheroDesc;
    private Button mDetailsButton;
    private Button mWikiButton;
    private Button mComicsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView =  inflater.inflate(R.layout.fragment_superhero_info, container, false);

        mImgSuperhero = (ImageView) fragmentView.findViewById(R.id.img_superhero);
        mTxtSuperheroName = (TextView) fragmentView.findViewById(R.id.txt_superhero_name);
        mTxtSuperheroDesc = (TextView) fragmentView.findViewById(R.id.txt_superhero_description);
        mDetailsButton = (Button) fragmentView.findViewById(R.id.btn_detail);
        mDetailsButton.setOnClickListener(this);
        mWikiButton = (Button) fragmentView.findViewById(R.id.btn_wiki);
        mWikiButton.setOnClickListener(this);
        mComicsButton = (Button) fragmentView.findViewById(R.id.btn_comics);
        mComicsButton.setOnClickListener(this);

        TabLayout tabs = (TabLayout) fragmentView.findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText(R.string.comics));
        tabs.addTab(tabs.newTab().setText(R.string.eventos));

        // Seteamos el ViewPager para que la TabLayout tenga la función de swipe
        //ViewPager viewPager = (ViewPager) fragmentView.findViewById(R.id.viewpager);
        //setupViewPager(viewPager);
        // Seteamos la TabLayout para los Comics y Eventos
        //TabLayout tabs = (TabLayout) fragmentView.findViewById(R.id.tabs);
        //tabs.setupWithViewPager(viewPager);

        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Enlazamos los valores del Superheroe seleccionado con la vista
        Picasso.with(getActivity()).load(mSuperhero.getImage() + IMG_EXTENSION).into(mImgSuperhero);
        mTxtSuperheroName.setText(mSuperhero.getName());
        mTxtSuperheroDesc.setText(mSuperhero.getDescription());

        // Comprobamos que el Superheroe tenga disponibles las urls recogidas por la API
        // En caso de faltar alguna, deshabilitamos el botón
        if(mSuperhero.getDetailLink() == null){
            mDetailsButton.setEnabled(false);
        }if(mSuperhero.getWikiLink() == null){
            mWikiButton.setEnabled(false);
        }if(mSuperhero.getComicLink() == null){
            mComicsButton.setEnabled(false);
        }
    }

    public static void setSuperhero(Superhero superhero){
        mSuperhero = superhero;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_detail:
                Uri uriDetail = Uri.parse(mSuperhero.getDetailLink());
                Intent intentDetail = new Intent(Intent.ACTION_VIEW, uriDetail);
                startActivity(intentDetail);
                break;
            case R.id.btn_wiki:
                Uri uriWiki = Uri.parse(mSuperhero.getWikiLink());
                Intent intentWiki = new Intent(Intent.ACTION_VIEW, uriWiki);
                startActivity(intentWiki);
                break;
            case R.id.btn_comics:
                Uri uriComics = Uri.parse(mSuperhero.getComicLink());
                Intent intentComics = new Intent(Intent.ACTION_VIEW, uriComics);
                startActivity(intentComics);
                break;
        }
    }
}
