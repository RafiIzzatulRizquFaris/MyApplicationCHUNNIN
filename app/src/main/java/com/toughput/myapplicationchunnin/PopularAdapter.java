package com.toughput.myapplicationchunnin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    List<Movie> movieArrayList;
    Context context;

    public PopularAdapter(List<Movie> movieArrayList, Context context) {
        this.movieArrayList = movieArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_popular, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(movieArrayList.get(position).getTitle());
        holder.date.setText(movieArrayList.get(position).getDate());
        String url = "https://image.tmdb.org/t/p/w500"+movieArrayList.get(position).getPosterpath();
        Glide.with(context).load(url).apply(new RequestOptions().override(150, 150)).into(holder.imgPoster);

    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, date;
        ImageView imgPoster;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.img_item_photo_movie);
            title = itemView.findViewById(R.id.tv_item_name_movie);
            date = itemView.findViewById(R.id.movie_final_date);
        }
    }
}
