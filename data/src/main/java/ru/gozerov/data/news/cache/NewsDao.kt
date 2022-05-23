package ru.gozerov.data.news.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.gozerov.data.news.models.DataNewsApi

@Dao
interface NewsDao {

    @Query("SELECT * FROM news_db")
    fun getNews(): Flow<DataNewsApi>

    @Query("SELECT * FROM news_db WHERE id = :id")
    suspend fun getNewsById(id: Int): DataNewsApi

    @Insert
    suspend fun initializeDatabase(news: List<DataNewsApi>)

    @Query("SELECT count(*) FROM news_db")
    suspend fun getNewsCount(): Int

}