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
        final String json_thumbnail="thumbnail";
        final String json_path="path";
        final String json_name="name";
        final String json_description="description";

        ArrayList<Superhero> heroList= new ArrayList<>();

        JSONObject heroJson = new JSONObject(heroJsonStr);
        JSONArray heroArray = heroJson.getJSONObject(json_data).getJSONArray(json_results);

        for (int i = 0; i < heroArray.length(); i++) {
            JSONObject data_hero = heroArray.getJSONObject(i);
            Superhero superhero = new Superhero();
            superhero.setImage(data_hero.getJSONObject(json_thumbnail).getString(json_path));
            superhero.setName(data_hero.getString(json_name));
            if("".equals(data_hero.getString(json_description))
                    || " ".equals(data_hero.getString(json_description))){
                superhero.setDescrption("(no description)");
            }else {
                superhero.setDescrption(data_hero.getString(json_description));
            }
            heroList.add(superhero);
        }
        for (Superhero result : heroList) {
            Log.i(SuperheroParser.TAG," preparing data " + result.getName());
        }
        return heroList;
    }
}
