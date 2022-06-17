package com.example.movieapp.Listeners;

import com.example.movieapp.Models.ApiResponse;

public interface OnApiListener {

void onResponse(ApiResponse response );
void onError(String message );

}
