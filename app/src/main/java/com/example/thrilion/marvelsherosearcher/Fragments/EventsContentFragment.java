package com.example.thrilion.marvelsherosearcher.Fragments;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thrilion.marvelsherosearcher.Adapters.ComicsContentAdapter;
import com.example.thrilion.marvelsherosearcher.Adapters.EventsContentAdapter;
import com.example.thrilion.marvelsherosearcher.POJO.Event;
import com.example.thrilion.marvelsherosearcher.R;
import com.example.thrilion.marvelsherosearcher.Utils.EventParser;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsContentFragment extends Fragment {

    private static final String TAG = "EventContentFragment";
    private static final String URL_EVENT = "http://gateway.marvel.com/v1/public/events?limit=100&characters=";
    private static final String CREDENTIAL = "&ts=1&apikey=40a72695f7fbb7b6f5ef9a1fb23fd575&hash=a7298b72b9e4e288813727adbb4c4562";
    private EventsContentAdapter mAdapter;

    public interface OnEventsContentInterface{
        int getSelectedSuperheroId();
    }
    private EventsContentFragment.OnEventsContentInterface mEventsContentCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mEventsContentCallback = (EventsContentFragment.OnEventsContentInterface) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(this.getActivity().toString() + "must implement OnComicsContentInterface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_events_content, container, false);

        // Creamos el Recyclerview y lo asignamos al adapter
        final RecyclerView recyclerView = (RecyclerView) fragmentView.findViewById(R.id.events_content_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(fragmentView.getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new EventsContentAdapter(fragmentView.getContext(), new ArrayList<Event>());
        recyclerView.setAdapter(mAdapter);

        // Recuperamos la id del Superheroe seleccionado a traves del callback
        int superheroId = this.mEventsContentCallback.getSelectedSuperheroId();

        // Llamamos a la AsyncTask pasandole la URL desde la que descargar la lista de personajes
        new EventsContentFragment.EventListBackgroundTask().execute(URL_EVENT + superheroId + CREDENTIAL);

        return fragmentView;
    }

    // AsyncTask para las peticiones a la API de Marvel
    private class EventListBackgroundTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String[] params) {
            StringBuilder resourceJsonStr = new StringBuilder();
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            try {
                // Preparamos la conexión
                java.net.URL myUrl = new URL(params[0]);
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
                        resourceJsonStr.append(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
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
            return resourceJsonStr.toString();
        }

        @Override
        protected void onPostExecute(String resourceJsonStr) {
            super.onPostExecute(resourceJsonStr);

            if (resourceJsonStr != null) {
                ArrayList<Event> eventList = null;
                // Parseamos el JSON, contenido en el String resultante, en un ArrayList
                try {
                    eventList = EventParser.ParseEventJson(getContext(), resourceJsonStr);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Hacemos un update de la vista
                mAdapter.updateComicList(eventList);
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
