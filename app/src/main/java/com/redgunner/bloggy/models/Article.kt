package com.redgunner.bloggy.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* this is a data class for single article and how it should looks represents the SQLite table
* Each property in the class represents a column in the table.
* */
@Entity(tableName = "ArticlesTable")
data class Article(
    val title: String,
    val category: String,
    val body: String,
    val date: String,
    var articleSaved: Boolean = false,
    var articleSeen: Boolean = false,
    val imageURL: String,
    val categoryColor: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
