package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BillActivity extends AppCompatActivity {
    ListView lvBill;
    private ArrayList<Item>arrItem;
    private ArrayList<String>lv;
    private ArrayAdapter<String>adapter;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        lvBill =(ListView) findViewById(R.id.lv_bill);

        lv=new ArrayList<>();
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lv);
        lvBill.setAdapter(adapter);

        arrItem=MainActivity.db.getAllItem();
        Intent intent =getIntent();
        if(intent!=null){
            lv.add("So ban:"+intent.getStringExtra("ban")+"\n"+arrItem.get(i).getName()+"\n"+"Tong tien:"+arrItem.get(i).getPrice() );
            i++;
            adapter.notifyDataSetChanged();
        }






    }
}
