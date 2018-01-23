package com.example.jasmin.votieraufgabe_Jasmin_V14;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.jasmin.votieraufgabe_10.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class UserInput extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Intent intent;
    //EditText userInput, text;
    String userName;
    Button showAll;
    Boolean empty;
    String url;
    ListView lv;
    ArrayList<entry> input;
    ArrayAdapter<entry> adapter;
    MyThread cloud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.listView);
        lv.setOnItemClickListener(this);
        showAll = findViewById(R.id.showAll);
        userName="";
        url= "";
        url = "http://webtechlecture.appspot.com/chat/posting/list";



    }

    public void click(View aView) {
        input = new ArrayList<entry>();
        adapter = new ArrayAdapter<entry>(this,android.R.layout.simple_list_item_1, input);
        cloud = new MyThread(lv, input, adapter, this);
        cloud.execute(url);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    entry e = adapter.getItem(i);
    System.out.println(e.toString());

        String userID = "";
        try {
        userID = URLEncoder.encode(e.userId, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        url = "http://webtechlecture.appspot.com/chat/posting/list?userid=" + userID;
        System.out.println(url);
        intent = new Intent(this,output.class);
        intent.putExtra("URL", url);
        startActivity(intent);

    }
}
