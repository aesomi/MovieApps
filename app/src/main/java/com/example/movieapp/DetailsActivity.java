package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapp.Adapters.RatesRecyclerAdapter;
import com.example.movieapp.Listeners.OnDetailsApiListener;
import com.example.movieapp.Models.DetailApıResponse;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    TextView   textView_rate, textView_source,textView_movie_name,textView_movie_released,textView_movie_runtime,textView_movie_plot;
     ImageView imageView_movie_poster;
     RecyclerView recycler_movie_ratings;
     RatesRecyclerAdapter adapter;
     Manager manager;
     ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);



        textView_rate = findViewById(R.id.textView_rate);
        textView_source = findViewById(R.id.textView_source);
        textView_movie_name = findViewById(R.id.textView_movie_name);
        textView_movie_released = findViewById(R.id.textView_movie_released);
        textView_movie_runtime = findViewById(R.id.textView_movie_runtime);
        textView_movie_plot = findViewById(R.id.textView_movie_plot);
        imageView_movie_poster= findViewById(R.id.imageView_movie_poster);
        recycler_movie_ratings=  findViewById(R.id.recycler_movie_ratings);
        manager= new Manager(this);
        String movie_id = getIntent().getStringExtra("data");

        dialog=new ProgressDialog(this);
        dialog.setTitle("Please");
        dialog.show();
        manager.searchMoviesDetails(listener,movie_id);


    }
    private OnDetailsApiListener listener = new OnDetailsApiListener() {
        @Override
        public void onResponse(DetailApıResponse response) {
            dialog.dismiss();
            if (response.equals(null)) {
                Toast.makeText(DetailsActivity.this, "Error", Toast.LENGTH_SHORT).show();
                return;
            }
            showResults(response);
        }

        @Override
        public void onError(String message) {
            Toast.makeText(DetailsActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
    };

    private void showResults(DetailApıResponse response) {
        textView_movie_name.setText(response.getTitle());
        textView_movie_released.setText("Year Released:" + response.getYear());
        Picasso.get().load(response.getPoster()).into(imageView_movie_poster);
recycler_movie_ratings.setHasFixedSize(true);
recycler_movie_ratings.setLayoutManager(new GridLayoutManager(this,1));
adapter = new RatesRecyclerAdapter(this,response.getRates());
recycler_movie_ratings.setAdapter(adapter);



    }
}