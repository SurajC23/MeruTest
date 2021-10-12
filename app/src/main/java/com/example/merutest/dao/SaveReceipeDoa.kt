package com.example.merutest.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.merutest.entity.EntityLikeReceipe
import com.example.merutest.entity.EntitySaveRecipes

@Dao
interface SaveReceipeDoa
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveReceipe(entitySaveRecipes: EntitySaveRecipes)

    @Delete
    suspend fun deleteReceipe(entitySaveRecipes: EntitySaveRecipes)

    @Query("SELECT * FROM `save`")
    fun getSaveRecipe(): LiveData<List<EntitySaveRecipes>>
}