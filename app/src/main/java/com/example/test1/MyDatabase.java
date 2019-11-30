package com.example.test1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;



class MyDatabase extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "Item_Manager";


    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "CREATE TABLE ITEM(id INTEGER primary key,"
                + "Name TEXT,"
                + "Price TEXT)";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addContact(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", item.getName());
        values.put("Price", item.getPrice());
        db.insert("ITEM", null, values);
        db.close();
    }

    public ArrayList<Item> getAllItem(){
        ArrayList<Item> listItem = new ArrayList<>();
        String script = "Select * from ITEM";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(script, null);
        while (cursor.moveToNext()){
            Item item = new Item();
            item.setId(cursor.getInt(0));
            item.setName(cursor.getString(1));
            item.setPrice(cursor.getString(2));
            listItem.add(item);
        }
        return listItem;
    }

    public void deleteContact(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("ITEM", "id=?", new String[]{String.valueOf(item.getId())});
    }

    public void update(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Name",item.getName());
        values.put("Price", item.getPrice());

        db.update("ITEM",values,"id=?",new String[] { String.valueOf(item.getId())});
    }
}
