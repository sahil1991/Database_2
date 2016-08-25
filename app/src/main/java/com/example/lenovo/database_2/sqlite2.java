package com.example.lenovo.database_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 19-08-2016.
 */
public class sqlite2 extends SQLiteOpenHelper {
    public static final String Database_name="Details";
    public static final String Table_name="Employee";
    public static final String col_1="ID";
    public static final String col_2="Name";
    public static final String col_3="surname";



    public sqlite2(Context context) {
        super(context, Database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ Table_name+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,surname TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ Table_name);
        onCreate(sqLiteDatabase);

    }
    public boolean insertData(String name,String surname){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentvalues=new ContentValues();
        contentvalues.put(col_2,name);
        contentvalues.put(col_3,surname);
        long result=  sqLiteDatabase.insert(Table_name,null,contentvalues);
        if(result==-1)
            return false;
        else
            return true;

    }
    public Cursor getAlldata(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from Employee",null);
        return res;

    }

}
