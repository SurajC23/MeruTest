package com.demo.myfirstapp.network

import com.example.merutest.model.RecipeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("search")
    fun getDataFromAPI(@Query("q") query: String): Call<RecipeModel>
}