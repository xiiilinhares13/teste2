package com.gswaugustom.login.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.gswaugustom.login.R;
import com.gswaugustom.login.actitivitys.Main_Avaliacao_Activity;
import com.gswaugustom.login.actitivitys.micro_services_Activity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class inicioFragment extends Fragment {

    private Context context = getContext();

    public inicioFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        String[] inicioItems = {"Consulta CPF",
                "Consulta CNPJ", "Consulta Score Positivo", "Renda Presumida", "Enrquecimento de dados", "Consulta login"};

        ListView listView = view.findViewById(R.id.inicioList);


        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                inicioItems
        );

        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                    Intent intent = new Intent(getActivity(), Main_Avaliacao_Activity.class);
                    startActivity(intent);

                } else if (position == 1) {

                    Intent intent = new Intent(getActivity(), Main_Avaliacao_Activity.class);
                    startActivity(intent);

                } else if (position == 2) {

                    Intent intent = new Intent(getActivity(), Main_Avaliacao_Activity.class);
                    startActivity(intent);
                } else if (position == 3) {

                    Intent intent = new Intent(getActivity(), Main_Avaliacao_Activity.class);
                    startActivity(intent);
                } else if (position == 4) {

                    Intent intent = new Intent(getActivity(), Main_Avaliacao_Activity.class);
                    startActivity(intent);
                } else if (position == 5) {

                    Intent intent = new Intent(getActivity(), Main_Avaliacao_Activity.class);
                    startActivity(intent);

                }
            }
        });

        return view;
    }

}
