package com.example.session6_task;

import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.session6_task.Movie.MovieClass;
import com.example.session6_task.Movie.Search;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    Button btnSearch;
    String url;
    RecyclerView recyclerView;
    EditText edtSearch;
    Button btnFavorite;
    Boolean isBackPressed=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSearch = findViewById(R.id.btnSearch);
        edtSearch = findViewById(R.id.edtSearch);
        btnFavorite = findViewById(R.id.btnFavorite);
        recyclerView = findViewById(R.id.recyclerView);


        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteHelper helper = new SQLiteHelper(MainActivity.this, "Favorites", null, 1);
                List<SQLiteHelper.Movie> movies = helper.GET_MOVIES();
                FavoriteAdapter adapter = new FavoriteAdapter(movies);
                recyclerView.setAdapter(adapter);


                RecyclerView.LayoutManager layout = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(layout);


            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                url = "http://www.omdbapi.com/?s=" + edtSearch.getText().toString() + "&apikey=40376d37";
                AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
                asyncHttpClient.get(url, new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {

                            Gson gson = new Gson();
                            MovieClass movieClass = gson.fromJson(response.toString(), MovieClass.class);
                            List<Search> searches = movieClass.getSearch();
                            RecyclerViewAdapter adapter = new RecyclerViewAdapter(searches);
                            recyclerView.setAdapter(adapter);
                            RecyclerView.LayoutManager layout = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
                            recyclerView.setLayoutManager(layout);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                });


            }
        });


    }

    @Override
    public void onBackPressed() {
        if(isBackPressed)
        super.onBackPressed();
        else {
            isBackPressed = true;
            Toast.makeText(MainActivity.this, "Press Back Again", Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isBackPressed=false;
                }
            }, 2500);
        }
    }
}
