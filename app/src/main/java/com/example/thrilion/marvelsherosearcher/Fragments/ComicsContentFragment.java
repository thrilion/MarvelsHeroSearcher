package com.example.thrilion.marvelsherosearcher.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thrilion.marvelsherosearcher.Adapters.ComicsContentAdapter;
import com.example.thrilion.marvelsherosearcher.POJO.Comic;
import com.example.thrilion.marvelsherosearcher.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComicsContentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_comics_content, container, false);

        // Creamos el Recyclerview y lo asignamos al adapter
        final RecyclerView recyclerView = (RecyclerView) fragmentView.findViewById(R.id.comics_content_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(fragmentView.getContext(), LinearLayoutManager.VERTICAL, false));
        final ComicsContentAdapter adapter = new ComicsContentAdapter(fragmentView.getContext(), new ArrayList<Comic>());
        recyclerView.setAdapter(adapter);

        return fragmentView;
    }
}
