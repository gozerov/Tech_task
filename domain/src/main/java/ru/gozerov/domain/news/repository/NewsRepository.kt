package ru.gozerov.domain.news.repository

import kotlinx.coroutines.flow.Flow
import ru.gozerov.core.Repository
import ru.gozerov.domain.news.models.NewsApi
import ru.gozerov.domain.news.models.SimpleNews

interface NewsRepository: Repository {

    suspend fun fetchSimpleNews(): Flow<List<SimpleNews>>

    suspend fun getNewsById(id: Int): NewsApi

}