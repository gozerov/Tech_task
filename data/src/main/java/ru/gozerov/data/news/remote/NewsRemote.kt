package ru.gozerov.data.news.remote

import ru.gozerov.data.news.models.CacheNewsApi

interface NewsRemote {
    suspend fun fetchNews(): List<CacheNewsApi>
}