package ru.gozerov.data.news.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
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

    override suspend fun fetchSimpleNews(): Flow<List<SimpleNews>> {
        return if (newsDao.getNewsCount()==0 || newsDao.getNews()!=newsRemoteService.fetchNews().articles) {
            val news = newsRemoteService.fetchNews().articles
            newsDao.initializeDatabase(news.toCacheNewsApi())
            flowOf(news.toListSimpleNews())
        } else {
            flowOf(newsRemoteService.fetchNews().articles.toListSimpleNews())
        }
    }

    override suspend fun getNewsById(id: Int): NewsApi {
        return newsDao.getNewsById(id).toNewsApi()
    }


    //Mappers

    private fun List<DataNewsApi>.toCacheNewsApi() = this.map { news ->
        CacheNewsApi(
            id = news.id,
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

    private fun List<DataNewsApi>.toListSimpleNews() = this.map { newsApi ->
        SimpleNews(
            title = newsApi.title,
            content = newsApi.content
        )
    }

    private fun CacheNewsApi.toNewsApi() = NewsApi(
        source = this.source,
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        imageUrl = this.url,
        publishedAt = this.publishedAt,
        content = this.content
    )

}