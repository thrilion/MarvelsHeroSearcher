package com.example.thrilion.marvelsherosearcher.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thrilion.marvelsherosearcher.Adapters.ResourcesAdapter;
import com.example.thrilion.marvelsherosearcher.Fragments.ComicsContentFragment;
import com.example.thrilion.marvelsherosearcher.Fragments.EventsContentFragment;
import com.example.thrilion.marvelsherosearcher.POJO.Superhero;
import com.example.thrilion.marvelsherosearcher.R;
import com.squareup.picasso.Picasso;

public class SuperheroInfoActivity extends AppCompatActivity
        implements View.OnClickListener, ComicsContentFragment.OnComicsContentInterface,
        EventsContentFragment.OnEventsContentInterface{

    private static final String TAG = "SuperheroInfoActivity";
    private static final String COMICS_TAB_TITLE = "Comics";
    private static final String EVENTS_TAB_TITLE = "Eventos";
    private static final int COMICS_TAB_POS = 0;
    private static final int EVENTS_TAB_POS = 1;
    private Superhero mSuperhero;
    private TabLayout mTabs;
    private int mComicListSize;
    private int mEventListSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superhero_info);

        // Seteamos la Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Seteamos el ViewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.resources_viewpager);
        setupViewPager(viewPager);

        // Seteamos el TabLayout
        mTabs = (TabLayout) findViewById(R.id.resources_tabs);
        mTabs.setupWithViewPager(viewPager);

        // Habilitamos el up button
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Recuperamos el intent y el objeto Superhero seleccionado
        Intent intent = getIntent();
        this.mSuperhero = intent.getParcelableExtra(MainActivity.EXTRA_SUPERHERO);

        // Views de la activity
        final ImageView imgSuperhero = (ImageView) findViewById(R.id.img_superhero);
        final TextView txtSuperheroName = (TextView) findViewById(R.id.txt_superhero_name);
        final TextView txtSuperheroDesc = (TextView) findViewById(R.id.txt_superhero_description);
        final Button detailsButton = (Button) findViewById(R.id.btn_detail);
        detailsButton.setOnClickListener(this);
        final Button wikiButton = (Button) findViewById(R.id.btn_wiki);
        wikiButton.setOnClickListener(this);
        final Button comicsButton = (Button) findViewById(R.id.btn_comics);
        comicsButton.setOnClickListener(this);

        // Enlazamos los valores del Superheroe seleccionado con la vista
        Picasso.with(this).load(this.mSuperhero.getImage()).into(imgSuperhero);
        txtSuperheroName.setText(this.mSuperhero.getName());
        txtSuperheroDesc.setText(this.mSuperhero.getDescription());

        // Comprobamos que el Superheroe tenga disponibles las urls recogidas por la API
        // En caso de faltar alguna, deshabilitamos el botón
        if(this.mSuperhero.getDetailLink() == null){
            detailsButton.setEnabled(false);
        }if(this.mSuperhero.getWikiLink() == null){
            wikiButton.setEnabled(false);
        }if(this.mSuperhero.getComicLink() == null){
            comicsButton.setEnabled(false);
        }
    }

    // Añadimos los Fragments al TabLayout
    private void setupViewPager(ViewPager viewPager) {
        ResourcesAdapter adapter = new ResourcesAdapter(getSupportFragmentManager());
        adapter.addFragment(new ComicsContentFragment(), COMICS_TAB_TITLE);
        adapter.addFragment(new EventsContentFragment(), EVENTS_TAB_TITLE);
        viewPager.setAdapter(adapter);
    }

    @Override
    public int getSelectedSuperheroId() {
        return mSuperhero.getId();
    }

    @Override
    public void setComicListSize(int size) {
        this.mComicListSize = size;
        this.mTabs.getTabAt(COMICS_TAB_POS).setText("(" + size + ") " + COMICS_TAB_TITLE);
    }

    @Override
    public void setEventListSize(int size) {
        this.mEventListSize = size;
        this.mTabs.getTabAt(EVENTS_TAB_POS).setText("(" + size + ") " + EVENTS_TAB_TITLE);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_detail:
                Uri uriDetail = Uri.parse(this.mSuperhero.getDetailLink());
                Intent intentDetail = new Intent(Intent.ACTION_VIEW, uriDetail);
                startActivity(intentDetail);
                break;
            case R.id.btn_wiki:
                Uri uriWiki = Uri.parse(this.mSuperhero.getWikiLink());
                Intent intentWiki = new Intent(Intent.ACTION_VIEW, uriWiki);
                startActivity(intentWiki);
                break;
            case R.id.btn_comics:
                Uri uriComics = Uri.parse(this.mSuperhero.getComicLink());
                Intent intentComics = new Intent(Intent.ACTION_VIEW, uriComics);
                startActivity(intentComics);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
            case android.R.id.home:
                // Si se pulsa el up button
                // Obtenemos el intent de la actividad padre
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                // Comprobamos si DetailActivity no se creó desde CourseActivity
                if (NavUtils.shouldUpRecreateTask(this, upIntent)
                        || this.isTaskRoot()) {

                    // Construimos de nuevo la tarea para ligar ambas actividades
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities();
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Terminamos con el método correspondiente para Android 5.x
                    this.finishAfterTransition();
                    return true;
                }

                // Dejamos que el sistema maneje el comportamiento del up button
                return false;
        }
    }
}
