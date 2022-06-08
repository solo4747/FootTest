package com.example.myapplication;

import static java.lang.Thread.sleep;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

import java.nio.charset.StandardCharsets;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class MainActivity extends AppCompatActivity {

    static int x = 1;
    static int y = 0;
    boolean boucle = true;
    JsonObjectRequest request;
    ListView playersListView = null;
    // Request a string response from the provided URL.
    public static String url = BuildConfig.SERVER_URL;
    private EditText result;
    private static boolean fetch = false;



    private ArrayList<Player> joueurs;
    //private ArrayList<String> playersName;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




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
                                                         //    playersListView.setAdapter(playersAdapter);




        playersListView = findViewById(R.id.list1);
        result = findViewById(R.id.URL);

            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);





        do {


            request = new JsonObjectRequest(Request.Method.GET, MainActivity.url, null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

//


                            String name = "";
                            String speed;
                            try {
                                JSONArray players = response.getJSONArray("results");


                                for (int i = 0; i < players.length(); i++) {
                                    name = players.getJSONObject(i).getString("name");
                                    speed = players.getJSONObject(i).getString("speed_score");

                                    //playersName.add(String.valueOf(y) + ". " + name);
                                    joueurs.add(new Player(name, speed, String.valueOf(y)));
                                    y++;

                                }
//                                        ArrayAdapter<String> playersAdapter = new ArrayAdapter<>(
//                                                MainActivity.this,
//                                                android.R.layout.simple_list_item_1,
//                                                playersName
//                                        );
//                                        playersListView.setAdapter(playersAdapter);
                                PlayerAdapter adapter = new PlayerAdapter(MainActivity.this,
                                        R.layout.row,
                                        joueurs);
                                playersListView = findViewById(R.id.list1);
                                playersListView.setAdapter(adapter);
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
            //url = "https://okochatest.footbar.com/profile/list/?page=1" + String.valueOf(x);

            MainActivity.url = cut(MainActivity.url) + String.valueOf(x);
            queue.add(request);

        } while (x < 7);

    }

public void prompt(){
    // get prompts.xml view
    LayoutInflater li = LayoutInflater.from(MainActivity.this);
    View promptsView = li.inflate(R.layout.url, null);

    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
            MainActivity.this);

    // set prompts.xml to alertdialog builder
    alertDialogBuilder.setView(promptsView);

    final TextView userInput = (TextView) promptsView
            .findViewById(R.id.URL);

    // set dialog message
    alertDialogBuilder
            .setCancelable(false)
            .setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // get user input and set it to result
                            // edit text
                            //result.setText(userInput.getText());
                            //url = userInput.getText().toString();



                        }

                    })
            .setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    });

    // create alert dialog
    AlertDialog alertDialog = alertDialogBuilder.create();

    // show it
    alertDialog.show();
}



public String cut(String s)
    {
            StringBuffer sb= new StringBuffer(s);

            sb.deleteCharAt(sb.length()-1);

            return sb.toString();
    }



    public String encrypt() throws Exception {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        byte[] keyBytes   = new byte[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        String algorithm  = "RawBytes";
        SecretKeySpec key = new SecretKeySpec(keyBytes, algorithm);

        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] plainText  = "https://okochatest.footbar.com/profile/list/?page=1".getBytes("UTF-8");
        String cipherText = cipher.doFinal(plainText).toString();

        return cipherText;
    }






}





