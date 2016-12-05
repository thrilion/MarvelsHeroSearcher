package com.example.thrilion.marvelsherosearcher.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thrilion.marvelsherosearcher.Fragments.SuperheroInfoFragment;
import com.example.thrilion.marvelsherosearcher.Fragments.SuperheroListFragment;
import com.example.thrilion.marvelsherosearcher.POJO.Superhero;
import com.example.thrilion.marvelsherosearcher.R;
import com.squareup.picasso.Picasso;

public class SuperheroInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String IMG_EXTENSION = ".jpg";
    private Superhero mSuperhero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superhero_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText(R.string.comics));
        tabs.addTab(tabs.newTab().setText(R.string.eventos));

        // Habilitamos el Action UP button
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Recuperamos el intent y el objeto Superhero seleccionado
        Intent intent = getIntent();
        this.mSuperhero = intent.getParcelableExtra(SuperheroListFragment.EXTRA_SUPERHERO);
        SuperheroInfoFragment.setSuperhero(this.mSuperhero);

        // Views de la activity
        final ImageView mImgSuperhero = (ImageView) findViewById(R.id.img_superhero);
        final TextView mTxtSuperheroName = (TextView) findViewById(R.id.txt_superhero_name);
        final TextView mTxtSuperheroDesc = (TextView) findViewById(R.id.txt_superhero_description);
        final Button mDetailsButton = (Button) findViewById(R.id.btn_detail);mDetailsButton.setOnClickListener(this);
        final Button mWikiButton = (Button) findViewById(R.id.btn_wiki);
        mWikiButton.setOnClickListener(this);
        final Button mComicsButton;mComicsButton = (Button) findViewById(R.id.btn_comics);
        mComicsButton.setOnClickListener(this);

        // Enlazamos los valores del Superheroe seleccionado con la vista
        Picasso.with(this).load(mSuperhero.getImage() + IMG_EXTENSION).into(mImgSuperhero);
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
