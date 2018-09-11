package com.gswaugustom.login.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.gswaugustom.login.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class configFragment extends Fragment {


    public configFragment() {
        // Required empty public constructor
    }


    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_config, container, false);
        this.mViewHolder.switchStatus = view.findViewById(R.id.switchStatus);
        this.mViewHolder.mySwitch = view.findViewById(R.id.mySwitch);
        this.mViewHolder.mBtnAlterarSenha = view.findViewById(R.id.btn_alterarSenha);
        this.mViewHolder.mBtnMeusDados = view.findViewById(R.id.btn_meusDados);
        this.mViewHolder.mBtnSobreApp = view.findViewById(R.id.btn_sobreApp);

        this.setOn();


        return view;

    }


    private void setOn() {

        this.mViewHolder.mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked) {
                        mViewHolder.switchStatus.setText("Notificação Ligada");

                    } else {
                        mViewHolder.switchStatus.setText("Notificação Desligada");
                    }
            }
        });

//        //check the current state before we display the screen
//        if (mViewHolder.mySwitch.isChecked()) {
//            mViewHolder.switchStatus.setText("Notificação Ligada");
//            mViewHolder.mySwitch.setChecked(true);
//        } else {
//            mViewHolder.switchStatus.setText("Notificação Desligada");
//            mViewHolder.mySwitch.setChecked(false);
//        }

    }


    public static class ViewHolder {

        private TextView switchStatus;
        private Switch mySwitch;
        private ImageButton mBtnAlterarSenha;
        private ImageButton mBtnMeusDados;
        private ImageButton mBtnSobreApp;
    }
}
