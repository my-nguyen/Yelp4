package com.nguyen.yelp4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.nguyen.yelp4.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
        const val BASE_URL = "https://api.yelp.com/v3/"
        const val API_KEY = "7WnY3zVPZ8Yj9S8JWphAu5dn8myhk0N0eAZ4P5vluMBcEg7t1gc41fdBHgluTHLNziDGBiH0UvciG4-p-IJQfPvR5Pdhd9WJ1G4pQnwZr6_cZG54KU6rZVrjITSfX3Yx"
    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val businesses = mutableListOf<Business>()
        val adapter = BusinessAdapter(this, businesses)
        binding.rvBusinesses.adapter = adapter
        binding.rvBusinesses.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val yelp = retrofit.create(YelpService::class.java)
        val result = yelp.searchBusinesses("Bearer $API_KEY", "Avocado Toast", "New York")
        result.enqueue(object: Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                Log.i(TAG, "onResponse $response")
                val body = response.body()
                if (body == null) {
                    Log.w(TAG, "Did not receive valid response body from Yelp API")
                } else {
                    businesses.addAll(body.businesses)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.i(TAG, "onFailure $t")
            }
        })
    }
}