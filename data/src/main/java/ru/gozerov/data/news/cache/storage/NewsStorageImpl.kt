package ru.gozerov.data.news.cache.storage

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import ru.gozerov.data.news.cache.dispatchers.Dispatcher
import ru.gozerov.data.news.cache.room.NewsDao
import ru.gozerov.data.news.models.CacheNewsApi
import javax.inject.Inject

class NewsStorageImpl @Inject constructor(
    private val newsDao: NewsDao,
    private val dispatcher: Dispatcher
): NewsStorage {

    override fun getNews(): Flow<List<CacheNewsApi>> = newsDao.getNews().flowOn(dispatcher.value)

    override suspend fun getNewsById(id: Int): CacheNewsApi = withContext(dispatcher.value) {
        return@withContext newsDao.getNewsById(id)
    }

    override suspend fun initializeDatabase(news: List<CacheNewsApi>) = withContext(dispatcher.value) {
        newsDao.clearDatabase()
        newsDao.initializeDatabase(news)
        return@withContext newsDao.getNews()
    }

    override suspend fun getNewsCount(): Int = withContext(dispatcher.value) {
        newsDao.getNewsCount()
    }

    override suspend fun compareNews(remoteNews: List<CacheNewsApi>): Boolean = withContext(dispatcher.value) {
        return@withContext newsDao.getNewsCount()==0 ||
                newsDao.getNews().firstOrNull()?.map { it.copy(id = 0) } != remoteNews
    }
}