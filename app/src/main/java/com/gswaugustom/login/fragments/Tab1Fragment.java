package com.gswaugustom.login.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gswaugustom.login.R;
import com.gswaugustom.login.adapter.RecyclerViewAdapter;
import com.gswaugustom.login.models.Consumidor;

import java.util.ArrayList;
import java.util.List;

public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";

    View v;

    private RecyclerView mRecyclerView;
    private List<Consumidor> lstConsumidor;

    public Tab1Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab1_fragment, container, false);

        mRecyclerView = v.findViewById(R.id.consumidor_recycler);
        RecyclerViewAdapter recyclerViewAdapter =  new RecyclerViewAdapter(getContext(),lstConsumidor);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(recyclerViewAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //add nome, img, cargo, e img ranking no meu array criado.

        lstConsumidor = new ArrayList<>();
        lstConsumidor.add(new Consumidor("Ronaldo Andrade", "Analista de Sistemas", R.drawable.homem, R.drawable.starfour));
        lstConsumidor.add(new Consumidor("Agnaldo Silva", "Team Leader", R.drawable.homemdois, R.drawable.starfour));
        lstConsumidor.add(new Consumidor("Amanda Valentina", "Programadora", R.drawable.mulher, R.drawable.starthree));
        lstConsumidor.add(new Consumidor("Sandra Cunha", "Programadora", R.drawable.mulherum, R.drawable.startwo));
        lstConsumidor.add(new Consumidor("Maria Clara Andrade", "Analista de sistema", R.drawable.mulher, R.drawable.starone));
        lstConsumidor.add(new Consumidor("Ronaldo Andrade", "Analista de Sistemas", R.drawable.homem, R.drawable.starfour));
        lstConsumidor.add(new Consumidor("Agnaldo Silva", "Team Leader", R.drawable.homemdois, R.drawable.starfour));
        lstConsumidor.add(new Consumidor("Carlos da Costa", "Programador", R.drawable.homem, R.drawable.starthree));
        lstConsumidor.add(new Consumidor("Carla Riso", "Programadora", R.drawable.mulherum, R.drawable.startwo));
        lstConsumidor.add(new Consumidor("Maria Clara Andrade", "Analista de sistema", R.drawable.mulher, R.drawable.starone));

    }
}
