package ru.gozerov.data.news.cache.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.gozerov.data.news.models.CacheNewsApi

@Dao
interface NewsDao {

    @Query("SELECT * FROM news_db")
    fun getNews(): Flow<List<CacheNewsApi>>

    @Query("SELECT * FROM news_db WHERE id = :id")
    suspend fun getNewsById(id: Int): CacheNewsApi

    @Insert
    suspend fun initializeDatabase(news: List<CacheNewsApi>)

    @Query("SELECT count(*) FROM news_db")
    suspend fun getNewsCount(): Int

    @Query("DELETE FROM news_db")
    suspend fun clearDatabase()

}