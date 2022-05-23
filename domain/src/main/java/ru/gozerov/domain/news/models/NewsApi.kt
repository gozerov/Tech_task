package ru.gozerov.domain.news.models

data class NewsApi(
    val source: NewsSource,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val imageUrl: String,
    val publishedAt: String,
    val content: String?
)