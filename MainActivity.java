package com.example.cc.railwaysapi;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    Context context;
    List<Modelclass> items=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         context=MainActivity.this;
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));



        new AsyncTaskDemo().execute();

    }
    public  class AsyncTaskDemo extends AsyncTask<String,String,String> {


        JSONObject jsonObject;
        String json = " ";
        HttpURLConnection httpURLConnection;
        URL url;
        StringBuilder stringBuilder = new StringBuilder();


        @Override
        protected String doInBackground(String... strings) {
            try {
                url = new URL("https://api.railwayapi.com/v2/route/train/12006/apikey/d7361zwvev/ ");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String data;
                while ((data = bufferedReader.readLine()) != null) {
                    stringBuilder.append(data);
                }
                Log.d("data", stringBuilder.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            json = stringBuilder.toString();
            Log.d("json", json);

            return json;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                jsonObject=new JSONObject(json);
                JSONArray jsonArray=jsonObject.getJSONArray("route");
                Log.d("size",String.valueOf(jsonArray.length()));   // Array ka size Ase nikalte he
                for (int i=0;i<=jsonArray.length();i++){   // Array ke ander array hota he to dubaara for loop lagte jaate he

                    Modelclass modelclass1=new Modelclass();
                    Log.d("no",jsonArray.getJSONObject(i).getString("no"));
                    Log.d("halt",jsonArray.getJSONObject(i).getString("halt"));
                    Log.d("Schdul", jsonArray.getJSONObject(i).getString("schdep"));





                    modelclass1.setTv1(jsonArray.getJSONObject(i).getString("no"));


                    modelclass1.setTv2(jsonArray.getJSONObject(i).getString("day"));

                    JSONObject jsonObject=jsonArray.getJSONObject(i).getJSONObject("station");  //Ager json object ka naam tha to JSONObject create kiya

     Log.d("Code",jsonObject.getString("code"));

                    modelclass1.setTv3(jsonObject.getString("name"));


                   items.add(modelclass1);



                }

            } catch (JSONException e) {
                e.printStackTrace();
            }



            myAdapter=new MyAdapter(context,items);
            recyclerView.setAdapter(myAdapter);

        }

    }

}
//Ager json object ka naam hota he to
