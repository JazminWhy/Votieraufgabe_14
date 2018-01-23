package com.example.jasmin.votieraufgabe_Jasmin_V14;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Jasmin on 22/12/2017.
 */

public class MyThread extends AsyncTask<String, Integer, ArrayAdapter<entry>> {


String json;
JSONObject jsonO;
JSONArray jsonA;
int x;
int counter;
    ListView lv;
    ArrayList<entry> input;
    ArrayAdapter<entry> adapter;
    Context act;
    //JSONArray json;
   // JSONObject json_o;


    public MyThread(ListView a, ArrayList<entry> al, ArrayAdapter<entry> s, Context c){
    lv = a;
    input = al;
    act = c;
    }
    @Override
    protected ArrayAdapter<entry> doInBackground(String... urls) {
        adapter = new ArrayAdapter<entry>(act,android.R.layout.simple_list_item_1, input);
        for (String urlString : urls) {
            URL url = null;
            Charset charset = Charset.forName("ISO-8859-1");
            try {
                url = new URL(urlString);
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), charset));
                String line = "";
                json ="";

                while ((line = reader.readLine()) != null) {
                    json += line;
                }
                System.out.println("json=" + json);

                if (json.equals("[]")){
                    return adapter;
                }
                jsonA  = new JSONArray(json);
                json = "";
                x = jsonA.length();
                for(int i = 0; i < x; i++){
                        try {
                            jsonO = jsonA.getJSONObject(i);
                            input.add(new entry(jsonO.getString("userid"), jsonO.getString("text"), jsonO.getString("time")));
                          } catch (JSONException e) {
                            e.printStackTrace();
                        }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (JSONException e) {
            e.printStackTrace();
           }
        }
        return adapter;
    }

    protected void onPostExecute(ArrayAdapter<entry> a) {
        lv.setAdapter(a);
    }
}
