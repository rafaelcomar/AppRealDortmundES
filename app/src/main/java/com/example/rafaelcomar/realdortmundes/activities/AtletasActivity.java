package com.example.rafaelcomar.realdortmundes.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.rafaelcomar.realdortmundes.R;
import com.example.rafaelcomar.realdortmundes.adapter.MyAdapter;
import com.example.rafaelcomar.realdortmundes.model.Atleta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AtletasActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atletas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        toolbar.setSubtitle("Atletas");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_arrow);



        toolbar.setNavigationOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(getApplicationContext() , "clicking the toolbar!", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        );

        // Set an OnMenuItemClickListener to handle menu item clicks
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerListaAtletas);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        LoaderAtletasTask ld = new LoaderAtletasTask();
        ld.execute();
//        mRecyclerView.setAdapter(mAdapter);
    }

    class LoaderAtletasTask extends AsyncTask<Void, Integer, List<Atleta>> {

        int count = 0;

        @Override
        protected List<Atleta> doInBackground(Void... voids) {
//            String url = Constants.URI_CATEGORY_LOCATION + category.getId();
            String url = "http://www.gestaodesaude.esy.es/campeonato/api/get_category_posts/?slug=atletas";


            OkHttpClient client = new OkHttpClient();
            List<Atleta> atletas = null;

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String body = response.body().string();
                JSONObject jsonBody = new JSONObject(body);

                count = jsonBody.getInt("count");
                JSONArray jsonAtletas = jsonBody.getJSONArray("posts");
                atletas = new ArrayList<>();

                for (int i = 0; i < jsonAtletas.length(); i++) {
                    JSONObject jsonAtleta = jsonAtletas.getJSONObject(i);

                    Atleta at = new Atleta();
                    at.setNome(jsonAtleta.getString("title"));
                    at.setThumbnail(jsonAtleta.getString("thumbnail"));
//                    Location location = new Location();
//                    location.setId(jsonLocation.getInt("id"));
//                    location.setTitle(jsonLocation.getString("title"));
//                    location.setDescription(jsonLocation.getString("content"));
//                    location.setThumbnail(jsonLocation.getString("thumbnail"));
//
//                    JSONObject jsonCustomFields = jsonLocation.getJSONObject("custom_fields");
//
//                    JSONArray jsonLatArray = jsonCustomFields.getJSONArray("latitude");
//                    location.setLatitude(jsonLatArray.get(0).toString());
//
//                    JSONArray jsonLongArray = jsonCustomFields.getJSONArray("longitude");
//                    location.setLongitude(jsonLongArray.get(0).toString());
//
//                    JSONArray jsonEnderecoArray = jsonCustomFields.getJSONArray("endereco");
//                    location.setEndereco(jsonEnderecoArray.get(0).toString());
//                    location.setCategory(category.getId());




                    atletas.add(at);
                    publishProgress(atletas.size());

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return atletas;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
//            if (progressBarLocations.getMax() != count) {
//                progressBarLocations.setMax(count);
//            }
//
//            progressBarLocations.setProgress(values[0]);
        }


        @Override
        protected void onPostExecute(List<Atleta> atletas) {
            super.onPostExecute(atletas);
//            locationAdapter.update(locations);
            mAdapter.update(atletas);

        }
    }
}
