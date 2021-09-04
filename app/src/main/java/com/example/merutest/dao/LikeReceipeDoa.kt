package com.example.merutest.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.merutest.entity.EntityLikeReceipe

@Dao
interface LikeReceipeDoa
{
    @Insert
    suspend fun likeReceipe(entityLikeReceipe: EntityLikeReceipe)

    @Delete
    suspend fun dislikeReceipe(entityLikeReceipe: EntityLikeReceipe)

    @Query("SELECT * FROM `like`")
    fun getLikeRecipe(): LiveData<List<EntityLikeReceipe>>
}