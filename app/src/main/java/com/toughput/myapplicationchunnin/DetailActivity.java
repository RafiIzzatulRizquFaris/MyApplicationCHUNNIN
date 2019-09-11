package com.toughput.myapplicationchunnin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    Movie movie;
    ImageView postershow;
    CollapsingToolbarLayout collapsingshow;
    TextView showOverview, showVote, showDate, showPop;
    FloatingActionButton btnBackDropShow;
    ProgressBar pgDetail;
    Button btnFavorite;
    private Boolean isFavorite = false;
//    private TvHelper tvHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
    }
}
