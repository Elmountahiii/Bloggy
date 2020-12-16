package com.redgunner.bloggy.models

import androidx.room.Entity
import androidx.room.PrimaryKey
/*
* this is a data class for single category and how it should looks
* */
@Entity(tableName = "CategoryTable")
data class Category (val name:String=""){

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}