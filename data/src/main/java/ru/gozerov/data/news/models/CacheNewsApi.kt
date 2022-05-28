package ru.gozerov.data.news.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.gozerov.data.news.cache.room.NewsDatabase

@Entity(tableName = NewsDatabase.name)
data class CacheNewsApi(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val source: String?,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)