package ru.gozerov.data.news.models

data class DataNewsApi (
    val source: DataArticleSource,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
    )