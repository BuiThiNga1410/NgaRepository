package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    private ArrayList<Item>itemArrayList;
    private CustomAdapter<Item>customAdapter;
    private ListView lvItem;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        lvItem=(ListView)findViewById(R.id.lv_menu);
        save=(Button)findViewById(R.id.bt_save);
        itemArrayList=new ArrayList<>();
        itemArrayList.add(new Item("Chân gà xả tắc","20000"));
        itemArrayList.add(new Item("Nem chua rán","25000"));
        itemArrayList.add(new Item("Xoài lắc","10000"));
        customAdapter=new CustomAdapter<>(this,R.layout.row_listview,itemArrayList);
        lvItem.setAdapter(customAdapter);
        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Item item = (Item) adapterView.getItemAtPosition(position);
                BillActivity.db.addItem(item);

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MenuActivity.this,BillActivity.class);
                startActivity(intent);
            }
        });
        //customAdapter.notifyDataSetChanged();
    }
}

