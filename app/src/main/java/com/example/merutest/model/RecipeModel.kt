package com.example.merutest.model

data class RecipeModel(val recipes: ArrayList<ReceipeData>)
data class ReceipeData(val recipe_id: String, val image_url: String, val publisher: String, val title: String)
