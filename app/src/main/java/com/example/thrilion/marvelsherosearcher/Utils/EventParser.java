package com.example.thrilion.marvelsherosearcher.Utils;

import android.content.Context;
import android.util.Log;

import com.example.thrilion.marvelsherosearcher.POJO.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 0kitachi on 05/12/2016.
 */

public class EventParser {

    private static final String TAG = "EventParser";

    public static ArrayList<Event> ParseEventJson(Context context, String eventJsonStr) throws JSONException {
        final String json_data="data";
        final String json_results="results";
        final String json_id="id";
        final String json_title="title";
        final String json_description="description";
        final String json_thumbnail="thumbnail";
        final String json_path="path";
        final String json_extension="extension";

        ArrayList<Event> eventList= new ArrayList<>();

        JSONObject eventJson = new JSONObject(eventJsonStr);
        JSONArray eventArray = eventJson.getJSONObject(json_data).getJSONArray(json_results);

        for (int i = 0; i < eventArray.length(); i++) {
            JSONObject data_comic = eventArray.getJSONObject(i);
            Event event = new Event();
            // Id
            event.setId(data_comic.getInt(json_id));
            // Title
            event.setTitle(data_comic.getString(json_title));
            // Description
            event.setDescription(data_comic.getString(json_description));
            // Imagen
            JSONObject thumb = data_comic.getJSONObject(json_thumbnail);
            event.setImage(thumb.getString(json_path) + "." + thumb.getString(json_extension));

            eventList.add(event);
        }

        for (Event result : eventList) {
            Log.i(TAG," preparing data " + result.getTitle());
        }

        return eventList;
    }
}
