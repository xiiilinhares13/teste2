package com.gswaugustom.login.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gswaugustom.login.R;
import com.gswaugustom.login.actitivitys.Main_Avaliacao_Activity;
import com.gswaugustom.login.actitivitys.Main_MeuPrimeiro_Servico_Activity;
import com.gswaugustom.login.actitivitys.Main_Play_Activity;

/**
 * A simple {@link Fragment} subclass.
 */
public class conteudoFragment extends Fragment {


    public conteudoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_conteudo, container, false);

        String[] inicioItems = {"Vídeo Arquitetura",
                "Documentação", "SWAGGER", "WIKI DO SPC", "1º MICROSSERVIÇO"};

        ListView listView = view.findViewById(R.id.conteudoList);

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

                    Intent intent = new Intent(getActivity(), Main_Play_Activity.class);
                    startActivity(intent);

                } else if (position == 1) {



                } else if (position == 2) {


                } else if (position == 3) {


                } else if (position == 4) {

                    Intent intent = new Intent(getActivity(), Main_MeuPrimeiro_Servico_Activity.class);
                    startActivity(intent);

                }
            }
        });

        return view;
    }

}
