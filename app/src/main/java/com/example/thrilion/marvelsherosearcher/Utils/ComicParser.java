package com.example.thrilion.marvelsherosearcher.Utils;

import android.content.Context;
import android.util.Log;

import com.example.thrilion.marvelsherosearcher.POJO.Comic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 0kitachi on 05/12/2016.
 */

public class ComicParser {

    private static final String TAG = "ComicParser";

    public static ArrayList<Comic> ParseComicJson(Context context, String comicJsonStr) throws JSONException {

        final String json_data="data";
        final String json_results="results";
        final String json_id="id";
        final String json_title="title";
        final String json_description="description";
        final String json_thumbnail="thumbnail";
        final String json_path="path";
        final String json_extension="extension";

        ArrayList<Comic> comicList= new ArrayList<>();

        JSONObject comicJson = new JSONObject(comicJsonStr);
        JSONArray comicArray = comicJson.getJSONObject(json_data).getJSONArray(json_results);

        for (int i = 0; i < comicArray.length(); i++) {
            JSONObject data_comic = comicArray.getJSONObject(i);
            Comic comic = new Comic();
            // Id
            comic.setId(data_comic.getInt(json_id));
            // Title
            comic.setTitle(data_comic.getString(json_title));
            // Description
            comic.setDescription(data_comic.getString(json_description));
            // Imagen
            JSONObject thumb = data_comic.getJSONObject(json_thumbnail);
            comic.setImage(thumb.getString(json_path) + "." + thumb.getString(json_extension));

            comicList.add(comic);
        }

        for (Comic result : comicList) {
            Log.i(TAG," preparing data " + result.getTitle());
        }

        return comicList;
    }
}
