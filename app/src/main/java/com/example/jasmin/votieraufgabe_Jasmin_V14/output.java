package com.example.jasmin.votieraufgabe_Jasmin_V14;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jasmin.votieraufgabe_10.R;

import java.util.ArrayList;

public class output extends AppCompatActivity {

    String url;
    Intent intent, intent_return;
    ListView lv;
    ArrayList<entry> input;
    ArrayAdapter<entry> adapter;
    MyThread cloud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        intent = getIntent();
        intent_return = new Intent(this, UserInput.class);
        url = intent.getStringExtra("URL");
        lv =   lv = findViewById(R.id.listView2);
        input = new ArrayList<entry>();
        adapter = new ArrayAdapter<entry>(this,android.R.layout.simple_list_item_1, input);
        cloud = new MyThread(lv, input, adapter, this);
        cloud.execute(url);

    }

    public void clickReturn(View aView) {
        startActivity(intent_return);
    }



}