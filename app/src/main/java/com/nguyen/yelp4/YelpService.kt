package com.nguyen.yelp4

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface YelpService {
    @GET("businesses/search")
    fun searchBusinesses(
        @Header("Authorization") authorization: String,
        @Query("term") term: String,
        @Query("location") location: String
    ): Call<Data>
}