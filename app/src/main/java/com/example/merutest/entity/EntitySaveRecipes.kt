package com.example.merutest.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "save", indices = [Index(value = ["recipe_id"], unique = true)])
data class EntitySaveRecipes
    ( @PrimaryKey(autoGenerate = true)
      val id: Long,
      val recipe_id: String,
      val image_url: String,
      val title: String,
      val publisher: String ) {
}