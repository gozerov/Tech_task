package ru.gozerov.data.news.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.gozerov.data.news.cache.NewsDao
import ru.gozerov.data.news.models.CacheNewsApi
import ru.gozerov.data.news.models.DataNewsApi
import ru.gozerov.data.news.remote.NewsRemoteService
import ru.gozerov.domain.news.models.NewsApi
import ru.gozerov.domain.news.models.SimpleNews
import ru.gozerov.domain.news.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor (
    private val newsDao: NewsDao,
    private val newsRemoteService: NewsRemoteService
): NewsRepository {

    override suspend fun fetchSimpleNews(): Flow<List<SimpleNews>> = withContext(Dispatchers.IO) {
        try {
            val news = newsRemoteService.fetchNews().articles.toCacheNewsApi()
            if (newsDao.getNewsCount()==0 || newsDao.getNews()!=news) {
                newsDao.initializeDatabase(news)
                val newsWithIds = newsDao.getNews()
                return@withContext newsWithIds.map { it.toListSimpleNews() }
            } else {
                return@withContext newsDao.getNews().map { it.toListSimpleNews() }
            }
        } catch (e: Exception) {
            return@withContext newsDao.getNews().map { it.toListSimpleNews() }
        }
    }

    override suspend fun getNewsById(id: Int): NewsApi = withContext(Dispatchers.IO) {
        return@withContext newsDao.getNewsById(id).toNewsApi()
    }


    //Mappers

    private fun List<DataNewsApi>.toCacheNewsApi() = this.map { news ->
        CacheNewsApi(
            id = 0,
            source = news.source.name,
            author = news.author,
            title = news.title,
            description = news.description,
            url = news.url,
            urlToImage = news.urlToImage,
            publishedAt = news.publishedAt,
            content = news.content
        )
    }

    private fun List<CacheNewsApi>.toListSimpleNews() = this.map { newsApi ->
        SimpleNews(
            id = newsApi.id,
            title = newsApi.title,
            description = newsApi.description
        )
    }

    private fun CacheNewsApi.toNewsApi() = NewsApi(
        source = source,
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        imageUrl = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content
    )

}