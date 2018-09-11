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
import com.gswaugustom.login.adapter.RecyclerViewAdapterMs;
import com.gswaugustom.login.models.Microsservice;

import java.util.ArrayList;
import java.util.List;

public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab2Fragment";

    View v;

    private RecyclerView mRecyclerView;
    private List<Microsservice> lstMicrosservice;

    public Tab2Fragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab2_fragment, container, false);

        mRecyclerView = v.findViewById(R.id.microsservices_recycler);
        RecyclerViewAdapterMs recyclerViewAdapter = new RecyclerViewAdapterMs(getContext(),lstMicrosservice);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(recyclerViewAdapter);

        return v;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //add itens na lista que criei

        lstMicrosservice = new ArrayList<>();
        lstMicrosservice.add(new Microsservice("Consulta CPF","Adolfo Bastos",R.drawable.starfour,R.drawable.cpfnovo));
        lstMicrosservice.add(new Microsservice("Consulta CNPJ","Paulo Cunha",R.drawable.starthree,R.drawable.cnpjnovo));
        lstMicrosservice.add(new Microsservice("Consulta Score Positivo","Thiago Abravanel",R.drawable.starthree,R.drawable.score));
        lstMicrosservice.add(new Microsservice("Renda Presumida","Carlos Santos",R.drawable.starthree,R.drawable.rendapresumida));
        lstMicrosservice.add(new Microsservice("Enriquecimento de dados","Paula Costa",R.drawable.starfour,R.drawable.enriquecimento));
        lstMicrosservice.add(new Microsservice("Consulta Login","Mariana Braga",R.drawable.starone,R.drawable.login));

    }
}
