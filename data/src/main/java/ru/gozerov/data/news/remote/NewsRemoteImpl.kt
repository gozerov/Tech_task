package ru.gozerov.data.news.remote

import kotlinx.coroutines.withContext
import ru.gozerov.data.news.cache.dispatchers.Dispatcher
import ru.gozerov.data.news.models.CacheNewsApi
import ru.gozerov.data.news.models.NewsMapper
import javax.inject.Inject

class NewsRemoteImpl @Inject constructor(
    private val newsRetrofit: NewsRetrofit,
    private val dispatcher: Dispatcher
): NewsRemote {

    override suspend fun fetchNews(): List<CacheNewsApi> = withContext(dispatcher.value) {
        return@withContext NewsMapper.map(newsRetrofit.fetchNews())
    }


}