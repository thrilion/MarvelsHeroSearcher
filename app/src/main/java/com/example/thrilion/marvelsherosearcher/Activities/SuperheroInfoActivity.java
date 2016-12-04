package com.example.thrilion.marvelsherosearcher.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.thrilion.marvelsherosearcher.Fragments.SuperheroInfoFragment;
import com.example.thrilion.marvelsherosearcher.Fragments.SuperheroListFragment;
import com.example.thrilion.marvelsherosearcher.POJO.Superhero;
import com.example.thrilion.marvelsherosearcher.R;

public class SuperheroInfoActivity extends AppCompatActivity {

    private Superhero mSuperhero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superhero_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Habilitamos el Action UP button
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Recuperamos el intent y el objeto Superhero seleccionado
        Intent intent = getIntent();
        //Bundle bundle = intent.getExtras();
        this.mSuperhero = intent.getParcelableExtra(SuperheroListFragment.EXTRA_SUPERHERO);
        //this.mSuperhero = bundle.getParcelable(SuperheroListFragment.EXTRA_SUPERHERO);
        SuperheroInfoFragment.setSuperhero(this.mSuperhero);
    }

}
