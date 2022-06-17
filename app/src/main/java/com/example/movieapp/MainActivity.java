package com.example.movieapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.Adapters.HomeRecyclerAdapter;
import com.example.movieapp.Listeners.OnApiListener;
import com.example.movieapp.Listeners.OnMovieClickListener;
import com.example.movieapp.Models.ApiResponse;

public class MainActivity extends AppCompatActivity implements OnMovieClickListener {

    SearchView search_view;
    RecyclerView recycler_view_home;
    HomeRecyclerAdapter adapter;
    Manager manager ;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        search_view = findViewById(R.id.search_view);
        recycler_view_home= findViewById(R.id.recycler_view_home);


        dialog = new ProgressDialog(this);
        manager=new Manager(this);

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               dialog.setTitle("Please wait...");
               dialog.show();
                manager.searchMovies(listener,query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private  final OnApiListener listener = new OnApiListener() {
        @Override
        public void onResponse(ApiResponse response) {

           dialog.dismiss();
            if(response==null){
                Toast.makeText(MainActivity.this, "No data available", Toast.LENGTH_SHORT).show();
                return;
            }
            showResult(response);
        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(MainActivity.this, "An Error Occured", Toast.LENGTH_SHORT).show();

        }
    };

    private void showResult(ApiResponse response) {
    recycler_view_home.setHasFixedSize(true);
    recycler_view_home.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
    adapter = new HomeRecyclerAdapter(this,response.getResult(),this);
    recycler_view_home.setAdapter(adapter);
    }


    @Override
    public void onMovieClicked(String id) {
        startActivity(new Intent(MainActivity.this, DetailsActivity.class).putExtra("data",id));
    }
}