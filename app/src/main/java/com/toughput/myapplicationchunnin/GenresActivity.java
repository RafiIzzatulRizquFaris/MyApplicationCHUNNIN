package com.toughput.myapplicationchunnin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class GenresActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PopularAdapter popularAdapter;
    ArrayList<Movie> movieArrayList;
    ProgressBar pgBar;
    String url = "https://api.themoviedb.org/3/discover/movie?with_genres=14&sort_by=popularity&api_key=ac80477002d21dc4400901f64c0506a7&language=en-US/4W0FnjSGn4x0mKZlBRx8OjFxQUM.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);

        recyclerView = findViewById(R.id.rv_genres);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
        pgBar = findViewById(R.id.pg_genre_movie);
        pgBar.setVisibility(View.VISIBLE);

        movieArrayList = new ArrayList<>();
        popularAdapter = new PopularAdapter(movieArrayList, this);
        recyclerView.setAdapter(popularAdapter);
        getPopMovie();
        popularAdapter.notifyDataSetChanged();
    }

    private void getPopMovie() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.themoviedb.org/3/discover/movie?with_genres=14&sort_by=popularity&api_key=ac80477002d21dc4400901f64c0506a7&language=en-US/4W0FnjSGn4x0mKZlBRx8OjFxQUM.jpg",null, new Response.Listener<JSONObject>() {
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
    private void getHorrorMovie() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.themoviedb.org/3/discover/movie?with_genres=27&sort_by=popularity&api_key=ac80477002d21dc4400901f64c0506a7&language=en-US/4W0FnjSGn4x0mKZlBRx8OjFxQUM.jpg",null, new Response.Listener<JSONObject>() {
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
                getPopMovie(
//                        "https://api.themoviedb.org/3/discover/movie?with_genres=14&sort_by=popularity&api_key=ac80477002d21dc4400901f64c0506a7&language=en-US/4W0FnjSGn4x0mKZlBRx8OjFxQUM.jpg"
                );
                break;
            case R.id.horror:
                movieArrayList.clear();
                getHorrorMovie(
//
                );
                break;
            case R.id.commedy:
                movieArrayList.clear();
                getPopMovie(
//                        "https://api.themoviedb.org/3/discover/movie?with_genres=35&sort_by=popularity&api_key=ac80477002d21dc4400901f64c0506a7&language=en-US/4W0FnjSGn4x0mKZlBRx8OjFxQUM.jpg"
                );
                break;
            case R.id.romance:
                movieArrayList.clear();
                getPopMovie(
//                        "https://api.themoviedb.org/3/discover/movie?with_genres=10749&sort_by=popularity&api_key=ac80477002d21dc4400901f64c0506a7&language=en-US/4W0FnjSGn4x0mKZlBRx8OjFxQUM.jpg"
                );
                break;

        }
    }
}
