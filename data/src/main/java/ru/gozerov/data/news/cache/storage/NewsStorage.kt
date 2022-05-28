package ru.gozerov.data.news.cache.storage

import kotlinx.coroutines.flow.Flow
import ru.gozerov.data.news.models.CacheNewsApi

interface NewsStorage {

    fun getNews(): Flow<List<CacheNewsApi>>

    suspend fun getNewsById(id: Int): CacheNewsApi

    suspend fun initializeDatabase(news: List<CacheNewsApi>) : Flow<List<CacheNewsApi>>

    suspend fun getNewsCount(): Int

    suspend fun compareNews(remoteNews: List<CacheNewsApi>): Boolean
}