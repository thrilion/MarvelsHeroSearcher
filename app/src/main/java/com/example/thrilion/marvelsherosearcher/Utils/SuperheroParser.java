package com.example.thrilion.marvelsherosearcher.Utils;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.example.thrilion.marvelsherosearcher.POJO.Superhero;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 0kitachi on 03/12/2016.
 */

public class SuperheroParser {

    private static final String TAG = "SuperheroParser";

    public static ArrayList<Superhero> ParseSuperheroJson(Context context, String heroJsonStr) throws JSONException {

        final String json_data="data";
        final String json_results="results";
        final String json_id="id";
        final String json_name="name";
        final String json_description="description";
        final String json_thumbnail="thumbnail";
        final String json_path="path";
        final String json_urls="urls";
        final String json_type="type";
        final String json_type_detail="detail";
        final String json_type_wiki="wiki";
        final String json_type_comiclink="comiclink";
        final String json_url="url";


        ArrayList<Superhero> heroList= new ArrayList<>();

        JSONObject heroJson = new JSONObject(heroJsonStr);
        JSONArray heroArray = heroJson.getJSONObject(json_data).getJSONArray(json_results);

        for (int i = 0; i < heroArray.length(); i++) {
            JSONObject data_hero = heroArray.getJSONObject(i);
            Superhero superhero = new Superhero();
            // Id
            superhero.setId(data_hero.getInt(json_id));
            Log.i(TAG,"ID: " + superhero.getId());
            // Nombre
            superhero.setName(data_hero.getString(json_name));
            // Descripcion
            if("".equals(data_hero.getString(json_description))
                    || " ".equals(data_hero.getString(json_description))){
                superhero.setDescrption("(no description)");
            }else {
                superhero.setDescrption(data_hero.getString(json_description));
            }
            // Imagen
            superhero.setImage(data_hero.getJSONObject(json_thumbnail).getString(json_path));
            // URLs
            JSONArray urlArray = data_hero.getJSONArray(json_urls);
            for (int j = 0; j < urlArray.length(); j++) {
                JSONObject url_list = urlArray.getJSONObject(j);
                switch (url_list.getString(json_type)){
                    case json_type_detail:
                        superhero.setDetailLink(url_list.getString(json_url));
                        Log.i(TAG,"Detail: " + superhero.getDetailLink());
                        break;
                    case json_type_wiki:
                        superhero.setWikiLink(url_list.getString(json_url));
                        Log.i(TAG,"Wiki: " + superhero.getWikiLink());
                        break;
                    case json_type_comiclink:
                        superhero.setComicLink(url_list.getString(json_url));
                        Log.i(TAG,"Comic: " + superhero.getComicLink());
                        break;
                }
            }
            heroList.add(superhero);
        }
        for (Superhero result : heroList) {
            Log.i(TAG," preparing data " + result.getName());
        }
        return heroList;
    }
}
