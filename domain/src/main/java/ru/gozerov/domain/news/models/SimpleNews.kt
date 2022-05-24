package ru.gozerov.domain.news.models

data class SimpleNews(
    val id: Int,
    val title: String,
    val description: String?
)