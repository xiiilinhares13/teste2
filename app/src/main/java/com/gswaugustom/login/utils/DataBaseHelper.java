package com.gswaugustom.login.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gswaugustom.login.constants.DataBaseConstants;


public class DataBaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "PerfilUsuariosSPCapp";

    private static final String SQL_CREATE_TABLE_PERFIL =
            "CREATE TABLE " + DataBaseConstants.OPERADOR.TABLE_NAME + " ("
                    + DataBaseConstants.OPERADOR.COLUMNS.ID + " integer primary key autoincrement, "
                    + DataBaseConstants.OPERADOR.COLUMNS.NOMEOPERADOR + " text, "
                    + DataBaseConstants.OPERADOR.COLUMNS.USERNAME + " text, "
                    + DataBaseConstants.OPERADOR.COLUMNS.REFRESHTOKEN + " integer);";



    private static final String DROP_TABLE_PERFIL = "DROP TABLE IF EXISTS " + DataBaseConstants.OPERADOR.TABLE_NAME;


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_PERFIL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //REMOVO E CRIO A TABELA NOVAMENTE P/ ATUALIZAR
        //  db.execSQL ( DROP_TABLE_PERFIL );
        //db.execSQL(SQL_CREATE_TABLE_PERFIL); );

    }
}
