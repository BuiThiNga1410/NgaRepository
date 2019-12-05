package com.example.test1;

import android.app.TaskStackBuilder;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;



class MyDatabase extends SQLiteOpenHelper {

    private static final  String TABLE_NAME="ITEM";
    private static final String DATABASE_NAME = "Item_Manager";
    private static final String ID="id";
    private static final String NAME="name";
    private static final String PRICE="price";
    private Context context;


    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("DBManager","DBManager:");
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE "+TABLE_NAME +" (" +
                ID +" integer primary key, "+
                NAME + " TEXT, "+
                PRICE+" TEXT)";
        db.execSQL(sqlQuery);
        Toast.makeText(context,"Create successfylly",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
        Toast.makeText(context,"Drop successfylly",Toast.LENGTH_SHORT).show();

    }

    public void addItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, item.getName());
        values.put(PRICE, item.getPrice());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<Item> getAllItem(){
        ArrayList<Item> listItem = new ArrayList<Item>();
        String script = "Select * from "+TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(script, null);
        if(cursor.moveToFirst()){
            do {
                Item item = new Item();
                item.setId(cursor.getInt(0));
                item.setName(cursor.getString(1));
                item.setPrice(cursor.getString(2));
                listItem.add(item);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listItem;
    }

    public void deleteItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + " = ?",
                new String[] { String.valueOf(item.getId()) });
        db.close();
    }

    public int update(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME,item.getName());
        values.put(PRICE, item.getPrice());

        return db.update(TABLE_NAME,values,ID +"=?",new String[] { String.valueOf(item.getId())});
    }
}
