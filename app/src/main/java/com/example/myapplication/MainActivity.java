package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    JSONObject object = null;
    // String url = "https://okochatest.footbar.com/profile/list/?page=1";
    static int x = 1;
    static int y = 0;
    boolean boucle = true;
    JsonObjectRequest request;
    String next = "";



    private ArrayList<Player> joueurs;
    private ArrayList<String> playersName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        ListView playersListView = null;
//        ArrayList<String> playersName = new ArrayList<String>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Cette fonction relie mon activity.xml à mon activity.java file.


        playersName = new ArrayList<>();
        joueurs = new ArrayList<>();


        //                  1. Create Adapter
                                                        //      ArrayAdapter<String> playersAdapter = new ArrayAdapter<>(
                                                        //                             MainActivity.this,
                                                        //                             android.R.layout.simple_list_item_1,
                                                        //                            playersName
                                                        //                                                );
        //                  2. Link ListView with XML
                                                         //   playersListView = findViewById(R.id.list1);

        //                  3. Link Adapter & ListView
                                                         //    finalPlayersListView.setAdapter(playersAdapter);




        playersListView = findViewById(R.id.list1);



            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);


// Request a string response from the provided URL.
        String url = "https://okochatest.footbar.com/profile/list/?page=1";

            do {

                ListView finalPlayersListView = playersListView;


                request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

//


                                    String name = "";

                                    try {
                                        JSONArray players = response.getJSONArray("results");
                                        name = players.getJSONObject(0).getString("name");

                                        for (int i = 0; i < players.length(); i++) {
                                            name = players.getJSONObject(i).getString("name");
//
                                            playersName.add(String.valueOf(y) + ". " + name);
                                            y++;

                                        }
                                        ArrayAdapter<String> playersAdapter = new ArrayAdapter<>(
                                                MainActivity.this,
                                                android.R.layout.simple_list_item_1,
                                                playersName
                                        );
                                        finalPlayersListView.setAdapter(playersAdapter);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                            }

                        }

                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "error = " + error, Toast.LENGTH_LONG).show();
                    }
                });

                x += 1;
                url = "https://okochatest.footbar.com/profile/list/?page=" + String.valueOf(x);
                queue.add(request);

            } while(x<7);

    }

//

}



