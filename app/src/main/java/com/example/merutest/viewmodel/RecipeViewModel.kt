package com.example.merutest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.myfirstapp.network.RetroInstance
import com.demo.myfirstapp.network.RetroService
import com.example.merutest.model.RecipeModel
import retrofit2.Call
import retrofit2.Response

class RecipeViewModel: ViewModel()
{
    lateinit var recipeListData: MutableLiveData<RecipeModel>

    init {
        recipeListData = MutableLiveData()
    }

    fun getRecipeListDataObserver(): MutableLiveData<RecipeModel>
    {
        return recipeListData
    }

    fun makeApiCall(input: String)
    {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromAPI(input)
        call.enqueue(object : retrofit2.Callback<RecipeModel>{
            override fun onResponse(call: Call<RecipeModel>, response: Response<RecipeModel>) {
                if(response.isSuccessful) {
                    recipeListData.postValue(response.body())
                } else {
                    recipeListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<RecipeModel>, t: Throwable) {
                recipeListData.postValue(null)
            }
        })
    }
}