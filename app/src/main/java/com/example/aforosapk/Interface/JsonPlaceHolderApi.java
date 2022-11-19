package com.example.aforosapk.Interface;

import com.example.aforosapk.Models.Post;

import java.util.List;

import retrofit2.*;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();

}
