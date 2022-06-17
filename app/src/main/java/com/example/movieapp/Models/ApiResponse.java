package com.example.movieapp.Models;

import java.util.List;

public class ApiResponse {
    List<ArrayObject> result = null;

    public List<ArrayObject> getResult() {
        return result;
    }

    public void setResult(List<ArrayObject> result) {
        this.result = result;
    }
}
