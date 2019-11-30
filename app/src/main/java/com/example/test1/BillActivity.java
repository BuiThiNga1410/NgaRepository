package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BillActivity extends AppCompatActivity {
    ListView lvBill;
    public static MyDatabase db;
    private ArrayList<Item>arrItem;
    private CustomAdapter<Item>customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        lvBill =(ListView)findViewById(R.id.lv_bill);
        db=new MyDatabase(this);
        arrItem=new ArrayList<>();
        arrItem=db.getAllItem();
       customAdapter=new  CustomAdapter(this,R.layout.row_listview,arrItem);
       lvBill.setAdapter(customAdapter);




    }
}
