package com.gswaugustom.login.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gswaugustom.login.constants.DataBaseConstants;
import com.gswaugustom.login.models.Usuario;
import com.gswaugustom.login.utils.DataBaseHelper;

public class PerfilRepository {
    private static PerfilRepository INSTANCE;
    private DataBaseHelper mDataBaseHelper;

    public PerfilRepository(Context context) {
        this.mDataBaseHelper = new DataBaseHelper(context);
    }

    public static synchronized PerfilRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new PerfilRepository(context);
        }
        return INSTANCE;
    }

    public Boolean insert(Usuario usuario) {
        try {
            SQLiteDatabase sqLiteDatabase = this.mDataBaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseConstants.OPERADOR.COLUMNS.USERNAME, usuario.getUsuario());
            contentValues.put(DataBaseConstants.OPERADOR.COLUMNS.NOMEOPERADOR, usuario.getNome());
            contentValues.put(DataBaseConstants.OPERADOR.COLUMNS.REFRESHTOKEN, usuario.getReverse_token());
            sqLiteDatabase.insert(DataBaseConstants.OPERADOR.TABLE_NAME, null, contentValues);

            return true;
        } catch (Exception e) {
            return false;
        }
    }



    public String getUsuarioByQuery() {

        SQLiteDatabase sqLiteDatabase = this.mDataBaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + DataBaseConstants.OPERADOR.TABLE_NAME + " order by " + DataBaseConstants.OPERADOR.COLUMNS.ID + " desc LIMIT 0, 1", null);
        //Cursor cursor = sqLiteDatabase.rawQuery("select max("+ DataBaseConstants.OPERADOR.COLUMNS.USERNAME + ") from " + DataBaseConstants.OPERADOR.TABLE_NAME +" order by " + DataBaseConstants.OPERADOR.COLUMNS.ID + " desc",null);
        String username = "";
        //List<Usuario> list = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            //Usuario usuario = new Usuario();
            //usuario.setId(cursor.getInt(cursor.getColumnIndex(DataBaseConstants.OPERADOR.COLUMNS.ID)));
            //usuario.setNome(cursor.getString(cursor.getColumnIndex(DataBaseConstants.OPERADOR.COLUMNS.NOMEOPERADOR)));
            username = cursor.getString(cursor.getColumnIndex(DataBaseConstants.OPERADOR.COLUMNS.USERNAME));
            //usuario.setReverse_token(cursor.getInt(cursor.getColumnIndex(DataBaseConstants.OPERADOR.COLUMNS.REFRESHTOKEN)));
            //list.add(usuario);

        }
        if (cursor != null) {
            cursor.close();
        }
        return username;
    }
}


