package com.example.merutest.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.merutest.entity.EntityLikeReceipe
import com.example.merutest.entity.EntitySaveRecipes

@Dao
interface SaveReceipeDoa
{
    @Insert
    suspend fun saveReceipe(entitySaveRecipes: EntitySaveRecipes)

    @Delete
    suspend fun deleteReceipe(entitySaveRecipes: EntitySaveRecipes)

    @Query("SELECT * FROM `save`")
    fun getSaveRecipe(): LiveData<List<EntitySaveRecipes>>
}