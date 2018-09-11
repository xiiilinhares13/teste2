package com.gswaugustom.login.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.gswaugustom.login.R;
import com.gswaugustom.login.actitivitys.Main_Consulta_Cnpj_Activity;
import com.gswaugustom.login.actitivitys.Main_MicrosServiceDetalhe_Activity;

/**
 * A simple {@link Fragment} subclass.
 */
public class microsservicosFragment extends Fragment implements View.OnClickListener {


    private ViewHolder mViewHolder = new ViewHolder();

    public microsservicosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_microsservicos, container, false);

        this.mViewHolder.mSearchView = view.findViewById(R.id.search);
        this.mViewHolder.mBtn_cnpj = view.findViewById(R.id.btn_consulta_cnpj);
        this.mViewHolder.mBtn_cpf = view.findViewById(R.id.btn_cpf);
        this.mViewHolder.mBtn_scorenegativo = view.findViewById(R.id.btn_scorenegativo);
        this.mViewHolder.mBtn_geolocalizacao = view.findViewById(R.id.btn_geolocalizacao);
        this.mViewHolder.mBtn_plotagem = view.findViewById(R.id.btn_plotagem);
        this.mViewHolder.mBtn_biometria = view.findViewById(R.id.btn_biometria);
        this.mViewHolder.mBtn_segmentacao = view.findViewById(R.id.btn_segmentacao);

        this.setListenres();
        // Inflate the layout for this fragment
        return view;
    }


    private void setListenres() {
        this.mViewHolder.mSearchView.setOnSearchClickListener(this);
        this.mViewHolder.mBtn_cnpj.setOnClickListener(this);
        this.mViewHolder.mBtn_cpf.setOnClickListener(this);
        this.mViewHolder.mBtn_scorenegativo.setOnClickListener(this);
        this.mViewHolder.mBtn_geolocalizacao.setOnClickListener(this);
        this.mViewHolder.mBtn_plotagem.setOnClickListener(this);
        this.mViewHolder.mBtn_biometria.setOnClickListener(this);
        this.mViewHolder.mBtn_segmentacao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.search) {

        } else if (id == R.id.btn_cpf) {
            Intent intent = new Intent(getActivity(), Main_MicrosServiceDetalhe_Activity.class);
            startActivity(intent);
        } else if (id == R.id.btn_scorenegativo) {

        } else if (id == R.id.btn_geolocalizacao) {

        } else if (id == R.id.btn_plotagem) {

        } else if (id == R.id.btn_biometria) {

        } else if (id == R.id.btn_segmentacao) {

        } else if (id == R.id.btn_consulta_cnpj) {
            Intent intent = new Intent(getActivity(), Main_Consulta_Cnpj_Activity.class);
            startActivity(intent);

        }


    }

    public static class ViewHolder {

        private SearchView mSearchView;
        private ImageButton mBtn_cnpj;
        private ImageButton mBtn_cpf;
        private ImageButton mBtn_scorenegativo;
        private ImageButton mBtn_geolocalizacao;
        private ImageButton mBtn_plotagem;
        private ImageButton mBtn_biometria;
        private ImageButton mBtn_segmentacao;
    }

}
