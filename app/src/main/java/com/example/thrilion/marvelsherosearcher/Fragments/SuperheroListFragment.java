package com.example.thrilion.marvelsherosearcher.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thrilion.marvelsherosearcher.Adapters.SuperheroListAdapter;
import com.example.thrilion.marvelsherosearcher.POJO.Superhero;
import com.example.thrilion.marvelsherosearcher.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuperheroListFragment extends Fragment {

    public SuperheroListFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_superhero_list, container, false);

        final RecyclerView recycler = (RecyclerView) fragmentView.findViewById(R.id.superhero_list_recycler);

        String[] listValues = {"Spider-Man", "Captain Marvel", "Hulk", "Thor", "Iron Man",
                "Black Widow", "Daredevil", "Captain America", "Wolvreine", "Luke Cage", "Loki", "Ultron",
                "Red Skull", "Mistique", "Thanos", "Magneto", "Dr. Doom", "Ant-Man", "Black Panther", "Doctor Strange",
                "Hawkeye", "Elektra", "Gambit"};

        List<Superhero> arrayListValues = new ArrayList<>();
        for (String listValue : listValues) {
            arrayListValues.add(new Superhero("@drawable/spider", listValue, "Descripción"));
        }

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(fragmentView.getContext(), LinearLayoutManager.VERTICAL, false));
        recycler.setAdapter(new SuperheroListAdapter(fragmentView.getContext(), arrayListValues));

        return fragmentView;
    }
}
