package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    private ArrayList<Item>itemArrayList;
    private CustomAdapter<Item>customAdapter;
    private ListView lvItem;
    private  String name="";
    private  int price=0;
    Button btnAdd;
    EditText edtBan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        lvItem=(ListView)findViewById(R.id.lv_menu);
        btnAdd=(Button)findViewById(R.id.btn_add);
        edtBan=(EditText)findViewById(R.id.edt_id);
        itemArrayList=new ArrayList<>();
        itemArrayList.add(new Item(Item.ID,"Chân gà xả tắc","20000"));
        itemArrayList.add(new Item(Item.ID,"Nem chua rán","25000"));
        itemArrayList.add(new Item(Item.ID,"Xoài lắc","10000"));
        customAdapter=new CustomAdapter<>(this,R.layout.row_listview,itemArrayList);
        lvItem.setAdapter(customAdapter);


        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Item item = (Item) adapterView.getItemAtPosition(position);
                name=""+name+item.getName()+"\n";
                price=price+Integer.parseInt(item.getPrice());

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(MenuActivity.this,BillActivity.class);
                intent.putExtra("ban",edtBan.getText().toString());
                Item item=new Item();
                item.setName(name);
                item.setPrice(String.valueOf(price));
                MainActivity.db.addItem(item);
                startActivity(intent);
            }
        });

    }
    //customAdapter.notifyDataSetChanged();
}

