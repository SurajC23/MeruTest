package com.example.merutest.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.merutest.entity.EntityLikeReceipe

@Dao
interface LikeReceipeDoa
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun likeReceipe(entityLikeReceipe: EntityLikeReceipe)

    @Delete
    suspend fun dislikeReceipe(entityLikeReceipe: EntityLikeReceipe)

    @Query("SELECT * FROM `like`")
    fun getLikeRecipe(): LiveData<List<EntityLikeReceipe>>
}