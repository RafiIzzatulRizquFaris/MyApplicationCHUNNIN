package com.toughput.myapplicationchunnin.GenresFragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.toughput.myapplicationchunnin.DetailActivity;
import com.toughput.myapplicationchunnin.ItemClickSupport;
import com.toughput.myapplicationchunnin.Movie;
import com.toughput.myapplicationchunnin.MovieActivity;
import com.toughput.myapplicationchunnin.PopularAdapter;
import com.toughput.myapplicationchunnin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdventureFragment extends Fragment {


    public AdventureFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    PopularAdapter popularAdapter;
    ArrayList<Movie> movieArrayList;
    ProgressBar pgBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_adventure, container, false);
        recyclerView = v.findViewById(R.id.rv_adventure);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        pgBar = v.findViewById(R.id.pg_adventure_movie);
        pgBar.setVisibility(View.VISIBLE);

        movieArrayList = new ArrayList<>();
        getMovie();
        popularAdapter = new PopularAdapter(movieArrayList, getContext());
        recyclerView.setAdapter(popularAdapter);
        popularAdapter.notifyDataSetChanged();
//        ArrayList<Movie> list = new ArrayList<>();

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MOVIE, movieArrayList.get(position));
                startActivity(intent);
            }
        });
        return v;
    }

    private void getMovie() {
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.themoviedb.org/3/discover/movie?api_key=ac80477002d21dc4400901f64c0506a7&language=en-US",null, new Response.Listener<JSONObject>() {
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
//        Volley.newRequestQueue(getActivity().getApplicationContext()).add(jsonObjectRequest);
        Volley.newRequestQueue(Objects.requireNonNull(getActivity()).getApplication()).add(jsonObjectRequest);
    }
}
