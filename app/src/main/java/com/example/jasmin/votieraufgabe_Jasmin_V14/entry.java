package com.example.jasmin.votieraufgabe_Jasmin_V14;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jasmin on 22/01/2018.
 */

public class entry {
String text;
String userId;
String time;
//long time_dbl;

public entry(String u, String t, String ti){


    userId = u;
    text = t;
    time = ti.toString();
    System.out.print("TIME " + time);
}

    @Override
    public String toString() {
    String s = "";
    String time_output;

     //long timestamp = time_dbl;
    // First make a System.DateTime equivalent to the UNIX Epoch.
        Date dateTime = new java.util.Date(Long.parseLong(time));
        String dT = new SimpleDateFormat("MM dd, yyyy h:ma").format(dateTime);
        s = "USER: " + userId + " - TIME: " + dT + " - TEXT:" + text + "";
        return s;
    }
}
