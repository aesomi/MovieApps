package com.example.movieapp;

import android.content.Context;
import android.widget.Toast;

import com.example.movieapp.Listeners.OnApiListener;
import com.example.movieapp.Listeners.OnDetailsApiListener;
import com.example.movieapp.Models.ApiResponse;
import com.example.movieapp.Models.DetailApıResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public class Manager {

    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.collectapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public Manager(Context context) {
        this.context = context;
    }


    public void searchMovies(OnApiListener listener , String movie_name){
        getMovies getMovies = retrofit.create(Manager.getMovies.class);
        Call<ApiResponse> call = getMovies.callMovies(movie_name);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, "Couldn't fetch Data", Toast.LENGTH_SHORT).show();
                return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });

    }


    public void searchMoviesDetails(OnDetailsApiListener listener , String movie_id){
        getMoviesDetails getMoviesDetails = retrofit.create(Manager.getMoviesDetails.class);
        Call<DetailApıResponse> call = getMoviesDetails.callMovieDetails(movie_id);

        call.enqueue(new Callback<DetailApıResponse>() {
            @Override
            public void onResponse(Call<DetailApıResponse> call, Response<DetailApıResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, "Couldn't fetch Data", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<DetailApıResponse> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });

    }


    public interface  getMovies {
        @Headers({
                "Accept: application/json",
                "authorization: apikey 5Euy5JAh3MLUyh9KQKzDHQ:0sATh9gYYKQqdwDNw8pX4d",
                "content-type: application/json"
        })
        @GET("imdb/imdbSearchByName?query={movie_name}")
        Call<ApiResponse> callMovies(
            @Path("movie_name") String movie_name
            );
    }

    public interface  getMoviesDetails {
        @Headers({
                "Accept: application/json",
                "authorization: apikey 5Euy5JAh3MLUyh9KQKzDHQ:0sATh9gYYKQqdwDNw8pX4d",
                "content-type: application/json"
        })
        @GET("imdb/imdbSearchById?movieId={movie_id}")
        Call<DetailApıResponse> callMovieDetails(
                @Path("movie_id") String movie_id
        );
    }
}
