package com.example.recyclerview;

import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Updates> news_lst;
    RecyclerView recyclerView;
    Snackbar snackbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        news_lst = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        snackbar = Snackbar.make(recyclerView,"Internet Connection Not Avaolable", Snackbar.LENGTH_INDEFINITE).setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        if(isOnline()){
            snackbar.dismiss();
            new GetDataFromNet().execute("https://script.google.com/macros/s/AKfycbz3te0zi2fokOHsKg5bFA1nuFUMpnOQDL9LGMkjONHXaQBb8f66/exec");
        }else {
            snackbar.show();
        }
    }

    public Boolean isOnline(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            NetworkCapabilities capabilities = manager.getNetworkCapabilities(manager.getActiveNetwork());
            if(capabilities != null){
                return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
            }
        }else {
            NetworkInfo info = manager.getActiveNetworkInfo();
            return info.isConnected();
        }
        return false;
    }

    public class GetDataFromNet extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL dataUrl = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) dataUrl.openConnection();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine())!=null){
                    stringBuilder.append(temp);
                }
                return stringBuilder.toString();


            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONArray jsonArray = new JSONObject(s).getJSONArray("user");
                Updates updates;
                for(int i = 1; i<jsonArray.length(); i++){
                    JSONObject news = jsonArray.getJSONObject(i);
                    updates = new Updates();
                    updates.setHead(news.getString("headlines"));
                    updates.setLink(news.getString("links"));
                    updates.setImg_URL(news.getString("img_url"));
                    updates.setDescription(news.getString("description"));
                    news_lst.add(updates);
                }


                rAdapter adapter = new rAdapter(news_lst);
                recyclerView.setAdapter(adapter);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}