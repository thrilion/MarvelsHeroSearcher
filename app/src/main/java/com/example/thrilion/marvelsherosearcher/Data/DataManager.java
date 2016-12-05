package com.example.thrilion.marvelsherosearcher.Data;

import com.example.thrilion.marvelsherosearcher.POJO.Superhero;

import java.util.ArrayList;

/**
 * Created by 0kitachi on 04/12/2016.
 */

public class DataManager {
    private ArrayList<Superhero> heroList;

    public DataManager(){
        this.heroList = new ArrayList<>();
    }

    public ArrayList<Superhero> getHeroList() {
        return heroList;
    }
    public void setHeroList(ArrayList<Superhero> heroList) {
        this.heroList = heroList;
    }

    public void clearHeroList(){
        this.heroList.clear();
    }
}
