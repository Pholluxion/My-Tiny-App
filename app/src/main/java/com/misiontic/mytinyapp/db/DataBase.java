package com.misiontic.mytinyapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    private Context context;

    private static final  String DB = "todos.db";
    private static final  int DB_VERSION = 1;

    private static final  String TABLA  = "todos";
    private static final  String COL_ID = "id";
    private static final  String COL_TITULO = "titulo";
    private static final  String COL_DESCRIPCION = "descripcion";
    private static final  String COL_ESTADO = "estado";


    public DataBase(@Nullable Context context) {
        super(context, DB, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql =
                "CREATE TABLE " + TABLA +
                        " (" +
                        COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COL_TITULO + " TEXT, "+
                        COL_DESCRIPCION + " TEXT, "+
                        COL_ESTADO + " INTEGER" +
                        ");";

        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLA);
        onCreate(sqLiteDatabase);
    }

    public void addToDo(String titulo, String desc,int estado){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_TITULO,titulo);
        cv.put(COL_DESCRIPCION,desc);
        cv.put(COL_ESTADO,estado);

        long res = db.insert(TABLA,null,cv);

        if (res == -1){
            Toast.makeText(context,"Error al registrar TODO",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Se registró el TODO",Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readProducts(){

        String sql = "SELECT * FROM "+TABLA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;

        if (db!= null){
            cursor  = db.rawQuery(sql,null);
        }
        return  cursor;
    }


    void  deleteProduct(String id){
        SQLiteDatabase db  =  this.getWritableDatabase();

        long rst = db.delete(TABLA,"codigo=?",new String[]{id});
        if (rst == -1){
            Toast.makeText(context, "No se ha podido eliminar el producto", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Producto eiminado ", Toast.LENGTH_SHORT).show();
        }
    }




}
