package com.example.merutest.interfaces

import com.example.merutest.entity.EntitySaveRecipes

interface DeleteRecipe {
    fun deleteFromSave(entitySaveRecipes: EntitySaveRecipes)
}