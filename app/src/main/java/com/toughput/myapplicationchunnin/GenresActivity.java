package com.toughput.myapplicationchunnin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GenresActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PopularAdapter popularAdapter;
    List<Movie> movieArrayList;
    ProgressBar pgBar;
    String url = "https://api.themoviedb.org/3/discover/movie?api_key=ac80477002d21dc4400901f64c0506a7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);

        recyclerView = findViewById(R.id.rv_genres);
        movieArrayList = new ArrayList<>();
        popularAdapter = new PopularAdapter(movieArrayList, getApplicationContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));

        recyclerView.setAdapter(popularAdapter);

        pgBar = findViewById(R.id.pg_genre_movie);
        pgBar.setVisibility(View.VISIBLE);


        if (savedInstanceState==null){
            getPopMovie(url);
        }
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(GenresActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MOVIE, movieArrayList.get(position));
                startActivity(intent);
            }
        });
    }

    private void getPopMovie(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Movie movie = new Movie();
                        movie.setTitle(jsonObject.getString("title"));
                        movie.setDate(jsonObject.getString("release_date"));
                        movie.setPosterpath(jsonObject.getString("poster_path"));
                        movie.setDescription(jsonObject.getString("overview"));
                        movie.setPopularity(String.valueOf(jsonObject.getDouble("popularity")));
                        movieArrayList.add(movie);
                    }
                    popularAdapter.notifyDataSetChanged();
                    pgBar.setVisibility(View.INVISIBLE);
                    Log.d("RESPONSE : ", "Success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error Response : ", String.valueOf(error));
            }
        }
        );
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void setMode(int itemId) {
        switch (itemId){
            case R.id.adventure:
                movieArrayList.clear();
                getPopMovie("https://api.themoviedb.org/3/discover/movie?api_key=ac80477002d21dc4400901f64c0506a7&with_genres=14");
                break;
            case R.id.horror:
                movieArrayList.clear();
                getPopMovie(
                        "https://api.themoviedb.org/3/discover/movie?api_key=ac80477002d21dc4400901f64c0506a7&with_genres=27"
                );
                break;
            case R.id.commedy:
                movieArrayList.clear();
                getPopMovie("https://api.themoviedb.org/3/discover/movie?api_key=ac80477002d21dc4400901f64c0506a7&with_genres=35");
                break;
            case R.id.romance:
                movieArrayList.clear();
                getPopMovie("https://api.themoviedb.org/3/discover/movie?api_key=ac80477002d21dc4400901f64c0506a7&with_genres=10749");
                break;

        }
    }
}
