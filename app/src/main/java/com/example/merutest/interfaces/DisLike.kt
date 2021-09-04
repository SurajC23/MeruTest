package com.example.merutest.interfaces

import com.example.merutest.entity.EntityLikeReceipe
import com.example.merutest.model.ReceipeData

interface DisLike {
    fun removeFromLike(entityLikeReceipe: EntityLikeReceipe)
}