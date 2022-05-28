package ru.gozerov.data.news.models

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.gozerov.domain.news.models.NewsApi
import ru.gozerov.domain.news.models.SimpleNews

internal object NewsMapper {

    fun map(news: Flow<List<CacheNewsApi>>): Flow<List<SimpleNews>> {
        return news.map {
                list -> list.map {
                    n -> SimpleNews(n.id, n.title, n.description)
                }
        }
    }

    fun map(news: CacheNewsApi): NewsApi {
        return NewsApi(
            source = news.source,
            author = news.author,
            title = news.title,
            description = news.description,
            url = news.url,
            imageUrl = news.urlToImage,
            publishedAt = news.publishedAt,
            content = news.content
        )
    }

    fun map(news: DataNewsResponse): List<CacheNewsApi> {
        return news.articles.toCacheNewsApi()
    }

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

}