package ru.gozerov.data.news.repository

import kotlinx.coroutines.flow.Flow
import ru.gozerov.data.news.cache.storage.NewsStorage
import ru.gozerov.data.news.models.NewsMapper
import ru.gozerov.data.news.remote.NewsRemote
import ru.gozerov.domain.news.models.NewsApi
import ru.gozerov.domain.news.models.SimpleNews
import ru.gozerov.domain.news.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor (
    private val newsStorage: NewsStorage,
    private val newsRemote: NewsRemote
): NewsRepository {

    override suspend fun fetchSimpleNews(): Flow<List<SimpleNews>> {
        return try {
            val news = newsRemote.fetchNews()
            if (newsStorage.compareNews(news)) {
                NewsMapper.map(newsStorage.initializeDatabase(news))
            } else {
                NewsMapper.map(newsStorage.getNews())
            }
        } catch (e: Exception) {
            NewsMapper.map(newsStorage.getNews())
        }
    }

    override suspend fun getNewsById(id: Int): NewsApi {
        return NewsMapper.map(newsStorage.getNewsById(id))
    }

}