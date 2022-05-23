package ru.gozerov.data.news.models

import com.google.gson.annotations.SerializedName

data class DataNewsResponse(
    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalArticles: Int,

    @SerializedName("articles")
    val articles: List<DataNewsApi>
)
