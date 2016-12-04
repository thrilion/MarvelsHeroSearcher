package com.example.thrilion.marvelsherosearcher.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thrilion.marvelsherosearcher.Activities.SuperheroInfoActivity;
import com.example.thrilion.marvelsherosearcher.Adapters.SuperheroListAdapter;
import com.example.thrilion.marvelsherosearcher.POJO.Superhero;
import com.example.thrilion.marvelsherosearcher.R;
import com.example.thrilion.marvelsherosearcher.Utils.SuperheroListItemClickListener;
import com.example.thrilion.marvelsherosearcher.Utils.SuperheroParser;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuperheroListFragment extends Fragment {

    private static final String TAG = "SuperheroListFragment";
    private static final String URL = "http://gateway.marvel.com/v1/public/characters?limit=100&nameStartsWith=";
    private static final String CREDENTIAL = "&ts=1&apikey=40a72695f7fbb7b6f5ef9a1fb23fd575&hash=a7298b72b9e4e288813727adbb4c4562";
    public static final String EXTRA_SUPERHERO = "SUPERHERO";

    private ConnectivityManager mConnectionManager;
    private NetworkInfo mNetworkInfo;
    private SuperheroListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_superhero_list, container, false);

        // Creamos el Recyclerview y lo asignamos al adapter
        final RecyclerView recyclerView = (RecyclerView) fragmentView.findViewById(R.id.superhero_list_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(fragmentView.getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new SuperheroListAdapter(fragmentView.getContext(), new ArrayList<Superhero>());
        recyclerView.setAdapter(adapter);

        // Listener del RecyclerView
        recyclerView.addOnItemTouchListener(
            new SuperheroListItemClickListener(fragmentView.getContext(), new SuperheroListItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Superhero sh = adapter.getHeroList().get(position);
                    Intent intent = new Intent(getActivity(), SuperheroInfoActivity.class);
                    intent.putExtra(EXTRA_SUPERHERO, sh);
                    getContext().startActivity(intent);
                }
            })
        );

        // Comprobamos el estado de la conexión a Internet
        this.mConnectionManager = (ConnectivityManager) fragmentView.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        this.mNetworkInfo = mConnectionManager.getActiveNetworkInfo();

        if(mNetworkInfo != null && mNetworkInfo.isConnected()){
            Log.i(SuperheroListFragment.TAG, mNetworkInfo.getTypeName() + " " + mNetworkInfo.getState().toString());

            // Creamos el EditText y el listener para poblar la lista a partir de los 3 carácteres
            final EditText heroName = (EditText) fragmentView.findViewById(R.id.edit_txt_hero_name);
            heroName.addTextChangedListener(new TextWatcher(){
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
                @Override
                public void afterTextChanged(Editable editable) {
                    if(heroName.getText().toString().length() >= 3){
                        // Preparamos la URL desde la que conectarnos a la API de Marvel
                        String query = null;
                        try {
                            query = URLEncoder.encode(heroName.getText().toString(), "utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        if(query != null) {
                            // Llamamos a la AsyncTask pasandole la URL desde la que descargar la lista de personajes
                            new CharacterListBackgroundTask().execute(URL + query + CREDENTIAL);
                        }else{
                            Toast.makeText(getContext(),"Error al entrar en el Superhéroe", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        adapter.clearHeroList();
                        adapter.notifyDataSetChanged();
                    }
                }
            });
        }else{
            // En caso de error de conexión, mostramos los mensajes informativos pertinentes
            Log.e(SuperheroListFragment.TAG, "Sin conexión a Internet");
            Toast.makeText(fragmentView.getContext(), R.string.sin_conexion, Toast.LENGTH_LONG).show();
        }
        return fragmentView;
    }

    // AsyncTask para las peticiones a la API de Marvel
    private class CharacterListBackgroundTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String[] params) {
            StringBuilder heroJsonStr = new StringBuilder();
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            try {
                // Preparamos la conexión
                URL myUrl = new URL(params[0]);
                Log.i(TAG, "The URL is: " + params[0]);
                urlConnection = (HttpURLConnection) myUrl.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);
                // Iniciamos la conexión
                urlConnection.connect();
                int respCode = urlConnection.getResponseCode();
                Log.i(TAG, "HTTP response " + respCode);
                if (respCode == HttpURLConnection.HTTP_OK) {
                    // En caso que la conexión haya sido correcta, pasamos a tratar la respuesta
                    InputStream inputStream = urlConnection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        heroJsonStr.append(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(TAG, "Error closing stream", e);
                    }
                }
            }
            return heroJsonStr.toString();
        }

        @Override
        protected void onPostExecute(String heroJsonStr) {
            super.onPostExecute(heroJsonStr);

            if (heroJsonStr != null) {
                ArrayList<Superhero> heroList = null;
                // Parseamos el JSON, contenido en el String resultante, en un ArrayList
                try {
                    heroList = SuperheroParser.ParseSuperheroJson(getActivity(), heroJsonStr);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Hacemos un update de la vista
                adapter.updateSuperheroList(heroList);
                adapter.notifyDataSetChanged();

            }else {
                Log.i(TAG, "No hay superheroes que mostrar");
            }
        }
    }
}
