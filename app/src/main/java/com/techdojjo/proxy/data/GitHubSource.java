package com.techdojjo.proxy.data;

import com.techdojjo.proxy.data.model.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GitHubSource {

    @GET("users/{username}")
    Observable<User> getUser(@Path("username") String username);

}
