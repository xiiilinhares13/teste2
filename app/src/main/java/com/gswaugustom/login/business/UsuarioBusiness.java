package com.gswaugustom.login.business;

import android.content.Context;

import com.gswaugustom.login.models.Usuario;
import com.gswaugustom.login.repository.PerfilRepository;

public class UsuarioBusiness {

    private PerfilRepository mPerfilRepository;

    public UsuarioBusiness(Context context) {
        this.mPerfilRepository = PerfilRepository.getInstance(context);
    }

    public Boolean insert(Usuario usuario){
        return this.mPerfilRepository.insert(usuario);
    }

//    public List<Usuario> getPerfil(){
//
//       //return this.mPerfilRepository.getUsuarioByQuery("select max("+ DataBaseConstants.OPERADOR.COLUMNS.USERNAME + ") from " + DataBaseConstants.OPERADOR.TABLE_NAME +" order by " + DataBaseConstants.OPERADOR.COLUMNS.ID + " desc");
//       // return this.mPerfilRepository.getUsuarioByQuery("select max(username) from Perfil order by id desc");
//    }

//
//    public List<Usuario> getPerfil(){
//        return this.mPerfilRepository.getUsuarioByQuery("select * from " + DataBaseConstants.OPERADOR.TABLE_NAME );
//    }
}
