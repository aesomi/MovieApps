package com.example.movieapp.Listeners;

import com.example.movieapp.Models.DetailApıResponse;

public interface OnDetailsApiListener {
    void onResponse(DetailApıResponse response);
    void onError (String message);

}
